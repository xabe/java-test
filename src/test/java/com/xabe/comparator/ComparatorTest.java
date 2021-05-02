package com.xabe.comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class ComparatorTest {

  private final Person michael = new Person("Michael", "Jackson", 51);

  private final Person rod = new Person("Rod", "Stewart", 71);

  private final Person paul = new Person("Paul", "McCartney", 74);

  private final Person mick = new Person("Mick", "Jagger", 73);

  private final Person jermaine = new Person("Jermaine", "Jackson", 61);

  @Test
  public void givenATwoPersonWhenInvokeCompareThenReturnInteger() throws Exception {
    //Given
    final Comparator<Person> comparator = Comparator.comparing(Person::getName);

    //When
    final Comparator<Person> cmpNull = Comparator.nullsLast(comparator);

    //Then
    assertThat(cmpNull, is(notNullValue()));
    assertThat(cmpNull.compare(this.michael, this.rod), is(lessThan(0)));
    assertThat(cmpNull.compare(this.paul, this.paul), is(equalTo(0)));
    assertThat(cmpNull.compare(this.michael, this.jermaine), is(greaterThan(0)));
    assertThat(cmpNull.compare(this.mick, null), is(lessThan(0)));
    assertThat(cmpNull.compare(null, this.mick), is(greaterThan(0)));
  }


}
