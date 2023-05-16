package com.lombardinternational.technicaltest.springmvc.mapper;

import com.lombardinternational.technicaltest.springmvc.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface PersonPatcher {

    void patch(@MappingTarget Person target, Person source);
}
