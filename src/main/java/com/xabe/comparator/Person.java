package com.xabe.comparator;

import com.xabe.builder.N;
import com.xabe.builder.PersonBuilder;
import com.xabe.builder.Y;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.function.Function;

public class Person {
    private final String name;
    private final String surname;
    private final int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(PersonBuilder<Y,Y> personBuilder) {
        this.age = personBuilder.getAge();
        this.name = personBuilder.getName();
        this.surname = personBuilder.getSurname();
    }

    public Person(Function<PersonBuilder<N, N>, PersonBuilder<Y, Y>> buildFn){
        this(buildFn.apply(new PersonBuilder<>(){}));
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
