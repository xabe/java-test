package com.xabe.http;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RateType {
  CAD,
  HKD,
  ISK,
  PHP,
  DKK,
  HUF,
  CZK,
  AUD,
  RON,
  SEK,
  IDR,
  INR,
  BRL,
  RUB,
  HRK,
  JPY,
  THB,
  CHF,
  SGD,
  PLN,
  BGN,
  TRY,
  CNY,
  NOK,
  NZD,
  ZAR,
  USD,
  MXN,
  ILS,
  GBP,
  KRW,
  MYR;

  public static final Map<String, RateType> RATE_TYPE_MAP =
      Stream.of(RateType.values()).collect(Collectors.collectingAndThen(Collectors.toMap(RateType::name,
          Function.identity()), Collections::unmodifiableMap));

  public static RateType getRateType(final String key) {
    return RATE_TYPE_MAP.get(key);
  }
}
