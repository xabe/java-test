package com.xabe.completablefuture.dto;

import com.xabe.mapstruct.dto.CarTypeDTO;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Card extends Product {

  private String id;

  private String name;

  private LocalDate expired;

  private CarTypeDTO type;

  public Card() {
    super(ProductType.CARD);
  }
}
