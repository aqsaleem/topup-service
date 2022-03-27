package com.tap.topupservice.modal;

import javax.validation.constraints.NotBlank;

public class Customer {

  @NotBlank
  private String id;
  @NotBlank
  private String walletId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }
}
