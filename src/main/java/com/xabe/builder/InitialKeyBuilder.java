package com.xabe.builder;

import java.util.HashMap;
import java.util.Map;

public class InitialKeyBuilder {

    public  <K> InitialValueBuilder<K> key(K k) {
        return new InitialValueBuilder<>(k);
    }

    public <K,V> Map<K, V> build() {
        return new HashMap<>();
    }
}
