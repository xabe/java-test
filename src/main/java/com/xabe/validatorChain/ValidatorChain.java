package com.xabe.validatorChain;

import com.xabe.combiner.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValidatorChain<T> {

  private final Validator<T> validator;

  private final ValidatorChain<T> validatorChain;

  private ValidatorChain(final Validator<T> validator) {
    this.validator = validator;
    this.validatorChain = null;
  }

  private ValidatorChain(final ValidatorChain<T> validatorChain, final Validator<T> validator) {
    this.validatorChain = validatorChain;
    this.validator = validator;
  }

  public ValidatorChain<T> then(final Validator<T> validator) {
    Objects.requireNonNull(validator, "Not Empty validator");
    return new ValidatorChain<T>(this, validator);
  }

  public List<ValidationResult> validate(final T item) {
    final List<ValidationResult> results = new ArrayList<>();
    if (this.validatorChain != null) {
      results.addAll(this.validatorChain.validate(item));
    }
    results.add(this.validator.validate(item));
    return results.stream().filter(element -> !element.isValid()).collect(Collectors.toList());
  }

  public static <T> ValidatorChain<T> use(final Validator<T> validator) {
    Objects.requireNonNull(validator, "Not Empty validator");
    return new ValidatorChain<T>(validator);
  }

}
