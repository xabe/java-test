package com.xabe.builder;

public class SquareBuilder extends ShapeBuilder<SquareBuilder> {

  private int edge;

  SquareBuilder() {
  }

  public SquareBuilder withEdge(final int edge) {
    this.edge = edge;
    return this;
  }

  public static SquareBuilder builder() {
    return new SquareBuilder();
  }

  @Override
  public Shape build() {
    return new Square(this.name, this.edge);
  }


}
