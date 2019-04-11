package com.xabe.orika.impl;

import com.xabe.orika.BeanMapper;
import com.xabe.orika.MapperComponent;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MapperComponentImpl implements MapperComponent {

    protected final BeanMapper mapper;

    @Autowired
    public MapperComponentImpl(BeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> List<T> mapList(List<?> source, Class<T> clazz) {
        return CollectionUtils.isEmpty(source) ? new ArrayList<>() : this.mapper.mapList(source, clazz);
    }

    @Override
    public <T> T map(Object source, Class<T> clazz) {
        return Objects.isNull(source) ? null : mapper.map( source, clazz );
    }

    @Override
    public <T> void map(Object source, T clazz) {
        if (Objects.nonNull (source)){
            mapper.map( source, clazz );
        }
    }

}
