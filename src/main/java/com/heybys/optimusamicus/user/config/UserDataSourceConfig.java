package com.heybys.optimusamicus.user.config;

import com.heybys.optimusamicus.common.config.CommonConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.heybys.optimusamicus.user.repository", entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "userTransactionManager")
public class UserDataSourceConfig {

  private final CommonConfigFactory commonConfigFactory;

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari.user")
  public HikariConfig userHikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean
  public DataSource userDataSource() {
    return new HikariDataSource(userHikariConfig());
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean = commonConfigFactory.createEntityManagerFactoryBean();
    factoryBean.setDataSource(userDataSource());
    factoryBean.setPersistenceUnitName("userEntityManager");
    factoryBean.setPackagesToScan("com.heybys.optimusamicus.user.entity");
    return factoryBean;
  }

  @Primary
  @Bean
  public PlatformTransactionManager userTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(userEntityManagerFactory().getObject());
    return transactionManager;
  }
}
