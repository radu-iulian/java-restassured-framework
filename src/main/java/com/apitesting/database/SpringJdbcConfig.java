package com.apitesting.database;

import com.apitesting.config.Secrets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.apitesting")
public class SpringJdbcConfig {

  private static DriverManagerDataSource dataSource;

  @Bean
  public static DataSource mysqlDataSource() {
    if (dataSource == null) {
      dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(Secrets.MYSQL_DRIVER_CLASS_NAME);
      dataSource.setUrl(Secrets.MYSQL_URL);
      dataSource.setUsername(Secrets.MYSQL_USERNAME);
      dataSource.setPassword(Secrets.MYSQL_PASSWORD);
    }
    return dataSource;
  }
}
