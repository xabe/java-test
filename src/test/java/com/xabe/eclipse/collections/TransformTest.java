package com.xabe.eclipse.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Test;

public class TransformTest {

  @Test
  public void shouldTransformAddressCollect() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Function<Person, Address> personAddressFunction = Person::getAddress;

    //When
    final MutableList<Address> personMutable = mutableList.collect(personAddressFunction);
    final ImmutableList<Address> personImmutable = immutableList.collect(personAddressFunction);
    final LazyIterable<Address> personLazy = mutableList.asLazy().collect(personAddressFunction);
    final ParallelListIterable<Address> personParallel = mutableList.asParallel(executor, 2).collect(personAddressFunction);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personMutable.size(), is(5));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.size(), is(5));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.toList().size(), is(5));
    assertThat(personParallel, is(notNullValue()));
    assertThat(personParallel.toList().size(), is(5));
  }

  @Test
  public void shouldTransformAddressFlatCollect() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Function<Person, Iterable<Address>> personAddressFunction = item -> List.of(item.getAddress());

    //When
    final MutableList<Address> personMutable = mutableList.flatCollect(personAddressFunction);
    final ImmutableList<Address> personImmutable = immutableList.flatCollect(personAddressFunction);
    final LazyIterable<Address> personLazy = mutableList.asLazy().flatCollect(personAddressFunction);
    final ParallelListIterable<Address> personParallel = mutableList.asParallel(executor, 2).flatCollect(personAddressFunction);

    //Then
    assertThat(personMutable, is(notNullValue()));
    assertThat(personMutable.size(), is(5));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.size(), is(5));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.toList().size(), is(5));
    assertThat(personParallel, is(notNullValue()));
    assertThat(personParallel.toList().size(), is(5));
  }

  @Test
  public void shouldTransformAddressGroupBy() throws Exception {
    //Given
    final ExecutorService executor = Executors.newWorkStealingPool();
    final MutableList<Person> mutableList = Lists.mutable.withAll(PersonMother.createPersons());
    final ImmutableList<Person> immutableList = Lists.immutable.withAll(PersonMother.createPersons());
    final Function<Person, Iterable<Address>> personAddressFunction = item -> List.of(item.getAddress());

    //When
    final MutableListMultimap<Iterable<Address>, Person> personMutable = mutableList.groupBy(personAddressFunction);
    final ImmutableListMultimap<Iterable<Address>, Person> personImmutable = immutableList.groupBy(personAddressFunction);
    final Multimap<Iterable<Address>, Person> personLazy = mutableList.asLazy().groupBy(personAddressFunction);
    final ListMultimap<Iterable<Address>, Person> personParallel = mutableList.asParallel(executor, 2).groupBy(personAddressFunction);

    //Then
    assertThat(personMutable, is(notNullValue()));
    final List<Address> key = List.of(Address.builder().country("Madrid").street("Calle Alcala").build());
    assertThat(personMutable.get(key).size(), is(2));
    assertThat(personImmutable, is(notNullValue()));
    assertThat(personImmutable.get(key).size(), is(2));
    assertThat(personLazy, is(notNullValue()));
    assertThat(personLazy.get(key).size(), is(2));
    assertThat(personParallel, is(notNullValue()));
    assertThat(personParallel.get(key).size(), is(2));
  }

}