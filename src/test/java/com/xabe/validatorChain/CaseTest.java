package com.xabe.validatorChain;

import org.junit.jupiter.api.Test;

import static com.xabe.validatorChain.Case.matchCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CaseTest {
    
    
    @Test
    public void givenAValueWhenInvokeMatchCaseThenReturnString() throws Exception {
        //Given
        final Integer value = 5;
        final Case<Integer,String> older = matchCase(item -> item >= 18, item -> "Older!!");
        final Case<Integer,String> younger = matchCase(item -> item < 18, item -> "younger!!");
        final Case<Integer,String> defaultCase = matchCase(item -> "Error");

        //When
        final String result = Case.match(value,defaultCase,older,younger);
        
        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result, is("younger!!"));
    }

    @Test
    public void givenAValueWhenInvokeMatchCaseThenReturnDefaultValue() throws Exception {
        //Given
        final Integer value = 5;
        final Case<Integer,String> adult = matchCase(item -> item >= 18, item -> "adult!!");
        final Case<Integer,String> older = matchCase(item -> item >= 65, item -> "older!!");
        final Case<Integer,String> defaultCase = matchCase(item -> "young");

        //When
        final String result = Case.match(value,defaultCase,older,adult);

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result, is("young"));
    }
}
