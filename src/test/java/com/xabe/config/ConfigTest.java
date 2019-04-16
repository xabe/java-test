package com.xabe.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ConfigTest {

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("property.override","override");
        System.setProperty("dir.config","/tmp/override");
        final String path = ConfigTest.class.getClassLoader().getResource("application.conf").getPath();
        final String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        final String parent = new File(decodePath).getParent();
        final String decode = URLDecoder.decode( parent, StandardCharsets.UTF_8.name());
        // System.setProperty("config.file",decodePath);
    }

    @Test
    public void shouldLoadDefaultFileApplicationConf() throws Exception {
        //Given
        final Config config = ConfigFactory.load();

        //When
        final Boolean value = config.getBoolean("config.conf");
        final String override = config.getString("property.override");
        final long durationMinutes = config.getDuration("cassandra.ttl", TimeUnit.MINUTES);
        final long durationSeconds = config.getDuration("cassandra.ttl", TimeUnit.SECONDS);
        final String dir = config.getString("some.dir");
        final Properties properties = getProperties(config.getConfig("pool.default"));


        //Then
        assertThat(value, is(notNullValue()));
        assertThat(value, is(true));
        assertThat(override, is("override"));
        assertThat(durationMinutes, is(5l));
        assertThat(durationSeconds, is(300l));
        assertThat(dir,is("/tmp/override/file.txt"));
        assertThat(properties,is(notNullValue()));
        assertThat(properties.size(),is(9));
    }

    @Test
    public void shouldLoadDefaultFileApplicationProperties() throws Exception {
        //Given
        final Config config = ConfigFactory.load();

        //When
        final Boolean value = config.getBoolean("config.properties");

        //Then
        assertThat(value, is(notNullValue()));
        assertThat(value, is(true));
    }

    @Test
    public void shouldLoadDefaultFileApplicationJson() throws Exception {
        //Given
        final Config config = ConfigFactory.load();

        //When
        final Boolean value = config.getBoolean("config.json");

        //Then
        assertThat(value, is(notNullValue()));
        assertThat(value, is(true));
    }


    @Test
    public void shouldLoadFileProperties() throws Exception {
        //Given
        final String path = ConfigTest.class.getClassLoader().getResource("version.properties").getPath();
        final String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        final Config config =  ConfigFactory.load(ConfigFactory.parseFile(new File(decodePath)));

        //When
        final String value = config.getString("version");

        //Then
        assertThat(value, is("1.0.0"));
    }

    @Test
    public void shouldLoadFilePropertiesOverride() throws Exception {
        //Given
        final String path = ConfigTest.class.getClassLoader().getResource("default.properties").getPath();
        final String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        final Config defaultConfig =  ConfigFactory.load(ConfigFactory.parseFile(new File(decodePath)));
        final Config fallbackConfig = ConfigFactory.parseResources("overrides.properties")
                .withFallback(defaultConfig)
                .resolve();



        //When
        final String name = fallbackConfig.getString("conf.name");
        final String title = fallbackConfig.getString("conf.title");

        //Then
        assertThat(name, is("override"));
        assertThat(title, is(notNullValue()));
    }


    private Properties getProperties(com.typesafe.config.Config config) {
        return config.entrySet().stream().collect(Collector.of(
                Properties::new,
                (properties, item) -> properties.put( item.getKey(), item.getValue().unwrapped().toString() ),
                (a, b) -> a,
                Collector.Characteristics.IDENTITY_FINISH));

    }

}
