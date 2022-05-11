package com.apitesting.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class ReflectionUtils {

  @SneakyThrows
  public static void setField(Object where, String fieldName, Object newValue) {

    Field field = where.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(where, newValue);
  }

}
