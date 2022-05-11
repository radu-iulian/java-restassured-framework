package com.apitesting.requests;

import static org.awaitility.Awaitility.with;

import io.restassured.response.Response;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import lombok.SneakyThrows;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.Assert;

public class GenericRetryClient<T extends Response> {

  @SneakyThrows
  private T retryOnStatusCode(Callable<T> function, int expectedStatus) {

    T resp;
    try {
      resp = with()
          .pollInterval(500, TimeUnit.MILLISECONDS)
          .await()
          .atMost(5, TimeUnit.SECONDS)
          .until(function, isStatus(expectedStatus));
    } catch (ConditionTimeoutException ex) {
      resp = function.call();
      Assert.fail(
          String.format(
              "------------ Retry Failed: HTTP code was <%s> instead of <%s> ------------ %n Response Body: <%s>",
              resp.getStatusCode(), expectedStatus, resp.body().prettyPrint()));
    }
    return resp;
  }

  @SneakyThrows
  private T retryOnSize(Callable<T> function, long size) {

    T resp;
    try {
      resp = with()
          .pollInterval(500, TimeUnit.MILLISECONDS)
          .await()
          .atMost(5, TimeUnit.SECONDS)
          .until(function, hasSize(size));
    } catch (ConditionTimeoutException ex) {
      resp = function.call();
      Assert.fail(
          String.format(
              "------------ Retry Failed: Got size <%s> instead of <%s> ------------ %n Response Body: <%s>",
              resp.jsonPath().getList("data"), size, resp.body().prettyPrint()));
    }
    return resp;
  }

  @SneakyThrows
  private T retryOnPropertyWithValue(Callable<T> function, String property,
      Object value) {

    T resp;
    try {
      resp = with()
          .pollInterval(500, TimeUnit.MILLISECONDS)
          .await()
          .atMost(5, TimeUnit.SECONDS)
          .until(function, hasJsonPropertyWithValue(property, value));
    } catch (ConditionTimeoutException ex) {
      resp = function.call();
      Assert.fail(
          String.format(
              "------------ Retry Failed: Property \"%s\":\"%s\" is not contained in body ------------ %n Response Body: <%s>",
              property, value, resp.body().prettyPrint()));
    }

    return resp;
  }

  @SneakyThrows
  public T waitForJsonPropertyWithValue(Callable<T> function, String property, Object value) {
    T response = function.call();
    if (!jsonContains(response.getBody().asString(), property, value)) {
      response = retryOnPropertyWithValue(function, property, value);
    }
    return response;
  }

  public static boolean jsonContains(String body, String property, Object value){
    boolean isString = value instanceof String;
    String format =  isString ? "\"%s\":\"%s\"" : "\"%s\":%s";

    return body.contains(String.format(format, property, value));
  }

  private static Predicate<Response> isStatus(int expectedStatus) {

    return p -> p.getStatusCode() == expectedStatus;
  }

  private static Predicate<Response> hasSize(long size) {

    return p -> p.jsonPath().getList("data").size() == size;
  }

  private static Predicate<Response> hasJsonPropertyWithValue(String property, Object value) {

    return p -> jsonContains(p.getBody().asString(), property, value);
  }

  @SneakyThrows
  public T waitForStatusCode(Callable<T> function, int expectedStatus) {

    T response = function.call();
    if (response.getStatusCode() != expectedStatus) {
      response = retryOnStatusCode(function, expectedStatus);
    }
    return response;
  }

  @SneakyThrows
  public T waitForSize(Callable<T> function, long expectedSize) {

    T response = function.call();
    if (response.jsonPath().getList("data").size() != expectedSize) {
      response = retryOnSize(function, expectedSize);
    }
    return response;
  }
}
