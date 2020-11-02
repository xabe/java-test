package com.xabe.mapstruct.mapper.config;

import com.xabe.mapstruct.dto.ProductDTO;
import com.xabe.mapstruct.entity.Product;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface ProductMapperConfig {

  @Mapping(source = "surname2", target = "surname2", qualifiedByName = "unwrap")
  void mapProduct(Product product, @MappingTarget ProductDTO productDTO);

}
