package com.xabe.comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person {
    private final String name;
    private final String surname;
    private final int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person person = (Person) o;
            return new EqualsBuilder()
                    .append(age, person.age)
                    .append(name, person.name)
                    .append(surname, person.surname)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(surname)
                .append(age)
                .toHashCode();
    }
}
