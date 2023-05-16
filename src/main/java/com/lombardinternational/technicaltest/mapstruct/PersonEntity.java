package com.lombardinternational.technicaltest.mapstruct;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonEntity {

    private String firstname;
    private String lastname;
    private LocalDate birthDate;
}
