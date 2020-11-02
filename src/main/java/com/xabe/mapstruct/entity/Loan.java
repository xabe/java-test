package com.xabe.mapstruct.entity;

import java.math.BigDecimal;
import java.time.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor(force = true)
public class Loan extends Product {

  private final Period duration;

  private final BigDecimal amortizedCapital;

}
