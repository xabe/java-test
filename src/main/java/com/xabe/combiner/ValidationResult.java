package com.xabe.combiner;

import java.util.Optional;

public interface ValidationResult {
	
	ValidationResult DEFAULT_VALIDATE = new ValidationResult() {
		
		@Override
		public boolean isValid() {
			return true;
		}
		
		@Override
		public Optional<String> getReason() {
			return Optional.empty();
		}
	};

	boolean isValid();

	Optional<String> getReason();

	static ValidationResult invalid(String reason) {
		return new Invalid(reason);
	}

}
