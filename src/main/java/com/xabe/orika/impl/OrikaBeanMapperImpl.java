package com.xabe.orika.impl;

import com.xabe.orika.BeanMapper;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrikaBeanMapperImpl implements BeanMapper {

  protected final MapperFacade mapperFacade;

  @Autowired
  public OrikaBeanMapperImpl(final MapperFacade mapperFacade) {
    this.mapperFacade = mapperFacade;
  }

  @Override
  public <T> List<T> mapList(final List<?> source, final Class<T> clazz) {
    return this.mapperFacade.mapAsList(source, clazz);
  }

  @Override
  public <T> T map(final Object source, final Class<T> clazz) {
    return this.mapperFacade.map(source, clazz);
  }

  @Override
  public <T> void map(final Object source, final T dest) {
    this.mapperFacade.map(source, dest);
  }
}