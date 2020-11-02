package com.xabe.mapstruct.mapper.config;

import com.xabe.mapstruct.mapper.CardMapper;
import com.xabe.mapstruct.mapper.OptionalMapper;
import org.mapstruct.MapperConfig;

@MapperConfig(uses = {CardMapper.class, OptionalMapper.class})
public interface PayrollAccountMapperConfig extends AccountMapperConfig {

}
