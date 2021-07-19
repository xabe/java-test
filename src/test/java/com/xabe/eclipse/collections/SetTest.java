package com.xabe.eclipse.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Set;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.ImmutableSet;
import org.junit.jupiter.api.Test;

public class SetTest {

  @Test
  void shouldUnionSets() {
    //Given
    final ImmutableSet<Integer> set1 = Sets.immutable.of(1, 2, 3, 4, 5);
    final ImmutableSet<Integer> set2 = Sets.immutable.of(6, 7, 8, 9, 10);

    //When
    final ImmutableSet<Integer> union = set1.union(set2);

    //Then
    final Set<Integer> result = union.castToSet();
    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasSize(10)));
  }

  @Test
  void shouldDifferenceSets() {
    //Given
    final ImmutableSet<Integer> set1 = Sets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    final ImmutableSet<Integer> set2 = Sets.immutable.of(2, 4, 6, 8, 10);

    //When
    final ImmutableSet<Integer> difference = set1.difference(set2);

    //Then
    final Set<Integer> result = difference.castToSet();
    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasItems(1, 3, 5, 7, 9)));
  }

  @Test
  void ShouldIntersectionSets() {
    //Given
    final ImmutableSet<Integer> set1 = Sets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    final ImmutableSet<Integer> set2 = Sets.immutable.of(2, 4, 6, 8, 10);

    //When
    final ImmutableSet<Integer> intersection = set1.intersect(set2);

    //Then
    final Set<Integer> result = intersection.castToSet();
    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasItems(2, 4, 6, 8, 10)));
  }

  @Test
  void shouldDisjointSets() {
    //Given
    final ImmutableSet<Integer> set1 = Sets.immutable.of(1, 2, 3, 4, 5);
    final ImmutableSet<Integer> set2 = Sets.immutable.of(6, 7, 8, 9, 10);

    //When
    final boolean isDisjoint = !set1.containsAllIterable(set2);

    //Then
    assertThat(isDisjoint, is(true));
  }

}
