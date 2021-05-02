package com.xabe.completablefuture.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public abstract class Product {

  private ProductType productType;

  private String id;

  private String name;

  private String surname;

  private String surname2;

  private LocalDate birthDate;

  private BigDecimal amount;

  public Product(final ProductType productType) {
    this.productType = productType;
  }
}
