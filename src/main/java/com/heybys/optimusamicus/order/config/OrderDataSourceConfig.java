package com.heybys.optimusamicus.order.config;

import com.heybys.optimusamicus.common.config.CommonConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.heybys.optimusamicus.order.repository",
    entityManagerFactoryRef = "orderEntityManagerFactory",
    transactionManagerRef = "orderTransactionManager")
public class OrderDataSourceConfig {

  private final CommonConfigFactory commonConfigFactory;

  @Value("${app.domain.order.persistence-unit-name}")
  private String persistenceUnitName;

  @Value("${spring.datasource.hikari.order.jdbc-url}")
  private String jdbcUrl;

  @Value("${spring.datasource.hikari.order.username}")
  private String username;

  @Value("${spring.datasource.hikari.order.password}")
  private String password;

  @Bean
  public HikariConfig orderHikariConfig() {

    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcUrl);
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);

    return hikariConfig;
  }

  @Bean
  public DataSource orderDataSource() {
    return new HikariDataSource(orderHikariConfig());
  }

  @Bean
  public JdbcTemplate orderJdbcTemplate() {
    return new JdbcTemplate(orderDataSource());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean =
        commonConfigFactory.createEntityManagerFactoryBean();
    factoryBean.setDataSource(orderDataSource());
    factoryBean.setPersistenceUnitName(persistenceUnitName);
    factoryBean.setPackagesToScan("com.heybys.optimusamicus.order.entity");
    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager orderTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(orderEntityManagerFactory().getObject());
    return transactionManager;
  }
}
