package com.xabe.mapstruct.mapper;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface OptionalMapper {

  @Named("unwrap")
  default <T> T unwrap(final Optional<T> optional) {
    return optional.orElse(null);
  }
}
