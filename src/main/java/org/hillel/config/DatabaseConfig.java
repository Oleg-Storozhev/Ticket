package org.hillel.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:database.properties"})
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(@Value("${database.username}") String userName,
                                 @Value("${database.password}") String password,
                                 @Value("${database.url}") String url,
                                 @Value("${database.name}") String database){
        HikariConfig config = new HikariConfig();
        config.setUsername(userName);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.addDataSourceProperty("databaseName", database);
        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        config.setMinimumIdle(30);
        config.setMaximumPoolSize(150);
        return new HikariDataSource(config);
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean emf(
            DataSource dataSource,
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.hbm2ddl}") String hdb2ddl,
            @Value("${hibernate.show_sql}") String show_sql,
            @Value("${hibernate.query.timeout}") String timeout) throws IOException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("hibernate.properties");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        System.out.println("Setting properties...");
        Properties properties= new Properties();
        properties.put("hibernate.dialect", PostgreSQL10Dialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("javax.persistence.query.timeout", 300000);
        emf.setJpaProperties(properties);
        System.out.println("Finished setting properties");
        return emf;
    }
    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory,
            DataSource dataSource){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);
        System.out.println("Finished with transactionManager");
        return jpaTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(final PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }
}