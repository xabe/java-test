package com.xabe.visitor;

public interface Visitable<T extends Visitor> {

  void accept(T visitor);

}
