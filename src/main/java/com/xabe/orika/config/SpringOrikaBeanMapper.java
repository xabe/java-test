package com.xabe.orika.config;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringOrikaBeanMapper extends ConfigurableMapper implements ApplicationContextAware {

  private MapperFactory factory;

  private ApplicationContext applicationContext;

  public SpringOrikaBeanMapper() {
    super(false);
  }

  @Override
  protected void configure(final MapperFactory factory) {
    this.factory = factory;
    this.addAllSpringBeans();
  }

  @Override
  protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
    super.configureFactoryBuilder(factoryBuilder);
    factoryBuilder.useAutoMapping(true).useBuiltinConverters(true);
  }

  @Override
  public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    this.init();
  }

  private void addAllSpringBeans() {
    this.applicationContext.getBeansOfType(Converter.class).values().forEach(this::addConverter);
    this.applicationContext.getBeansOfType(Mapper.class).values().forEach(this::addMapper);
  }

  public void addConverter(final Converter<?, ?> converter) {
    this.factory.getConverterFactory().registerConverter(converter);
  }

  public void addMapper(final Mapper<?, ?> mapper) {
    this.factory.classMap(mapper.getAType(), mapper.getBType())
        .byDefault()
        .customize((Mapper) mapper)
        .register();
  }
}