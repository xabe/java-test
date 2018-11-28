package com.xabe.builder;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SquareBuilderTest {
	
	@Test
	public void shouldCreateSquare() throws Exception {
		assertThat(SquareBuilder.builder().withName("square").withEdge(4).build(), is(notNullValue()));
		BiFunction<String, Integer, String> o  = (s, i) -> s.substring(i);
		BiFunction<String, Integer, String> o1  = String::substring;
		
		String name = "name";
		assertThat(o.apply(name, 1), is(o1.apply(name, 1)));
	}

	
}
