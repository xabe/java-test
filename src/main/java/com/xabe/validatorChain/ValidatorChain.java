package com.xabe.validatorChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.xabe.combiner.User;
import com.xabe.combiner.ValidationResult;

public class ValidatorChain<T> {

	private final Validator<T> validator;
	private final ValidatorChain<T> validatorChain;

	private ValidatorChain(Validator<T> validator) {
		this.validator = validator;
		this.validatorChain = null;
	}

	private ValidatorChain(ValidatorChain<T> validatorChain, Validator<T> validator) {
		this.validatorChain = validatorChain;
		this.validator = validator;
	}

	public ValidatorChain<T> then(Validator<T> validator) {
		Objects.requireNonNull(validator, "Not Empty validator");
		return new ValidatorChain<T>( this, validator );
	}

	public List<ValidationResult> validate(T item) {
		final List<ValidationResult> results = new ArrayList<>();
		if(this.validatorChain != null){
			results.addAll(this.validatorChain.validate(item));
		}
		results.add(this.validator.validate(item));
		return results.stream().filter(element -> !element.isValid() ).collect(Collectors.toList());
	}

	public static <T> ValidatorChain<T> use(Validator<T> validator) {
		Objects.requireNonNull(validator, "Not Empty validator");
		return new ValidatorChain<T>(validator);
	}

}
