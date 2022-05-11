package com.apitesting.models.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {

  GO_REST_BLANK_USER_NAME("name", "can't be blank"),
  GO_REST_BLANK_USER_GENDER("gender", "can't be blank"),
  GO_REST_BLANK_USER_EMAIL("email", "can't be blank"),
  GO_REST_BLANK_USER_STATUS("status", "can't be blank");

  private String field;
  private String message;

}
