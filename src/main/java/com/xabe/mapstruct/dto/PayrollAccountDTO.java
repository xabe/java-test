package com.xabe.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
public class PayrollAccountDTO extends AccountDTO {

  private final CardDTO card;

}
