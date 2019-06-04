package com.xabe.autoclose;

import java.util.function.Consumer;

public class AutoCloser<T> {
    
    private T resource;

    public AutoCloser(T resource) {
        this.resource = resource;
    }

    public AutoClosableSupplier closeWith(Consumer<T> closer){
        return new AutoClosableSupplier(resource, closer);
    }

    public static <T> AutoCloser<T> useResource(T notAutoclosable) {
        return new AutoCloser<T>(notAutoclosable);
    }
}
