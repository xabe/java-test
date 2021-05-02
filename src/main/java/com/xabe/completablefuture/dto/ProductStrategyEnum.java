package com.xabe.completablefuture.dto;

import java.util.function.Supplier;

public enum ProductStrategyEnum {
  CARD(ProductType.CARD, Card::new),
  LOAN(ProductType.LOAN, Loan::new),
  MORTGAGE(ProductType.MORTGAGE, Mortgage::new),
  ACCOUNT(ProductType.ACCOUNT, Account::new);

  private final ProductType type;

  private final Supplier<Product> constructor;

  ProductStrategyEnum(final ProductType type, final Supplier<Product> constructor) {
    this.type = type;
    this.constructor = constructor;
  }

  public ProductType getType() {
    return this.type;
  }

  public Product create() {
    return this.constructor.get();
  }
}
