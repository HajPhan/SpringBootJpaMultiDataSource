package com.example.SpringBootJPAMultiDataSource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySources({ @PropertySource("classpath:datasource-cfg.properties") })
public class DataSource4Config {

    @Autowired
    private Environment env; // Contains Properties Load by @PropertySources

    @Bean
    public DataSource ds4Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.4"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.4"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.4"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.4"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ds4EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds4Datasource());

        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { Constants.PACKAGE_ENTITIES_4 });

        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_4);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect.4"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql.4"));

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager ds4TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds4EntityManager().getObject());
        return transactionManager;
    }

}
