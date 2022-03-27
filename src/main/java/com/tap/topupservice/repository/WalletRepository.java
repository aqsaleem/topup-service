package com.tap.topupservice.repository;

import com.tap.topupservice.modal.CustomerWallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<CustomerWallet, String> {

  CustomerWallet findCustomerWalletByCustomerId(String customerId);
}
