package com.xabe.combiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class UserValidationTest {

  @Test
  public void givenAUserWhenInvokeNameIsNotEmptyReturnTrue() throws Exception {
    //Given
    final User user = new User("user", "");

    //Then
    final ValidationResult validationResult = UserValidation.nameIsNotEmpty().apply(user);

    //Return
    assertThat(validationResult.isValid(), is(true));
  }

  @Test
  public void givenAUserNameEmptyWhenInvokeNameIsNotEmptyReturnFalse() throws Exception {
    //Given
    final User user = new User("", "");

    //Then
    final ValidationResult validationResult = UserValidation.nameIsNotEmpty().apply(user);

    //Return
    assertThat(validationResult.isValid(), is(false));
    assertThat(validationResult.getReason().get(), is(notNullValue()));
  }

  @Test
  public void givenAUserWhenInvokeEmailIsIncorrectReturnTrue() throws Exception {
    //Given
    final User user = new User("user", "email@email.com");

    //Then
    final ValidationResult validationResult = UserValidation.emailIsCorrect().apply(user);

    //Return
    assertThat(validationResult.isValid(), is(true));
  }

  @Test
  public void givenAUserEmailWhenInvokeEmailIsIncorrectReturnFalse() throws Exception {
    //Given
    final User user = new User("", "");

    //Then
    final ValidationResult validationResult = UserValidation.emailIsCorrect().apply(user);

    //Return
    assertThat(validationResult.isValid(), is(false));
    assertThat(validationResult.getReason().get(), is(notNullValue()));
  }

  @Test
  public void givenAUserWhenInvokeAllReturnValidationResult() throws Exception {
    //Given
    final User user = new User("", "");

    //Then
    final ValidationResult validationResult =
        UserValidation.all(UserValidation.emailIsCorrect(), UserValidation.nameIsNotEmpty()).apply(user);

    //Return
    assertThat(validationResult.isValid(), is(false));
    assertThat(validationResult.getReason().get(), is(notNullValue()));


  }

}
