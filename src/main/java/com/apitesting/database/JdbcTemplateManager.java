package com.apitesting.database;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTemplateManager {

  protected final JdbcTemplate jdbcTemplate;

  public JdbcTemplateManager() {
    DataSource dataSource = SpringJdbcConfig.mysqlDataSource();
    jdbcTemplate = new JdbcTemplate(dataSource);
  }
}
