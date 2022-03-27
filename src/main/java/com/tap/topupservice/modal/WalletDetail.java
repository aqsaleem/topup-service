package com.tap.topupservice.modal;

import java.io.Serializable;
import java.math.BigDecimal;

public class WalletDetail implements Serializable {

  private String walletId;
  private BigDecimal amount;

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
