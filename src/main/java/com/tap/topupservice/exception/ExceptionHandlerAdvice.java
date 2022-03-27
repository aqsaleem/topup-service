package com.tap.topupservice.exception;

import static com.tap.topupservice.util.Constants.ERROR;
import static com.tap.topupservice.util.Constants.GENERAL_ERROR_MESSAGE;
import static java.util.Collections.singletonList;

import com.tap.topupservice.modal.ErrorDetail;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.tap.topupservice")
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

  /**
   * Validation errors occurred bad request response code: 400
   */
  @ExceptionHandler(value = ValidationException.class)
  protected ResponseEntity<Object> handleException(ValidationException ex) {
    LOGGER.error(ex.getMessage(), ex);
    final List<ErrorDetail> errors = getValidationErrors(ex.getValidationResult().getFieldErrors());
    return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Customer Not Found, sending response code: 404
   */
  @ExceptionHandler(value = CustomerNotFoundException.class)
  protected ResponseEntity<Object> handleException(CustomerNotFoundException ex) {
    LOGGER.error(ex.getMessage(), ex);
    ErrorDetail errorDetail = new ErrorDetail("Customer", ex.getMessage());
    return new ResponseEntity<>(singletonList(errorDetail), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  /**
   * Wallet Not Found, sending response code: 404
   */
  @ExceptionHandler(value = WalletNotFoundException.class)
  protected ResponseEntity<Object> handleException(WalletNotFoundException ex) {
    LOGGER.error(ex.getMessage(), ex);
    ErrorDetail errorDetail = new ErrorDetail("Wallet", ex.getMessage());
    return new ResponseEntity<>(singletonList(errorDetail), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  /**
   * General Error message occurred due to unknown exception response code: 500
   */
  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<Object> handleException(Exception ex) {
    LOGGER.error(ex.getMessage(), ex);
    return new ResponseEntity<>(
        singletonList(new ErrorDetail(ERROR, GENERAL_ERROR_MESSAGE)),
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private List<ErrorDetail> getValidationErrors(List<FieldError> fieldErrors) {
    return fieldErrors
        .stream()
        .map(error -> {
          ErrorDetail errorDetail = new ErrorDetail(error.getField(), error.getDefaultMessage());
          LOGGER.error(errorDetail.toString());
          return errorDetail;
        })
        .collect(Collectors.toList());
  }
}
