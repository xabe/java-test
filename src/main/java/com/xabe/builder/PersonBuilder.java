package com.xabe.builder;

public abstract class PersonBuilder <Name extends IsSpecified,Surname extends IsSpecified> {

    private String name;
    private String surname;
    private int age;

    public PersonBuilder(){};

    public static PersonBuilder<N,N> builder() {
        return new PersonBuilder<N,N>(){};
    }

    public PersonBuilder<Y,Surname> withName(String name) {
        this.name = name;
        return (PersonBuilder<Y, Surname>) this;
    }

    public PersonBuilder<Name,Y> withSurname(String surname) {
        this.surname = surname;
        return (PersonBuilder<Name, Y>) this;
    }

    public PersonBuilder<Name,Surname> withAge(int age) {
        this.age = age;
        return this;
    }

  //  public Person build() {
  //      return new Person((PersonBuilder<Y, Y>) this);
  //  }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
