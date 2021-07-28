package com.xabe.visitor;

public class RequestVisitor implements LocationVisitor, RouteVisitor {

  @Override
  public void visit(final LocationRequestExecutor locationRequestExecutor) {
    System.out.println("This is a LocationRequestExecutor! let's do something with it!");
    locationRequestExecutor.someBusiness();
  }

  @Override
  public void visit(final RouteRequestExecutor routeRequestExecutor) {
    System.out.println("This is a RouteRequestExecutor! let's do something with it!");
    routeRequestExecutor.anyBusiness();
  }

}
