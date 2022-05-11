package com.apitesting.config;

import com.apitesting.models.YamlConfiguration;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

@Slf4j
public class Configurations {

  public static final String GO_REST_API_URL;
  public static final Boolean LOCAL;

  static {
    try (InputStream inputStream =
        Files.newInputStream(
            Paths.get("src", "main", "resources", "envs", "staging.yaml"))) {
      YamlConfiguration configurations = new Yaml().loadAs(inputStream, YamlConfiguration.class);
      GO_REST_API_URL = configurations.getGoRestUrl();
      LOCAL = configurations.getLocal();

    } catch (IOException e) {
      log.error(e.getMessage());
      throw new ExceptionInInitializerError(e);
    }
  }
}
