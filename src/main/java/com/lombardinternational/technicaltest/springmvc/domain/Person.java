package com.lombardinternational.technicaltest.springmvc.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Person {

    private String firstname;
    private String lastname;
    private Boolean modifiable;
}
