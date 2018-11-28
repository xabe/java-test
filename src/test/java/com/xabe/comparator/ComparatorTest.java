package com.xabe.comparator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ComparatorTest {

    private Person michael = new Person("Michael", "Jackson", 51);
    private Person rod = new Person("Rod", "Stewart", 71);
    private Person paul = new Person("Paul", "McCartney", 74);
    private Person mick = new Person("Mick", "Jagger", 73);
    private Person jermaine = new Person("Jermaine", "Jackson", 61);
    
    @Test
    public void givenATwoPersonWhenInvokeCompareThenReturnInteger() throws Exception {
        //Given
        Comparator<Person> comparator = Comparator.comparing(Person::getName);

        //When
        Comparator<Person> cmpNull = Comparator.nullsLast(comparator);

        //Then
        assertThat(cmpNull, is(notNullValue()));
        assertThat(cmpNull.compare(michael,rod), is(lessThan(0)));
        assertThat(cmpNull.compare(paul,paul), is(equalTo(0)));
        assertThat(cmpNull.compare(michael,jermaine), is(greaterThan(0)));
        assertThat(cmpNull.compare(mick,null), is(lessThan(0)));
        assertThat(cmpNull.compare(null,mick), is(greaterThan(0)));
    }


}
