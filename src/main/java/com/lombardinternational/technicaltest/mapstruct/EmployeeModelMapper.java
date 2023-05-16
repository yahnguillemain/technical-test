package com.lombardinternational.technicaltest.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class EmployeeModelMapper {

    public abstract EmployeeModel map(PersonEntity source);

}
