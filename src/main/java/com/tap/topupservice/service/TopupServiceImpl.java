package com.tap.topupservice.service;

import static com.tap.topupservice.enums.MessagePropertyKey.CUSTOMER_NOT_FOUND;
import static com.tap.topupservice.enums.MessagePropertyKey.WALLET_NOT_FOUND;
import static com.tap.topupservice.util.ErrorMessageUtil.getErrorMessage;

import com.tap.topupservice.annotation.AuditTrail;
import com.tap.topupservice.enums.TransactionStatus;
import com.tap.topupservice.exception.CustomerNotFoundException;
import com.tap.topupservice.exception.WalletNotFoundException;
import com.tap.topupservice.modal.Balance;
import com.tap.topupservice.modal.CustomerWallet;
import com.tap.topupservice.modal.TopupRequest;
import com.tap.topupservice.modal.TopupResponse;
import com.tap.topupservice.repository.WalletRepository;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopupServiceImpl implements TopupService {

  @Autowired
  private WalletRepository walletRepository;

  @Override
  @AuditTrail(operation = "topup")
  public TopupResponse topup(TopupRequest request) {
    // Getting Customer Wallets from DB Using Customer Id
    CustomerWallet customerWallet = walletRepository
        .findCustomerWalletByCustomerId(request.getCustomer().getId());

    // Iterating on Wallets and credit amount if wallet found
    // Break the flow if customer or wallet not found
    customerWallet = Optional.ofNullable(customerWallet).map(customer -> {
      customer.getWalletDetails().stream().filter(
          walletDetail -> walletDetail.getWalletId().equals(request.getCustomer().getWalletId()))
          .findFirst()
          .map(walletDetail -> {
            walletDetail.setAmount(walletDetail.getAmount()
                .add(request.getAmount().subtract(request.getFees().getAmount())));
            return walletDetail;
          })
          .orElseThrow(() -> new WalletNotFoundException(
              getErrorMessage(WALLET_NOT_FOUND.getValue(),
                  request.getCustomer().getWalletId())));
      return customer;
    }).orElseThrow(() -> new CustomerNotFoundException(
        getErrorMessage(CUSTOMER_NOT_FOUND.getValue(),
            request.getCustomer().getId())));

    walletRepository.save(customerWallet);
    return buildResponse(request, customerWallet);
  }

  private TopupResponse buildResponse(TopupRequest request, CustomerWallet customerWallet) {
    TopupResponse response = new TopupResponse();
    response.setId(UUID.randomUUID());
    response.setCreated(new Date());
    response.setStatus(TransactionStatus.SUCCESS.getValue());
    response.setAmount(request.getAmount());
    response.setCurrency(request.getCurrency());
    response.setChargeId(request.getChargeId());

    response.setCustomer(request.getCustomer());
    response.setFees(request.getFees());

    Balance balance = new Balance();
    balance.setTotalAmount(customerWallet.getWalletDetails().stream()
        .filter(
            walletDetail -> walletDetail.getWalletId().equals(request.getCustomer().getWalletId()))
        .findFirst().get().getAmount());
    response.setBalance(balance);
    return response;
  }
}
