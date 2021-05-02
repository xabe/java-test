package com.xabe.completablefuture.dto;

import java.math.BigDecimal;
import java.time.Period;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Loan extends Product {

  private Period duration;

  private BigDecimal amortizedCapital;

  public Loan() {
    super(ProductType.LOAN);
  }
}
