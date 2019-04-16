package com.xabe.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigResolveOptions;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;

public class TypesafePropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<Config> createPropertySource(String name, EncodedResource resource) throws IOException {
        final Config config = ConfigFactory
                .load(resource.getResource().getFilename(),
                        ConfigParseOptions.defaults().setAllowMissing(false),
                        ConfigResolveOptions.noSystem()).resolve();
        final String safeName = name == null ? "typeSafe" : name;
        return new TypesafeConfigPropertySource(safeName, config);
    }
}
