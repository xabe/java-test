package com.xabe.visitor;

public class LocationRequestExecutor implements Visitable<LocationVisitor> {

  private final Legacy legacy;

  public LocationRequestExecutor(final Legacy legacy) {
    this.legacy = legacy;
  }

  @Override
  public void accept(final LocationVisitor visitor) {
    visitor.visit(this);
  }

  public void someBusiness() {
    System.out.println("LocationRequestExecutor some business");
    this.legacy.location();
  }
}
