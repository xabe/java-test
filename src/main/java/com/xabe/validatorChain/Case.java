package com.xabe.validatorChain;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Case <T, R> {

    private final Predicate<T> predicate;
    private final Function<T, R> value;

    public Case(Predicate<T> predicate, Function<T, R> value) {
        this.predicate = predicate;
        this.value = value;
    }

    public boolean isMatching(T item) {
        return predicate.test(item);
    }

    public R result(T item) {
        return value.apply(item);
    }

    public static <T,R> Case<T, R> matchCase(Predicate<T> predicate, Function<T,R> value){
        return new Case<>(predicate, value);
    }

    public static <T,R> Case<T, R> matchCase(Function<T,R> value){
        return new Case<>(item -> true, value);
    }

    public static <T,R> R match(T item, Case<T, R> defaultCase, Case<T, R> ...matches) {
        return Stream.of(matches).
                filter(match -> match.isMatching(item)).
                map( match -> match.result(item)).
                findFirst().
                orElseGet(() -> defaultCase.result(item));
    }
}
