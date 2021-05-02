package com.xabe.builder;

public abstract class PersonBuilder<Name extends IsSpecified, Surname extends IsSpecified> {

  private String name;

  private String surname;

  private int age;

  public PersonBuilder() {
  }

  ;

  public static PersonBuilder<N, N> builder() {
    return new PersonBuilder<N, N>() {
    };
  }

  public PersonBuilder<Y, Surname> withName(final String name) {
    this.name = name;
    return (PersonBuilder<Y, Surname>) this;
  }

  public PersonBuilder<Name, Y> withSurname(final String surname) {
    this.surname = surname;
    return (PersonBuilder<Name, Y>) this;
  }

  public PersonBuilder<Name, Surname> withAge(final int age) {
    this.age = age;
    return this;
  }

  //  public Person build() {
  //      return new Person((PersonBuilder<Y, Y>) this);
  //  }

  public int getAge() {
    return this.age;
  }

  public String getName() {
    return this.name;
  }

  public String getSurname() {
    return this.surname;
  }
}
