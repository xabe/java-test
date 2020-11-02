package com.xabe.mapstruct.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor(force = true)
public abstract class ProductDTO {

  private final String id;

  private final String name;

  private final String surname;

  private final String surname2;

  private final LocalDate birthDate;

  private final BigDecimal amount;
}
