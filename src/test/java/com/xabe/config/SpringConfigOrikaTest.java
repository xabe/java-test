package com.xabe.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SpringConfigOrikaTest {

  @Autowired
  private Environment environment;

  @Test
  public void shouldLoadApplicationConf() throws Exception {
    //Given
    final ConfigurableEnvironment configurableEnvironment = StandardEnvironment.class.cast(this.environment);
    final MutablePropertySources propertySources = configurableEnvironment.getPropertySources();

    //When
    final String result = this.environment.getProperty("config.conf");
    final String fake = this.environment.getProperty("fake");
    final Optional<PropertySource<?>> propertySource =
        propertySources.stream().filter(item -> "typeSafe".equals(item.getName())).findFirst();

    //Then
    assertThat(result, is(notNullValue()));
    assertThat(result, is("true"));
    assertThat(propertySource.isPresent(), is(true));
    assertThat(fake, is(nullValue()));
  }
}
