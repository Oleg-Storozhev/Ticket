package org.hillel.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.hillel.hibernate.jpa.repository",
        entityManagerFactoryRef = "emf")
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(environment.getProperty("database.username"));
        config.setPassword(environment.getProperty("database.password"));
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.addDataSourceProperty("databaseName", environment.getProperty("database.name"));
        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        config.setMinimumIdle(30);
        config.setMaximumPoolSize(150);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws IOException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("hibernate.properties");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(getHibernateProperties());
        return emf;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource());
        System.out.println("Finished with transactionManager");
        return jpaTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(final PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }

    public Properties getHibernateProperties() throws IOException {
        System.out.println("Getting Properties");
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);
            System.out.println("Finished Getting Properties");
            return properties;
        } catch (IOException e){
            throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath!", e);
        }
    }
}