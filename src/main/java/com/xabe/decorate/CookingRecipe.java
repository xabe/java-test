package com.xabe.decorate;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface CookingRecipe {

  default List<String> add(final String item) {
    final ArrayList<String> ingredients = new ArrayList<>(this.ingredients());
    ingredients.add(item);
    return ingredients;
  }

  List<String> ingredients();

  static CookingRecipe withSalt(final CookingRecipe cookingRecipe) {
    return () -> cookingRecipe.add("Salt");
  }

  static CookingRecipe withMilk(final CookingRecipe cookingRecipe) {
    return () -> cookingRecipe.add("Milk");
  }

  static CookingRecipe withVanillaAlmondExtract(final CookingRecipe cookingRecipe) {
    return () -> cookingRecipe.add("Vanilla/Almond Extract");
  }
}
