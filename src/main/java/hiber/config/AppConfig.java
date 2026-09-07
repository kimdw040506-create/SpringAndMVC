package hiber.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("hiber")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig {

    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(
                environment.getProperty("db.driver")
        );

        dataSource.setUrl(
                environment.getProperty("db.url")
        );

        dataSource.setUsername(
                environment.getProperty("db.username")
        );

        dataSource.setPassword(
                environment.getProperty("db.password")
        );

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource());
        factory.setPackagesToScan("hiber.model");

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        factory.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();

        properties.put(
                "hibernate.dialect",
                environment.getProperty("hibernate.dialect")
        );

        properties.put(
                "hibernate.show_sql",
                environment.getProperty("hibernate.show_sql")
        );

        properties.put(
                "hibernate.hbm2ddl.auto",
                environment.getProperty("hibernate.hbm2ddl.auto")
        );

        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager transactionManager =
                new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject()
        );

        return transactionManager;
    }
}

