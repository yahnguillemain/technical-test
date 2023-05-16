package com.lombardinternational.technicaltest.springmvc.mapper;

import com.lombardinternational.technicaltest.springmvc.api.PersonDto;
import com.lombardinternational.technicaltest.springmvc.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

    PersonDto map(Person person);

    @Mapping(target = "modifiable", constant = "true")
    Person map(PersonDto dto);
}
