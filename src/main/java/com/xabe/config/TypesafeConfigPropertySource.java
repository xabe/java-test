package com.xabe.config;

import com.typesafe.config.Config;
import org.springframework.core.env.PropertySource;

public class TypesafeConfigPropertySource extends PropertySource<Config> {

    public TypesafeConfigPropertySource(String name, Config source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String path) {
        return source.hasPath(path) ? source.getAnyRef(path) : null;
    }
}
