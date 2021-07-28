package com.xabe.visitor;

public interface RouteVisitor extends Visitor {

  void visit(RouteRequestExecutor routeRequestExecutor);

}
