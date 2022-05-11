package com.apitesting.constants;

import lombok.Getter;

@Getter
public class ApiParameter {

  private final String name;
  private final Object defaultValue;

  public ApiParameter(String name, Object defaultValue) {
    this.name = name;
    this.defaultValue = defaultValue;
  }
}
