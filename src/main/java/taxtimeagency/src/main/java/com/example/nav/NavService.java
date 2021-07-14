package com.example.nav;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NavService {

    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    private List<ServiceType> serviceTypes = Collections.synchronizedList(new ArrayList<>(List.of(
            new ServiceType("001", "Adóazonosító kérése"),
            new ServiceType("002", "Adószám kérése"),
            new ServiceType("003", "Adóvisszaigénylés"),
            new ServiceType("004", "Bevallások"),
            new ServiceType("005", "Adatváltozási ügyintézés"),
            new ServiceType("006", "Aáltalános ügyfélszolgálat")
    )));

    private List<TaxTime> taxTimes = new ArrayList<>();

    public NavService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public boolean hasTypeWithCode(String code) {
        return serviceTypes.stream()
        .filter(t-> t.getCode().equals(code))
        .findFirst()
        .isPresent();
    }

    public TaxTimeDTO addAppointment(CreateAppointmentCommand command) {
        return modelMapper.map(addNewAppointment(command), TaxTimeDTO.class);
    }

    private TaxTime addNewAppointment(CreateAppointmentCommand command) {
        TaxTime taxTime = new TaxTime(command);
        return taxTime;
    }


    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public List<TaxTime> getTaxTimes() {
        return taxTimes;
    }

    public List<ServiceTypeDTO> getTypeList() {
        List<ServiceType> result = new ArrayList<>(serviceTypes);
        Type targetListType = new TypeToken<List<ServiceTypeDTO>>(){}.getType();
        return modelMapper.map(result, targetListType);
    }
}
