package com.xabe.orika.dto;

import java.time.LocalDate;

public class UserMutableDTO {

  private String name;

  private String surname;

  private LocalDate birthdate;

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthdate() {
    return this.birthdate;
  }

  public void setBirthdate(final LocalDate birthdate) {
    this.birthdate = birthdate;
  }

}