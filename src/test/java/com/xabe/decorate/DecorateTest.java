package com.xabe.decorate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class DecorateTest {

  @Test
  public void shouldDecorateCookingRecipe() throws Exception {
    //Given
    final CookingRecipe cookingRecipe = new CakeRecipe(List.of("Flour"));

    //When
    final CookingRecipe result = getCookingRecipeWithExtra(cookingRecipe,
        CookingRecipe::withSalt,
        CookingRecipe::withMilk,
        CookingRecipe::withVanillaAlmondExtract);

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result.ingredients(), is(hasSize(4)));
    assertThat(result.ingredients(), is(contains("Flour", "Salt", "Milk", "Vanilla/Almond Extract")));
  }

  @SafeVarargs
  static CookingRecipe getCookingRecipeWithExtra(final CookingRecipe cookingRecipe,
      final Function<CookingRecipe, CookingRecipe>... ingredients) {
    return Stream.of(ingredients).reduce(Function.identity(), Function::andThen).apply(cookingRecipe);
  }

}
