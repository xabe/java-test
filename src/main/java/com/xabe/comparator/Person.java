package com.xabe.comparator;

import com.xabe.builder.N;
import com.xabe.builder.PersonBuilder;
import com.xabe.builder.Y;
import java.util.function.Function;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person {

  private final String name;

  private final String surname;

  private final int age;

  public Person(final String name, final String surname, final int age) {
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  public Person(final PersonBuilder<Y, Y> personBuilder) {
    this.age = personBuilder.getAge();
    this.name = personBuilder.getName();
    this.surname = personBuilder.getSurname();
  }

  public Person(final Function<PersonBuilder<N, N>, PersonBuilder<Y, Y>> buildFn) {
    this(buildFn.apply(new PersonBuilder<>() {
    }));
  }

  public String getName() {
    return this.name;
  }

  public String getSurname() {
    return this.surname;
  }

  public int getAge() {
    return this.age;
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof Person) {
      final Person person = (Person) o;
      return new EqualsBuilder()
          .append(this.age, person.age)
          .append(this.name, person.name)
          .append(this.surname, person.surname)
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(this.name)
        .append(this.surname)
        .append(this.age)
        .toHashCode();
  }
}
