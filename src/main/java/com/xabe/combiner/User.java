package com.xabe.combiner;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

  private final String name;

  private final String email;

  @Override
  public boolean equals(final Object other) {
    if (other instanceof User) {
      final User castOther = (User) other;
      return new EqualsBuilder().append(this.name, castOther.name).append(this.email, castOther.email).isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.name).append(this.email).toHashCode();
  }

  public User(final String name, final String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

}
