package com.tap.topupservice.exception;

public class WalletNotFoundException extends RuntimeException {

  private String message;

  public WalletNotFoundException(String message) {
    super(message);
  }
}
