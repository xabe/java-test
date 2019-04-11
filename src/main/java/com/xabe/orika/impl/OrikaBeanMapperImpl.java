package com.xabe.orika.impl;

import com.xabe.orika.BeanMapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrikaBeanMapperImpl implements BeanMapper {

    protected final MapperFacade mapper;

    @Autowired
    public OrikaBeanMapperImpl(MapperFacade mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> List<T> mapList(List<?> source, Class<T> clazz) {
        return mapper.mapAsList( source, clazz );
    }

    @Override
    public <T> T map(Object source, Class<T> clazz) {
        return mapper.map( source, clazz );
    }

    @Override
    public <T> void map(Object source, T dest) {
        mapper.map( source, dest );
    }
}
