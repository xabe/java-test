package com.xabe.monad;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

public class MonadTest {

  @Test
  public void givenASpringWhenInvokeCompositionThenReturnString() throws Exception {
    //Given
    final String input = "  happy monading  ";

    //When
    final ResultOrError<String, String> result = Monad.trim(input).bind(Monad::toUpperCase).bind(Monad::appendExclamd);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getResult(), is("HAPPY MONADING!"));
    assertThat(result.getError(), is(nullValue()));
  }

  @Test
  public void givenASpringEmptyWhenInvokeCompositionThenReturnError() throws Exception {
    //Given
    final String input = "    ";

    //When
    final ResultOrError<String, String> result = Monad.trim(input).bind(Monad::toUpperCase).bind(Monad::appendExclamd);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getResult(), is(nullValue()));
    assertThat(result.getError(), is("String must contain non-space characters."));
  }

  @Test
  public void givenASpringNumberWhenInvokeCompositionThenReturnError() throws Exception {
    //Given
    final String input = "  hello 123  ";

    //When
    final ResultOrError<String, String> result = Monad.trim(input).bind(Monad::toUpperCase).bind(Monad::appendExclamd);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getResult(), is(nullValue()));
    assertThat(result.getError(), is("String must contain only letter and spaces."));
  }

  @Test
  public void givenASpringLengthWhenInvokeCompositionThenReturnError() throws Exception {
    //Given
    final String input = "  hello hahaha ahhahah ahahaha ";

    //When
    final ResultOrError<String, String> result = Monad.trim(input).bind(Monad::toUpperCase).bind(Monad::appendExclamd);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getResult(), is(nullValue()));
    assertThat(result.getError(), is("String not exceed 20 character"));
  }

}
