package com.example.nav;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NavController {

    private NavService navService;

    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/types")
    public List<ServiceTypeDTO> getTypeList() {
        return navService.getTypeList();
    }

    @PostMapping("/appointments")
    public TaxTimeDTO addNewAppointment(@Valid @RequestBody CreateAppointmentCommand command) {
        return navService.addAppointment(command);
    }

}
