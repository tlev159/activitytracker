package com.example.nav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IsValidInterval
public class CreateAppointmentCommand {

    @IsValidTaxNumber(message = "Nem jo a beirt adoazonosito!")
    private String taxNumber;

    @IsExistingType
    private String type;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime start;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime end;

}
