package com.xabe.orika.mapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class PersonToUserDTOConverter extends CustomConverter<Person,UserDTO> {

    @Override
    public UserDTO convert(Person person, Type<? extends UserDTO> type, MappingContext mappingContext) {
        return UserDTO.of(person);
    }
}
