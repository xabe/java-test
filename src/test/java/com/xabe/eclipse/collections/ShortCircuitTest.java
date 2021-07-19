package com.xabe.eclipse.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Test;

public class ShortCircuitTest {

  @Test
  public void shouldShortCircuitDetect() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> predicate = person -> person.getAge() >= 30;

    //When
    final Person personMutable = mutableList.detect(predicate);
    final Person personImmutable = immutableList.detect(predicate);
    final Person personLazy = mutableList.asLazy().detect(predicate);
    final Person personParallel = mutableList.asParallel(executor, 2).detect(predicate);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personParallel, is(notNullValue()));
  }

  @Test
  public void shouldShortCircuitAnySatisfy() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> predicate = person -> person.getAge() >= 30;

    //When
    final boolean personMutable = mutableList.anySatisfy(predicate);
    final boolean personImmutable = immutableList.anySatisfy(predicate);
    final boolean personLazy = mutableList.asLazy().anySatisfy(predicate);
    final boolean personParallel = mutableList.asParallel(executor, 2).anySatisfy(predicate);

    //Then
    assertThat(personMutable, is(true));
    assertThat(personImmutable, is(true));
    assertThat(personLazy, is(true));
    assertThat(personParallel, is(true));
  }

  @Test
  public void shouldShortCircuitAllSatisfy() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> predicate = person -> person.getAge() >= 1;

    //When
    final boolean personMutable = mutableList.allSatisfy(predicate);
    final boolean personImmutable = immutableList.allSatisfy(predicate);
    final boolean personLazy = mutableList.asLazy().allSatisfy(predicate);
    final boolean personParallel = mutableList.asParallel(executor, 2).anySatisfy(predicate);

    //Then
    assertThat(personMutable, is(true));
    assertThat(personImmutable, is(true));
    assertThat(personLazy, is(true));
    assertThat(personParallel, is(true));
  }

  @Test
  public void shouldShortCircuitNoneSatisfy() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> predicate = person -> person.getAge() < 1;

    //When
    final boolean personMutable = mutableList.noneSatisfy(predicate);
    final boolean personImmutable = immutableList.noneSatisfy(predicate);
    final boolean personLazy = mutableList.asLazy().noneSatisfy(predicate);
    final boolean personParallel = mutableList.asParallel(executor, 2).noneSatisfy(predicate);

    //Then
    assertThat(personMutable, is(true));
    assertThat(personImmutable, is(true));
    assertThat(personLazy, is(true));
    assertThat(personParallel, is(true));
  }

}
