package com.xabe.mapstruct.mapper;

import com.xabe.mapstruct.dto.CarTypeDTO;
import com.xabe.mapstruct.dto.CardDTO;
import com.xabe.mapstruct.entity.CarTypeDO;
import com.xabe.mapstruct.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

@Mapper
public interface CardMapper {

  CardDTO toCarDTO(Card card);

  @ValueMapping(source = "CREDIT", target = "CREDIT")
  @ValueMapping(source = "DEBIT", target = "DEBIT")
  @ValueMapping(source = MappingConstants.ANY_REMAINING, target = "DEBIT")
  @ValueMapping(source = MappingConstants.NULL, target = "DEBIT")
  CarTypeDTO toCarTypeDTO(CarTypeDO carTypeDO);

}
