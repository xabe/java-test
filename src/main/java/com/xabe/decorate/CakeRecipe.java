package com.xabe.decorate;

import java.util.List;

public class CakeRecipe implements CookingRecipe {

  private final List<String> ingredients;

  public CakeRecipe(final List<String> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public List<String> ingredients() {
    return this.ingredients;
  }
}
