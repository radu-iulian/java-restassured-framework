package com.apitesting.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Optional;

public class Secrets {

  public static String BEARER_TOKEN;
  public static final String MYSQL_URL;
  public static final String MYSQL_DRIVER_CLASS_NAME;
  public static final String MYSQL_USERNAME;
  public static final String MYSQL_PASSWORD;

  private static final Dotenv ENV = Dotenv.configure().directory(".").ignoreIfMissing().load();

  static {
    BEARER_TOKEN = Optional.ofNullable(ENV.get("BEARER_TOKEN"))
        .orElse(System.getProperty("BEARER_TOKEN"));
    MYSQL_URL = Optional.ofNullable(ENV.get("MYSQL_URL")).orElse(System.getProperty("MYSQL_URL"));
    MYSQL_DRIVER_CLASS_NAME = Optional.ofNullable(ENV.get("MYSQL_DRIVER_CLASS_NAME"))
        .orElse(System.getProperty("MYSQL_DRIVER_CLASS_NAME"));
    MYSQL_USERNAME = Optional.ofNullable(ENV.get("MYSQL_USERNAME"))
        .orElse(System.getProperty("MYSQL_USERNAME"));
    MYSQL_PASSWORD = Optional.ofNullable(ENV.get("MYSQL_PASSWORD"))
        .orElse(System.getProperty("MYSQL_PASSWORD"));
  }

}
