package com.xabe.orika.dto;

import java.time.LocalDate;

public class Person {

  private final String name;

  private final String surname;

  private final LocalDate birthdate;

  public Person(final String name, final String surname, final LocalDate birthdate) {
    this.name = name;
    this.surname = surname;
    this.birthdate = birthdate;
  }

  public String getName() {
    return this.name;
  }

  public String getSurname() {
    return this.surname;
  }

  public LocalDate getBirthdate() {
    return this.birthdate;
  }

}