package com.xabe.orika.dto;

import java.time.LocalDate;

public class UserDTO {

  private final String name;

  private final String surname;

  private final LocalDate birthdate;

  public UserDTO(final String name, final String surname, final LocalDate birthdate) {
    this.name = name;
    this.surname = surname;
    this.birthdate = birthdate;
  }

  UserDTO(final Person person) {
    this.birthdate = person.getBirthdate();
    this.name = person.getName();
    this.surname = person.getSurname();
  }

  public static UserDTO of(final Person person) {
    return new UserDTO(person);
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