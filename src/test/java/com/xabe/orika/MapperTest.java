package com.xabe.orika;


import com.xabe.orika.config.Config;
import com.xabe.orika.mapper.Person;
import com.xabe.orika.mapper.PersonDTO;
import com.xabe.orika.mapper.UserDTO;
import com.xabe.orika.mapper.UserMutableDTO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Config.class })
public class MapperTest {

    @Autowired
    private MapperComponent mapperComponent;

    @Test
    public void givenAPersonWhenInvokeMapThenReturnPersonDTO() throws Exception {
        //Given
        final Person person = new Person("pepe","perez", LocalDate.of(1985, Month.JANUARY,1));

        //When
        final PersonDTO result = mapperComponent.map(person, PersonDTO.class);

        //Then
        MatcherAssert.assertThat(result, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getName(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getSurname(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getBirthdate(), Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void givenAListPersonWhenInvokeMapThenReturnListPersonDTO() throws Exception {
        //Given
        final Person person = new Person("pepe","perez", LocalDate.of(1985, Month.JANUARY,1));

        //When
        final List<PersonDTO> result = mapperComponent.mapList(List.of(person), PersonDTO.class);

        //Then
        MatcherAssert.assertThat(result, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.get(0).getName(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.get(0).getSurname(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.get(0).getBirthdate(), Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void givenAPersonWhenInvokeMapThenReturnUserDTO() throws Exception {
        //Given
        final Person person = new Person("pepe","perez", LocalDate.of(1985, Month.JANUARY,1));

        //When
        final UserDTO result = mapperComponent.map(person, UserDTO.class);

        //Then
        MatcherAssert.assertThat(result, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getName(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getSurname(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getBirthdate(), Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void givenAPersonWhenInvokeMapThenReturnUserMutableDTO() throws Exception {
        //Given
        final Person person = new Person("pepe","perez", LocalDate.of(1985, Month.JANUARY,1));
        final UserMutableDTO userMutableDTO = new UserMutableDTO();

        //When
        mapperComponent.map(person, userMutableDTO);

        //Then
        MatcherAssert.assertThat(userMutableDTO, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(userMutableDTO.getName(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(userMutableDTO.getSurname(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(userMutableDTO.getBirthdate(), Matchers.is(Matchers.notNullValue()));
    }
}
