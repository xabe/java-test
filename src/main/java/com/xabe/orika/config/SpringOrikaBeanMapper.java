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
    protected void configure(MapperFactory factory) {
        this.factory = factory;
        addAllSpringBeans();
    }

    @Override
    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        super.configureFactoryBuilder( factoryBuilder );
        factoryBuilder.useAutoMapping( true ).useBuiltinConverters( true );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.init();
    }

    private void addAllSpringBeans() {
        applicationContext.getBeansOfType( Converter.class ).values().stream().forEach( converter -> addConverter(converter));
        applicationContext.getBeansOfType( Mapper.class ).values().stream().forEach( mapper -> addMapper(mapper));
    }

    public void addConverter(final Converter<?, ?> converter) {
        factory.getConverterFactory().registerConverter( converter );
    }

    public void addMapper(final Mapper<?, ?> mapper) {
        factory.classMap( mapper.getAType(), mapper.getBType() )
                .byDefault()
                .customize( (Mapper) mapper )
                .register();
    }
}