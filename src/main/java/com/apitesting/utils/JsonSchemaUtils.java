package com.apitesting.utils;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class JsonSchemaUtils {

  public static void validateJsonSchema(Response response, String fileName) {
    response.then()
        .body(matchesJsonSchema(FileUtils.getJsonFile(fileName, true)));
  }

}
