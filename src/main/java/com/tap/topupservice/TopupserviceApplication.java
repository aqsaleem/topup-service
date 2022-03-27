package com.tap.topupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TopupserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TopupserviceApplication.class, args);
  }

}
