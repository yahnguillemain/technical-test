package com.lombardinternational.technicaltest.springmvc.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    private String firstname;
    private String lastname;
}
