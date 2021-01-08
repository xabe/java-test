package com.xabe.monad;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class Monad {

  private static final Pattern pattern = Pattern.compile("[a-zA-Z ]+");

  public static ResultOrError<String, String> trim(final String input) {
    if (StringUtils.isBlank(input)) {
      return ResultOrError.createError("String must contain non-space characters.");
    }
    return ResultOrError.createResult(input.trim());
  }

  public static ResultOrError<String, String> toUpperCase(final String input) {
    if (!pattern.asMatchPredicate().test(input)) {
      return ResultOrError.createError("String must contain only letter and spaces.");
    }
    return ResultOrError.createResult(input.toUpperCase());
  }

  public static ResultOrError<String, String> appendExclamd(final String input) {
    if (input.length() > 20) {
      return ResultOrError.createError("String not exceed 20 character");
    }
    return ResultOrError.createResult(input + "!");
  }
}
