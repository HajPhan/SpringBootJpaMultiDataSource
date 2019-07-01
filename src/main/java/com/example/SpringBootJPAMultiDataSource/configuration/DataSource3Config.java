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
public class DataSource3Config {

    @Autowired
    private Environment env; // Contains Properties Load by @PropertySources

    @Bean
    public DataSource ds3Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.3"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.3"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.3"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.3"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ds3EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds3Datasource());

        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { Constants.PACKAGE_ENTITIES_3 });

        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_3);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect.3"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql.3"));

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager ds3TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds3EntityManager().getObject());
        return transactionManager;
    }

}
