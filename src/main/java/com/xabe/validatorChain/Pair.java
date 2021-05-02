package com.xabe.validatorChain;

public interface Pair<T, R> {

  boolean isMatching(Case<Integer, String> integerStringCase);
}
