package com.xabe.validatorChain;

import com.xabe.combiner.User;
import com.xabe.combiner.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ValidatorChainTest {
	
	@Test
	public void givenAUserWhenInvokeValidateThenReturnListEmptyErros() throws Exception {
		//Given
		final User user = new User("user", "email@gmail.com");
		
		//When
		final Validator<User> validatorName = getValidatorName();
		final List<ValidationResult> results = ValidatorChain.use(validatorName).validate(user);
		
		
		//Then
		assertThat(results, is(  hasSize(0) ));
	}
	
	@Test
	public void givenAUserWhenInvokeValidateThenReturnListErros() throws Exception {
		//Given
		final User user = new User("", "email@gmail.com");
		
		//When
		final Validator<User> validatorName = getValidatorName();
		final List<ValidationResult> results = ValidatorChain.use(validatorName).validate(user);
		
		
		//Then
		
		assertThat(results, is(  hasSize(1) ));
		assertThat(results.get(0).getReason().get(), is( "Error name"  ));
	}
	
	@Test
	public void givenAUserWhenInvokeTwoValidateThenReturnListErros() throws Exception {
		//Given
		final User user = new User("", "");
		
		//When
		final Validator<User> validatorName = getValidatorName();
		final Validator<User> validatorEmail = getValidatorEmail();
		final List<ValidationResult> results = ValidatorChain.use(validatorName).then( validatorEmail ).validate(user);
		
		
		//Then
		
		assertThat(results, is(  hasSize(2) ));

	}

	private Validator<User> getValidatorEmail() {
		return user -> StringUtils.isNotBlank(user.getEmail())  ? ValidationResult.DEFAULT_VALIDATE : ValidationResult.invalid("Error mail") ;
	}

	private Validator<User> getValidatorName() {
		return user -> StringUtils.isNotBlank(user.getName())  ? ValidationResult.DEFAULT_VALIDATE : ValidationResult.invalid("Error name") ;
	}

}
