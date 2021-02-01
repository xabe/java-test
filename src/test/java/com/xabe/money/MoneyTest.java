package com.xabe.money;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Currency;
import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQuery;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import org.junit.jupiter.api.Test;

public class MoneyTest {

  @Test
  public void createMoney() throws Exception {
    //Given

    //When
    final Money result = Money.of(1.0, "EUR");

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.getNumberStripped(), is(notNullValue()));
    assertThat(result.getCurrency().getCurrencyCode(), is(notNullValue()));

  }

  @Test
  public void createMoneyMorocco() throws Exception {
    //Given
    final Currency mad = Currency.getInstance("MAD");

    final CurrencyUnit ma = Monetary.getCurrency(new Locale("AR", "MA"));

    //When
    final Money result = Money.of(1.0, ma);

    //Then
    assertThat(ma.getDefaultFractionDigits(), is(2));
    assertThat(mad.getCurrencyCode(), is("MAD"));
    assertThat(result, is(notNullValue()));
    assertThat(result.getNumberStripped(), is(notNullValue()));
    assertThat(result.getCurrency().getCurrencyCode(), is(notNullValue()));
  }

  @Test
  public void createMoneyEuro() throws Exception {
    //Given
    final CurrencyUnit eur = Monetary.getCurrency(new Locale("ES", "ES"));

    //When
    final Money result = Money.of(1.0, eur);

    //Then
    assertThat(eur.getDefaultFractionDigits(), is(2));
    assertThat(result, is(notNullValue()));
    assertThat(result.getNumberStripped(), is(notNullValue()));
    assertThat(result.getCurrency().getCurrencyCode(), is(notNullValue()));
  }

  @Test
  public void givenAmountUSDWhenCustomFormatThenEquals() {
    final MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory()
        .setCurrency("USD").setNumber(1L).create();

    final AmountFormatQuery pattern = AmountFormatQueryBuilder.
        of(new Locale("en-US")).set(CurrencyStyle.NAME).set("pattern", "00000.00 ¤").build();

    final MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(pattern);
    final String customFormatted = customFormat.format(oneDollar);

    assertThat(customFormat, is(notNullValue()));
    assertThat("USD 1.00", is(oneDollar.toString()));
    assertThat("00001.00 US Dollar", is(customFormatted));
  }

  @Test
  public void givenAmountMadWhenCustomFormatThenEquals() {
    final MonetaryAmount oneDirham = Monetary.getDefaultAmountFactory()
        .setCurrency("MAD").setNumber(1L).create();

    final AmountFormatQuery pattern = AmountFormatQueryBuilder.
        of(new Locale("AR", "MA")).set(CurrencyStyle.SYMBOL).set("pattern", "#,##0.00 ¤").build();

    final MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(pattern);
    final String customFormatted = customFormat.format(oneDirham);

    assertThat(customFormat, is(notNullValue()));
    assertThat("MAD 1.00", is(oneDirham.toString()));
    assertThat("1,00 د.م.\u200F", is(customFormatted));
  }

  @Test
  public void givenAmountRupiaWhenCustomFormatThenEquals() {
    final MonetaryAmount oneRupia = Monetary.getDefaultAmountFactory()
        .setCurrency("INR").setNumber(3000.00).create();

    final AmountFormatQuery pattern = AmountFormatQueryBuilder.
        of(new Locale("", "IND")).set(CurrencyStyle.SYMBOL).build();

    final MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(pattern);
    final String customFormatted = customFormat.format(oneRupia);

    assertThat(customFormat, is(notNullValue()));
    assertThat("INR 3000.00", is(oneRupia.toString()));
    assertThat("₹ 3,000.00", is(customFormatted));
  }

}
