package com.xabe.mapstruct.dto;

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
public class MortgageDTO extends ProductDTO {

  private final Period duration;

  private final BigDecimal amortizedCapital;

}
