package com.xabe.mapstruct.mapper.config;

import com.xabe.mapstruct.mapper.OptionalMapper;
import org.mapstruct.MapperConfig;

@MapperConfig(uses = {OptionalMapper.class})
public interface MortgageMapperConfig extends ProductMapperConfig {

}
