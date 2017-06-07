package com.james.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by james on 6/1/17.
 */
@Configuration
public class DatabaseConfiguration {
    @Value("${spring.datasource.driver-class-name}")
    private String DB_DRIVER_CLASS_NAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Primary
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(DB_URL);
        dataSourceBuilder.username(DB_USERNAME);
        dataSourceBuilder.password(DB_PASSWORD);
        dataSourceBuilder.driverClassName(DB_DRIVER_CLASS_NAME);

        return dataSourceBuilder.build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }
}
