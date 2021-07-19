package com.xabe.eclipse.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Test;

public class FilterTest {

  @Test
  public void shouldFilterWithSelect() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> personPredicate = person -> person.getAge() >= 18;

    //When
    final MutableList<Person> personMutable = mutableList.select(personPredicate);
    final ImmutableList<Person> personImmutable = immutableList.select(personPredicate);
    final LazyIterable<Person> personLazy = mutableList.asLazy().select(personPredicate);
    final ParallelListIterable<Person> personParallel = mutableList.asParallel(executor, 2).select(personPredicate);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personMutable.size(), is(3));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.size(), is(3));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.toList().size(), is(3));
    assertThat(personParallel, is(notNullValue()));
    assertThat(personParallel.toList().size(), is(3));
  }

  @Test
  public void shouldFilterWithReject() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> personPredicate = person -> person.getAge() >= 18;

    //When
    final MutableList<Person> personMutable = mutableList.reject(personPredicate);
    final ImmutableList<Person> personImmutable = immutableList.reject(personPredicate);
    final LazyIterable<Person> personLazy = mutableList.asLazy().reject(personPredicate);
    final ParallelListIterable<Person> personParallel = mutableList.asParallel(executor, 2).reject(personPredicate);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personMutable.size(), is(2));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.size(), is(2));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.toList().size(), is(2));
    assertThat(personParallel, is(notNullValue()));
    assertThat(personParallel.toList().size(), is(2));
  }

  @Test
  public void shouldFilterWithPartition() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Predicate<Person> personPredicate = person -> person.getAge() >= 18;

    //When
    final PartitionMutableList<Person> personMutable = mutableList.partition(personPredicate);
    final PartitionImmutableList<Person> personImmutable = immutableList.partition(personPredicate);
    final PartitionIterable<Person> personLazy = mutableList.asLazy().partition(personPredicate);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personMutable.getSelected().size(), is(3));
    assertThat(personMutable.getRejected().size(), is(2));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.getSelected().size(), is(3));
    assertThat(personImmutable.getRejected().size(), is(2));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.getSelected().size(), is(3));
    assertThat(personLazy.getRejected().size(), is(2));
  }

}