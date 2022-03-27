package com.tap.topupservice.enums;

public enum MessagePropertyKey {
  CUSTOMER_NOT_FOUND("customer.not.found"),
  WALLET_NOT_FOUND("wallet.not.found");

  private String value;

  private MessagePropertyKey(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
