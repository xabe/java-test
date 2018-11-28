package com.xabe.builder;

public interface ValueBuilder<K, V> {

    KeyBuilder<K, V> value(V v);
}
