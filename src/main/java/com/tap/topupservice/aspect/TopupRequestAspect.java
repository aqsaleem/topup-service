package com.tap.topupservice.aspect;

import com.tap.topupservice.modal.TopupRequest;
import com.tap.topupservice.repository.TopupRepository;
import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TopupRequestAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(TopupRequestAspect.class);

  @Autowired
  private TopupRepository topupRepository;

  @Before(value = "@annotation(com.tap.topupservice.annotation.AuditTrail)")
  public void topupRequestAuditTrail(JoinPoint joinPoint) {
    TopupRequest topupRequest = (TopupRequest)joinPoint.getArgs()[0];
    topupRequest.setTopupDate(new Date());
    LOGGER.debug("Saving TopupRequest into DB for Audit purpose");
    topupRepository.save(topupRequest);
  }
}
