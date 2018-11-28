package com.xabe.builder;

public class InitialValueBuilder <K> {

    private final K key;

    public InitialValueBuilder(K key) {
        this.key = key;
    }

    public <V> KeyBuilder<K, V> value(V value) {
        return new Maps<K, V>().key(key).value(value);
    }
}
