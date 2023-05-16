package com.lombardinternational.technicaltest.mapstruct;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Import(PersonModelMapper.class)
public class PersonModelMapperTest {

    private PersonModelMapper mapper= Mappers.getMapper(PersonModelMapper.class);

    /**
     * Here the mapper must map the person from entity to model with following rules:
     * Firstname is mapped as is
     * Lastname is mapped and putin upper case
     * Age is calculated based on Birth Date
     */
    @Test
    public void testMappingPersonModel() {
        PersonEntity source = PersonEntity.builder()
                                          .birthDate(LocalDate.now().minus(18, ChronoUnit.YEARS))
                                          .firstname("Samuel")
                                          .lastname("Henri")
                                          .build();

        assertThat(mapper.map(source)).hasFieldOrPropertyWithValue("firstname", "Samuel")
                                      .hasFieldOrPropertyWithValue("lastname", "HENRI")
                                      .hasFieldOrPropertyWithValue("age", 18);

    }

}
