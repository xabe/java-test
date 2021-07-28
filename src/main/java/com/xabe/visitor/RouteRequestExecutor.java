package com.xabe.visitor;

public class RouteRequestExecutor implements Visitable<RouteVisitor> {

  private final Legacy legacy;

  public RouteRequestExecutor(final Legacy legacy) {
    this.legacy = legacy;
  }

  @Override
  public void accept(final RouteVisitor visitor) {
    visitor.visit(this);
  }

  public void anyBusiness() {
    System.out.println("RouteRequestExecutor any business");
    this.legacy.route();
  }
}
