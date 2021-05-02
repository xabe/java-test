package com.xabe.completablefuture.dto;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductFactory {

  private static final Map<ProductType, ProductStrategyEnum>
      map = Arrays.stream(ProductStrategyEnum.values()).collect(Collectors.toMap(ProductStrategyEnum::getType, Function.identity()));

  public static Optional<Product> createProduct(final ProductType type) {
    final ProductStrategyEnum productStrategy = map.get(type);
    if (Objects.isNull(productStrategy)) {
      return Optional.empty();
    }
    return Optional.of(productStrategy.create());
  }

}
