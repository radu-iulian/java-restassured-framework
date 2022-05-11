package com.apitesting.requests;

import static io.restassured.RestAssured.reset;

import com.apitesting.config.Configurations;
import com.apitesting.config.Secrets;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import net.serenitybdd.rest.SerenityRest;

@Data
public class ApiRequest {

  private final RequestSpecification request = SerenityRest.given();
  private Response response;
  private String path;

  protected ApiRequest() {
    reset();
    setDefaults();
  }

  private void setDefaults() {
    request.baseUri(Configurations.GO_REST_API_URL);
    request.accept(ContentType.JSON);
    setHeaders();
    if (Configurations.LOCAL) {
      request.log().all();
    }
  }

  private void setHeaders() {
    Map<String, Object> headers = new HashMap<>();
    headers.put("Authorization", "Bearer " + Secrets.BEARER_TOKEN);
    headers.put("Content-Type", ContentType.JSON);
    headers.put("Cache-Control", "no-cache");
    request.headers(headers);
  }

  public Response get() {
    response = request.get();
    return response;
  }

  public Response post(Object payload) {
    response = request.body(payload).post();
    return response;
  }

  public Response patch(Object payload) {
    response = request.body(payload).patch();
    return response;
  }

  public Response put(Object payload) {
    response = request.body(payload).put();
    return response;
  }

  public Response delete() {
    response = request.delete();
    return response;
  }

  public ApiRequest setBasePath(String path) {
    request.basePath(path);
    return this;
  }

  public ApiRequest setPathParam(String param, String value) {
    request.pathParam(param, value);
    return this;
  }
}
