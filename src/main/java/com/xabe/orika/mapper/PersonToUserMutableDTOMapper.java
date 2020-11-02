package com.xabe.orika.mapper;

import com.xabe.orika.dto.Person;
import com.xabe.orika.dto.UserMutableDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PersonToUserMutableDTOMapper extends CustomMapper<Person, UserMutableDTO> {

  @Override
  public void mapAtoB(final Person person, final UserMutableDTO userMutableDTO, final MappingContext context) {
    super.mapAtoB(person, userMutableDTO, context);
    userMutableDTO.setName(person.getName());
    userMutableDTO.setSurname(person.getSurname());
    userMutableDTO.setBirthdate(person.getBirthdate());
  }
}
