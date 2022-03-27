package com.tap.topupservice.service;

import com.tap.topupservice.modal.TopupRequest;
import com.tap.topupservice.modal.TopupResponse;

public interface TopupService {

  TopupResponse topup(TopupRequest request);
}
