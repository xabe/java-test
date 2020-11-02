package com.xabe.mapstruct.mapper;

import com.xabe.mapstruct.dto.SavingAccountDTO;
import com.xabe.mapstruct.entity.SavingAccount;
import com.xabe.mapstruct.mapper.config.SavingAccountMapperConfig;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", config = SavingAccountMapperConfig.class)
public interface SavingAccountMapper {

  @InheritConfiguration(name = "mapAccount")
  SavingAccountDTO toSavingAccountDTO(SavingAccount savingAccount);

}
