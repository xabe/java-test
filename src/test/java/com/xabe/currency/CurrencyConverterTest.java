package com.xabe.currency;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CurrencyConverterTest {

    /**
     * Implement the Currency converter using lambdas so that
     * the following code compiles and executes correctly
     * given the CurrencyConverter interface.
     */
    @Test
    public void currencyConverter_1() {

        LocalDate date = LocalDate.of(2018, 11, 5);
        CurrencyConverter converter =
                CurrencyConverter.of(date).from("EUR").to("GBP");

        double euros = 1d;
        double convertedEuros = converter.convert(euros);
        double gbps = 0.87749d;
        assertThat(convertedEuros, is(notNullValue()));
        assertThat(convertedEuros, is(equalTo(gbps)));
    }

    @Test
    public void currencyConverter_2() {

        LocalDate date = LocalDate.of(2018, 11, 5);
        CurrencyConverter converter =
                CurrencyConverter.of(date).from("NOK").to("EUR");

        double norwegianKrown = 1d;
        double convertedKrown = converter.convert(norwegianKrown);
        double euros = 0.10507d;
        assertThat(convertedKrown, is(notNullValue()));
        assertThat(convertedKrown, is(closeTo(euros, 0.00001)));
    }

    @Test
    public void currencyConverter_3() {

        LocalDate date = LocalDate.of(2018, 11, 5);
        CurrencyConverter converter =
                CurrencyConverter.of(date).from("CHF").to("CAD");

        double swissFrancs = 10d;
        double convertedSwissFrancs = converter.convert(swissFrancs);
        double canadianDollars = 13.0665d;
        assertThat(convertedSwissFrancs, is(notNullValue()));
        assertThat(convertedSwissFrancs, is(closeTo(canadianDollars,0.0001)));
    }
}
