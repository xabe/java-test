package com.xabe.builder;

public class ShapeBuilder<T extends ShapeBuilder> {

  protected String name;

  ShapeBuilder() {
  }

  public static ShapeBuilder<?> builder() {
    return new ShapeBuilder<>();
  }

  public T withName(final String name) {
    this.name = name;
    return (T) this;
  }

  public Shape build() {
    return new Shape(this.name);
  }

}
