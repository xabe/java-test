package com.xabe.builder;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Maps <K,V> implements KeyBuilder<K, V>, ValueBuilder<K, V>{

    private final List<Map.Entry<K, V>> entries;
    private K lastKey;

    protected Maps() {
        this.entries = new ArrayList<>();
    }

    public static InitialKeyBuilder  builder() {
        return new InitialKeyBuilder();
    }

    public ValueBuilder<K,V> key(K key) {
        this.lastKey = key;
        return (ValueBuilder<K, V>) this;
    }

    @Override
    public KeyBuilder<K, V> value(V v) {
        entries.add(new AbstractMap.SimpleEntry<>(lastKey, v));
        return (KeyBuilder<K, V>) this;
    }

    @Override
    public Map<K, V> build() {
        return entries.stream()
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
