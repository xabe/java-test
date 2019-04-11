package com.xabe.orika.mapper;

import java.time.LocalDate;

public class UserDTO {

    private final String name;
    private final String surname;
    private final LocalDate birthdate;

    public UserDTO(String name, String surname, LocalDate birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    UserDTO(Person person) {
        this.birthdate = person.getBirthdate();
        this.name = person.getName();
        this.surname = person.getSurname();
    }

    public static UserDTO of(Person person) {
        return new UserDTO(person);
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
