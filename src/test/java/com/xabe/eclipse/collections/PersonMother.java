package com.xabe.eclipse.collections;

import java.util.List;

public class PersonMother {

  public static Iterable<Person> createPersons() {
    return List.of(Person.builder().id("1").name("Camilo").surname("Haydée").age(18)
            .address(Address.builder().country("Madrid").number(10).street("Calle Alcala").build()).build(),
        Person.builder().id("2").name("Paula").surname("Robles").age(38)
            .address(Address.builder().country("Madrid").number(12).street("Calle Alcala").build()).build(),
        Person.builder().id("3").name("Rosalinda").surname("Garcia").age(16)
            .address(Address.builder().country("Mostoles").number(1).street("Avenida portugal").build()).build(),
        Person.builder().id("4").name("Ovidio").surname("Perez").age(28)
            .address(Address.builder().country("Madrid").number(100).street("Paseo castellana").build()).build(),
        Person.builder().id("5").name("Gaspar").surname("Sanchez").age(14)
            .address(Address.builder().country("Getafe").number(13).street("Calle Madrid").build()).build());
  }

  public static Person createPerson() {
    return Person.builder().id("1").name("Camilo").surname("Haydée").age(18)
        .address(Address.builder().country("Madrid").number(10).street("Calle Alcala").build()).build();
  }

  public static Person createPerson2() {
    return Person.builder().id("2").name("Paula").surname("Haydée").age(18)
        .address(Address.builder().country("Madrid").number(10).street("Calle Alcala").build()).build();
  }
}
