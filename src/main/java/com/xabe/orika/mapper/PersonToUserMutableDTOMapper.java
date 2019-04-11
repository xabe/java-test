package com.xabe.orika.mapper;

import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PersonToUserMutableDTOMapper extends ma.glasnost.orika.CustomMapper<Person, UserMutableDTO> {

    @Override
    public void mapAtoB(Person person, UserMutableDTO userMutableDTO, MappingContext context) {
        super.mapAtoB(person, userMutableDTO, context);
        userMutableDTO.setName(person.getName());
        userMutableDTO.setSurname(person.getSurname());
        userMutableDTO.setBirthdate(person.getBirthdate());
    }
}
