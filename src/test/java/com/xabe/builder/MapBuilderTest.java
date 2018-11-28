package com.xabe.builder;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class MapBuilderTest {

    @Test
    public void shouldCreateMapWithBuilder() throws Exception {
        //Given
        Map<Integer, String> result = Maps.builder()
                .key(1)                 // The key type is decided here for all following keys
                .value("One")           // The value type is decided here for all following values
                .key(2)                 // Must be the same or extend the first key type
                .value("Two")           // Must be the same type or extend the first value type
                .key(10).value("Zehn") // And so on...
                .build();               // Creates the map!
        //When

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result.get(1), is("One"));
        assertThat(result.get(2), is("Two"));
        assertThat(result.get(10), is("Zehn"));

    }

    @Test
    public void shouldCreateEmpty() throws Exception {
        //Given
        Map<String, Integer> result = Maps.builder().build();
        //When

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));


    }
}
