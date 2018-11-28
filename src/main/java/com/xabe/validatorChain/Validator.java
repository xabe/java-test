package com.xabe.validatorChain;

import com.xabe.combiner.ValidationResult;

@FunctionalInterface
public interface Validator<T> {
	
	ValidationResult validate(T item);

}
