package com.xabe.combiner;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public interface UserValidation extends Function<User, ValidationResult> {

	static UserValidation nameIsNotEmpty() {
		return user -> StringUtils.isNotBlank(user.getName()) ? ValidationResult.DEFAULT_VALIDATE
				: ValidationResult.invalid("Name is empty.");
	}

	static UserValidation emailIsCorrect() {
		return user -> user.getEmail().indexOf("@") != -1 ? ValidationResult.DEFAULT_VALIDATE : ValidationResult.invalid("Error");
	}

	default UserValidation andThen(UserValidation after) {
		return user -> {
			final ValidationResult result = this.apply(user);
			return result.isValid() ? after.apply(user) : result;
		};
	}

	static UserValidation all(UserValidation... validations) {
		return user -> {
			final String reasons = Arrays.stream(validations).map(v -> v.apply(user)).filter(r -> !r.isValid())
					.map(r -> r.getReason().get()).collect(Collectors.joining("\n"));
			return reasons.isEmpty() ? ValidationResult.DEFAULT_VALIDATE : ValidationResult.invalid(reasons);
		};
	}

}
