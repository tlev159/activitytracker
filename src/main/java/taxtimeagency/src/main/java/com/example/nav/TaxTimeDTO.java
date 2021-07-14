package com.example.nav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IsValidInterval
public class TaxTimeDTO {

    @IsValidTaxNumber(message = "Nem jo a beirt adoazonosito!")
    private String taxNumber;

    @IsExistingType(message = "Nincs ilyen kodu ugy!")
    private String type;


    private LocalDateTime start;

    private LocalDateTime end;

}
