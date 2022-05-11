package com.apitesting.utils;

import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomValuesGenerator {

  public static final Random RANDOM = new Random();

  private static final Faker FAKER = new Faker();

  public static final String[] LANGUAGES = {
          "it",
          "de",
          "en",
          "fr"
  };

  public static final String[] GENDERS = {
          "male",
          "female"
  };

  public static final String[] COUNTRIES = {
      "AT","BE","BG","HR","CY","CZ","DK","EE","FI","FR","DE",
      "GR","GL","HU","IS","IE","IT","LV","LI","LT","LU","MT",
      "NL","NO","PL","PT","RO","SK","SI","ES","SE","CH","TR"
      ,"UA","GB"
  };

  public static String getRandomValue(String[] values) {
    return values[RANDOM.nextInt(values.length)];
  }

  public static String generateRandomEmail() {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000L);
    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();

    return String.join(".", "qa", timestamp, firstName, lastName + "@pregatit", "com");
  }

  public static String getRandomPhoneNumber() {
    List<String> prefixList = Arrays.asList("+4074","+4075","+4072");
    String randomPrefix = prefixList.get(RANDOM.nextInt(prefixList.size()));

    return randomPrefix + RANDOM.ints(1000000,9999999).findFirst().getAsInt();
  }

  public static String generateRandomNumberAsString(int digits) {
    return FAKER.number().digits(digits);
  }

  public static String getRandomFirstName() {
    return FAKER.name().firstName();
  }

  public static String getRandomLastName() {
    return FAKER.name().lastName();
  }

  public static Date getRandomBirthday() {
    return FAKER.date().birthday();
  }

  public static String getRandomCity() {
    return FAKER.address().city();
  }

  public static Date getFutureDate() {
    return FAKER.date().future(30, 1, TimeUnit.DAYS);
  }

  public static Date getPastDate() {
    return FAKER.date().past(30, TimeUnit.DAYS);
  }

  public static String getTaxResidence() { return FAKER.country().countryCode2().toUpperCase(); }

  public static String getTaxId() { return FAKER.bothify("??#########").toUpperCase(); }

}
