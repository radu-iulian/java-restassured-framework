package com.apitesting.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

  public static List<String> stringToListConverter(String stringValue) {
    return Arrays.asList(stringValue.split("\\s*,\\s*"));
  }

}
