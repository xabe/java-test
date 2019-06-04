package com.xabe.validatorChain;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Pair<T,R> {

    boolean isMatching(Case<Integer, String> integerStringCase);
}
