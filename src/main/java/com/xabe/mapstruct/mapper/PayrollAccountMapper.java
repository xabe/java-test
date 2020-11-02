package com.xabe.mapstruct.mapper;

import com.xabe.mapstruct.dto.PayrollAccountDTO;
import com.xabe.mapstruct.entity.PayrollAccount;
import com.xabe.mapstruct.mapper.config.PayrollAccountMapperConfig;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", config = PayrollAccountMapperConfig.class)
public interface PayrollAccountMapper {

  @InheritConfiguration(name = "mapAccount")
  PayrollAccountDTO toPayrollAccountDTO(PayrollAccount payrollAccount);
}
