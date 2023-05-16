package com.lombardinternational.technicaltest.springmvc.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorResource {

    private String code;
    private String message;
    private String description;
    private Date timestamp;
}