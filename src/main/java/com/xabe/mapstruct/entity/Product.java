package com.xabe.mapstruct.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
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
public abstract class Product {

  private final String id;

  private final String name;

  private final String surname;

  private final String surname2;

  private final LocalDate birthDate;

  private final BigDecimal amount;

  public Optional<String> getSurname2() {
    return Optional.ofNullable(this.surname2);
  }
}
