package com.tap.topupservice.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

  private BindingResult validationResult;

  public ValidationException(BindingResult validationResult) {
    super("Validation errors");
    this.validationResult = validationResult;
  }

  public BindingResult getValidationResult() {
    return validationResult;
  }
}
