package com.xabe.pipe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;



public class PipeTest {
	
	
	@Test
	public void givenAStringWhenInvokeApplyThenReturnNumberWords() throws Exception {
		//Given
		final String value = " hello everybody ";
		
	
		final Map<String, Integer> result = Pipe.from(this::trim).then(this::converterStringArrayString).then(this::converterArrayStringMap).apply(value);
		assertThat(result.get("hello"), is(5));
		assertThat(result.get("everybody"), is(9));
	}
	
	public String[] converterStringArrayString(String value){
		return value.split(" ");
	}
	
	public Map<String, Integer> converterArrayStringMap(String[] values){
		return Arrays.stream(values).collect(Collectors.toMap(Function.identity(), String::length));
	}
	
	public String trim (String item){
		return item.trim();
	}

}
