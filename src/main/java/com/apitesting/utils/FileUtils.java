package com.apitesting.utils;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

  private static final String SOURCE_FOLDER = "src";
  private static final String JSON_FOLDER = "main/resources/%s";

  public static File getJsonFile(String fileName, boolean isSchema) {
    String path =
        (isSchema) ? String.format(JSON_FOLDER, "jsonSchemas") : String.format(JSON_FOLDER, "json");
    return Paths.get(SOURCE_FOLDER, path).resolve(fileName).toFile();
  }
}
