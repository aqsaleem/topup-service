package com.tap.topupservice.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer_wallet")
public class CustomerWallet implements Serializable {

  @Id
  private String id;

  private String customerId;
  private List<WalletDetail> walletDetails = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public List<WalletDetail> getWalletDetails() {
    return walletDetails;
  }

  public void setWalletDetails(List<WalletDetail> walletDetails) {
    this.walletDetails = walletDetails;
  }
}
