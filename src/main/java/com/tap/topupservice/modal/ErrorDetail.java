package com.tap.topupservice.modal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ErrorDetail {
  private String element;
  private String message;

  public ErrorDetail(String element, String message) {
    this.element = element;
    this.message = message;
  }

  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    this.element = element;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("element", getElement())
        .append("message", getMessage())
        .build();
  }
}
