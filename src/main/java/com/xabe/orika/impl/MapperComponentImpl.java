package com.xabe.orika.impl;

import com.xabe.orika.BeanMapper;
import com.xabe.orika.MapperComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperComponentImpl implements MapperComponent {

  protected final BeanMapper mapper;

  @Autowired
  public MapperComponentImpl(final BeanMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public <T> List<T> mapList(final List<?> source, final Class<T> clazz) {
    return CollectionUtils.isEmpty(source) ? new ArrayList<>() : this.mapper.mapList(source, clazz);
  }

  @Override
  public <T> T map(final Object source, final Class<T> clazz) {
    return Objects.isNull(source) ? null : this.mapper.map(source, clazz);
  }

  @Override
  public <T> void map(final Object source, final T clazz) {
    if (Objects.nonNull(source)) {
      this.mapper.map(source, clazz);
    }
  }
}