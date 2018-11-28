package com.xabe.combiner;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class User {

	private final String name;
	private final String email;

	@Override
	public boolean equals(final Object other) {
		if (other instanceof User) {
			User castOther = (User) other;
			return new EqualsBuilder().append(name, castOther.name).append(email, castOther.email).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(email).toHashCode();
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
