package com.xabe.eclipse.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.junit.jupiter.api.Test;

public class MapTest {

  @Test
  void shouldInsertMap() {
    //Given
    final Person person = PersonMother.createPerson();
    final Person person2 = PersonMother.createPerson2();

    //When
    final ImmutableMap<Person, List<String>> courses = Maps.immutable.of(person, List.of("Principles of Management"));
    final ImmutableMap<Person, List<String>> courses2 = courses.newWithKeyValue(person2, List.of("International Law"));

    //Then
    assertThat(courses.size(), is(1));
    assertThat(courses2.size(), is(2));
  }

  @Test
  void shouldGetValueByKey() {
    //Given
    final Person person = PersonMother.createPerson();
    final Person person2 = PersonMother.createPerson2();

    //When
    final ImmutableMap<Person, List<String>> courses = Maps.immutable.of(person, List.of("Principles of Management"));
    final List<String> andrea = courses.get(person);
    final List<String> barbora = courses.getIfAbsentValue(person2, andrea);

    //Then
    assertThat(courses.size(), is(1));
    assertThat(barbora, is(notNullValue()));
    assertThat(barbora, is(andrea));
  }

  @Test
  void shouldUpdateValue() {
    //Given
    final Person person = PersonMother.createPerson();

    //When
    final MutableMap<Person, List<String>> courses = Maps.mutable.of(person, Lists.mutable.of("Principles of Management"));
    courses.ifPresentApply(person, l -> l.add("C++ Programming"));

    //Then
    assertThat(courses.size(), is(1));
    assertThat(courses.get(person).size(), is(2));
  }

  @Test
  void shouldDelete() {
    //Given
    final Person person = PersonMother.createPerson();
    final Person person2 = PersonMother.createPerson2();

    final Map<Person, List<String>> courses = Map.of(
        person, List.of("Algebra", "Introduction to Management", "History 101"),
        person2, List.of("International Law", "European Art 201"));

    final ImmutableMap<Person, List<String>> coursesEclipse = Maps.immutable.ofMap(courses);

    //When
    final ImmutableMap<Person, List<String>> result = coursesEclipse.newWithoutKey(person2);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.keysView().size(), is(1));
  }

}
