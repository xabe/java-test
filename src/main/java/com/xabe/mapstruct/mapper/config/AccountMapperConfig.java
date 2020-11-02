package com.xabe.mapstruct.mapper.config;

import com.xabe.mapstruct.dto.AccountDTO;
import com.xabe.mapstruct.entity.Account;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface AccountMapperConfig extends ProductMapperConfig {

  @InheritConfiguration(name = "mapProduct")
  void mapAccount(Account account, @MappingTarget AccountDTO accountDTO);

}
