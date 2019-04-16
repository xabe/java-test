package com.xabe.config;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SpringConfigTest {

    @Autowired
    private Environment environment;

    @Test
    public void shouldLoadApplicationConf() throws Exception {
        //Given
        final ConfigurableEnvironment configurableEnvironment = StandardEnvironment.class.cast(environment);
        final MutablePropertySources propertySources = configurableEnvironment.getPropertySources();


        //When
        final String result = environment.getProperty("config.conf");
        final String fake = environment.getProperty("fake");
        final Optional<PropertySource<?>> propertySource = propertySources.stream().filter(item -> "typeSafe".equals(item.getName())).findFirst();

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result, is("true"));
        assertThat(propertySource.isPresent(),is(true));
        assertThat(fake, is(nullValue()));
    }
}
