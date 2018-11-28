package com.xabe.builder;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShapeBuilderTest {

	@Test
	public void shouldCreateShape() throws Exception {
		assertThat(ShapeBuilder.builder().withName("shape").build(), is(notNullValue()));
	}
	
}
