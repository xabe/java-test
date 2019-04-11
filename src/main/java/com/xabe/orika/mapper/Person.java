package com.xabe.orika.mapper;

import java.time.LocalDate;

public class Person {


    private final String name;
    private final String surname;
    private final LocalDate birthdate;

    public Person(String name, String surname, LocalDate birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

}
