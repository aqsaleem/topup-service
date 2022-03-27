package com.tap.topupservice.util;

import com.tap.topupservice.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class ValidationUtils {
  private static Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

  public static void throwExceptionIfValidationErrorsFound(BindingResult validationResults) {
    if (validationResults.hasErrors()) {
      throw new ValidationException(validationResults);
    }
  }
}
