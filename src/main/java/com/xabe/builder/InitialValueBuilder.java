package com.xabe.builder;

public class InitialValueBuilder<K> {

  private final K key;

  public InitialValueBuilder(final K key) {
    this.key = key;
  }

  public <V> KeyBuilder<K, V> value(final V value) {
    return new Maps<K, V>().key(this.key).value(value);
  }
}
