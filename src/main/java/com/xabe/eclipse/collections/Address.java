package com.xabe.eclipse.collections;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

  private final String street;

  private final String country;

  @EqualsAndHashCode.Exclude
  private final Integer number;
}
