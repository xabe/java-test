package com.xabe.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class ShapeBuilderTest {

  @Test
  public void shouldCreateShape() throws Exception {
    assertThat(ShapeBuilder.builder().withName("shape").build(), is(notNullValue()));
  }

}
