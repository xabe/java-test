package com.xabe.mapstruct.mapper;

import com.xabe.mapstruct.dto.LoanDTO;
import com.xabe.mapstruct.entity.Loan;
import com.xabe.mapstruct.mapper.config.LoanMapperConfig;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", config = LoanMapperConfig.class)
public interface LoanMapper {

  @InheritConfiguration(name = "mapProduct")
  LoanDTO toLoanDTO(Loan loan);

}
