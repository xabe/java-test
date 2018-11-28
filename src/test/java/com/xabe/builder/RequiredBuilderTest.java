package com.xabe.builder;

import com.xabe.comparator.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RequiredBuilderTest {

    @Test
    public void shouldCreateAPerson() throws Exception {
        //Given

        final Person person = new Person(PersonBuilder.builder().withName("name").withSurname("surname").withAge(22));

        assertThat(person, is(notNullValue()));
        assertThat(person.getName(), is("name"));
        assertThat(person.getSurname(), is("surname"));
        assertThat(person.getAge(), is(22));

    }

//    @Test
//    public void shouldNotCreateAPerson() throws Exception {
        //Given
//        Assertions.assertThrows(ClassCastException.class, () -> {
 //           new Person(PersonBuilder.builder().withAge(22));
 //       });
 //   }

}
