package com.xabe.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

public class SquareBuilderTest {

  @Test
  public void shouldCreateSquare() throws Exception {
    assertThat(SquareBuilder.builder().withName("square").withEdge(4).build(), is(notNullValue()));
    final BiFunction<String, Integer, String> o = (s, i) -> s.substring(i);
    final BiFunction<String, Integer, String> o1 = String::substring;

    final String name = "name";
    assertThat(o.apply(name, 1), is(o1.apply(name, 1)));
  }


}
