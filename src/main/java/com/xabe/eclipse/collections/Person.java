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
public class Person {

  private final String id;

  private final String name;

  private final String surname;

  private final Integer age;

  private final Address address;

}
