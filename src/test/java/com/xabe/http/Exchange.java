package com.xabe.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Map;

public class Exchange {

  private final Map<RateType, Long> rates;

  private final String base;

  private final LocalDate date;

  @JsonCreator
  public Exchange(
      @JsonProperty("rates") final Map<RateType, Long> rates,
      @JsonProperty("base") final String base,
      @JsonProperty("date") final LocalDate date) {
    this.rates = rates;
    this.base = base;
    this.date = date;
  }

  public Map<RateType, Long> getRates() {
    return this.rates;
  }

  public String getBase() {
    return this.base;
  }

  public LocalDate getDate() {
    return this.date;
  }
}

