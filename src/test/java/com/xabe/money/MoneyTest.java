package com.xabe.money;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;
import java.util.Set;

public class MoneyTest {

    @Test
    public void createMoney() throws Exception {
        //Given

        //When
        final Money result = Money.of(1.0, "EUR");

        //Then
        MatcherAssert.assertThat(result, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getNumberStripped(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getCurrency().getCurrencyCode(), Matchers.is(Matchers.notNullValue()));

    }

    @Test
    public void createMoneyMorocco() throws Exception {
        //Given
        final CurrencyUnit ma = Monetary.getCurrency(new Locale("AR","MA"));

        //When
        final Money result = Money.of(1.0, ma);

        //Then
        MatcherAssert.assertThat(result, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getNumberStripped(), Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(result.getCurrency().getCurrencyCode(), Matchers.is(Matchers.notNullValue()));

    }

}
