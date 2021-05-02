package com.xabe.currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class CurrencyConverterTest {

  /**
   * Implement the Currency converter using lambdas so that the following code compiles and executes correctly given the CurrencyConverter
   * interface.
   */
  @Test
  public void currencyConverter_1() {

    final LocalDate date = LocalDate.of(2018, 11, 5);
    final CurrencyConverter converter =
        CurrencyConverter.of(date).from("EUR").to("GBP");

    final double euros = 1d;
    final double convertedEuros = converter.convert(euros);
    final double gbps = 0.87749d;
    assertThat(convertedEuros, is(notNullValue()));
    assertThat(convertedEuros, is(equalTo(gbps)));
  }

  @Test
  public void currencyConverter_2() {

    final LocalDate date = LocalDate.of(2018, 11, 5);
    final CurrencyConverter converter =
        CurrencyConverter.of(date).from("NOK").to("EUR");

    final double norwegianKrown = 1d;
    final double convertedKrown = converter.convert(norwegianKrown);
    final double euros = 0.10507d;
    assertThat(convertedKrown, is(notNullValue()));
    assertThat(convertedKrown, is(closeTo(euros, 0.00001)));
  }

  @Test
  public void currencyConverter_3() {

    final LocalDate date = LocalDate.of(2018, 11, 5);
    final CurrencyConverter converter =
        CurrencyConverter.of(date).from("CHF").to("CAD");

    final double swissFrancs = 10d;
    final double convertedSwissFrancs = converter.convert(swissFrancs);
    final double canadianDollars = 13.0665d;
    assertThat(convertedSwissFrancs, is(notNullValue()));
    assertThat(convertedSwissFrancs, is(closeTo(canadianDollars, 0.0001)));
  }
}
