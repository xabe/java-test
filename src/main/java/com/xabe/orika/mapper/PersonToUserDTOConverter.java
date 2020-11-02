package com.xabe.orika.mapper;

import com.xabe.orika.dto.Person;
import com.xabe.orika.dto.UserDTO;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class PersonToUserDTOConverter extends CustomConverter<Person, UserDTO> {

  @Override
  public UserDTO convert(final Person person, final Type<? extends UserDTO> type, final MappingContext mappingContext) {
    return UserDTO.of(person);
  }
}