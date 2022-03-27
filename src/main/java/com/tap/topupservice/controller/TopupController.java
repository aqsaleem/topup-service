package com.tap.topupservice.controller;

import static com.tap.topupservice.util.Constants.TOPUP_BASE_URI;
import static com.tap.topupservice.util.ValidationUtils.throwExceptionIfValidationErrorsFound;

import com.tap.topupservice.modal.TopupRequest;
import com.tap.topupservice.modal.TopupResponse;
import com.tap.topupservice.service.TopupService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TOPUP_BASE_URI)
public class TopupController {

  private static Logger LOGGER = LoggerFactory.getLogger(TopupController.class);

  @Autowired
  private TopupService topupService;

  @RequestMapping(method = RequestMethod.POST)
  public TopupResponse topup(@RequestBody @Valid TopupRequest request,
      BindingResult bindingResult) {
    throwExceptionIfValidationErrorsFound(bindingResult);
    LOGGER.debug("Processing Topup Request for Customer {}, Amount {}",
        request.getCustomer().getId(), request.getAmount());
    return topupService.topup(request);
  }
}
