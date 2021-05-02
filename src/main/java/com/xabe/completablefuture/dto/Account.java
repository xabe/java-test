package com.xabe.completablefuture.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends Product {

  private BigDecimal profit;

  public Account() {
    super(ProductType.ACCOUNT);
  }
}
