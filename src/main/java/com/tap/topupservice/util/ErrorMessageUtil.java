package com.tap.topupservice.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ErrorMessageUtil {
  private static ResourceBundle messageSource = ResourceBundle.getBundle("ErrorMessages");

  public static String getMessage(String code) {
    return messageSource.getString(code);
  }

  public static String getErrorMessage(String code, String... params) {
    return MessageFormat.format(getMessage(code), params);
  }
}
