package com.xabe.mapstruct.mapper;

import com.xabe.mapstruct.dto.MortgageDTO;
import com.xabe.mapstruct.entity.Mortgage;
import com.xabe.mapstruct.mapper.config.MortgageMapperConfig;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", config = MortgageMapperConfig.class)
public interface MortgageMapper {

  @InheritConfiguration(name = "mapProduct")
  MortgageDTO toMortgageDTO(Mortgage mortgage);

}
