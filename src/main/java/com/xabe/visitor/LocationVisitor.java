package com.xabe.visitor;

public interface LocationVisitor extends Visitor {

  void visit(LocationRequestExecutor locationRequestExecutor);
}
