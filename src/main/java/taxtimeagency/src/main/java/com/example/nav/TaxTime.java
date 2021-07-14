package com.example.nav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxTime {

    private String taxNumber;

    private String type;

    private LocalDateTime start;

    private LocalDateTime end;

    public TaxTime(CreateAppointmentCommand command) {
        this.taxNumber = command.getTaxNumber();
        this.type = command.getType();
        this.start = command.getStart();
        this.end = command.getEnd();
    }
}
