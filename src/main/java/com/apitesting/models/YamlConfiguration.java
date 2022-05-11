package com.apitesting.models;

import lombok.Data;

@Data
public class YamlConfiguration {

  private String version;
  private String released;
  private String goRestUrl;
  private Boolean local;
}
