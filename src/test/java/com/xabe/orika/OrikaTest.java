package com.xabe.orika;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.xabe.orika.config.ConfigOrika;
import com.xabe.orika.dto.Person;
import com.xabe.orika.dto.PersonDTO;
import com.xabe.orika.dto.UserDTO;
import com.xabe.orika.dto.UserMutableDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConfigOrika.class})
public class OrikaTest {

  @Autowired
  private MapperComponent mapperComponent;

  @Test
  public void givenAPersonWhenInvokeMapThenReturnPersonDTO() throws Exception {
    //Given
    final Person person = new Person("pepe", "perez", LocalDate.of(1985, Month.JANUARY, 1));

    //When
    final PersonDTO result = this.mapperComponent.map(person, PersonDTO.class);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getName(), is(notNullValue()));
    assertThat(result.getSurname(), is(notNullValue()));
    assertThat(result.getBirthdate(), is(notNullValue()));
  }

  @Test
  public void givenAListPersonWhenInvokeMapThenReturnListPersonDTO() throws Exception {
    //Given
    final Person person = new Person("pepe", "perez", LocalDate.of(1985, Month.JANUARY, 1));

    //When
    final List<PersonDTO> result = this.mapperComponent.mapList(List.of(person), PersonDTO.class);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.get(0).getName(), is(notNullValue()));
    assertThat(result.get(0).getSurname(), is(notNullValue()));
    assertThat(result.get(0).getBirthdate(), is(notNullValue()));
  }

  @Test
  public void givenAPersonWhenInvokeMapThenReturnUserDTO() throws Exception {
    //Given
    final Person person = new Person("pepe", "perez", LocalDate.of(1985, Month.JANUARY, 1));

    //When
    final UserDTO result = this.mapperComponent.map(person, UserDTO.class);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getName(), is(notNullValue()));
    assertThat(result.getSurname(), is(notNullValue()));
    assertThat(result.getBirthdate(), is(notNullValue()));
  }

  @Test
  public void givenAPersonWhenInvokeMapThenReturnUserMutableDTO() throws Exception {
    //Given
    final Person person = new Person("pepe", "perez", LocalDate.of(1985, Month.JANUARY, 1));
    final UserMutableDTO userMutableDTO = new UserMutableDTO();

    //When
    this.mapperComponent.map(person, userMutableDTO);

    //Then
    assertThat(userMutableDTO, is(notNullValue()));
    assertThat(userMutableDTO.getName(), is(notNullValue()));
    assertThat(userMutableDTO.getSurname(), is(notNullValue()));
    assertThat(userMutableDTO.getBirthdate(), is(notNullValue()));
  }
}
