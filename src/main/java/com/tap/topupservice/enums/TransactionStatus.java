package com.tap.topupservice.enums;

public enum TransactionStatus {
  INITIATED("INITIATED"),
  SUCCESS("SUCCESS"),
  FAILED("FAILED");

  private String value;

  TransactionStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
