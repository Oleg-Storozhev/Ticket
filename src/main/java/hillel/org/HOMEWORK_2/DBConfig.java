package hillel.org.HOMEWORK_2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("database propertis.properties")
@EnableTransactionManagement
public class DBConfig{

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();

        config.setPassword(environment.getProperty("database.password"));
        config.setUsername(environment.getProperty("database.username"));
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.addDataSourceProperty("databaseName", environment.getProperty("database.url"));

        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        LocalContainerEntityManagerFactoryBean en = new LocalContainerEntityManagerFactoryBean();
        en.setDataSource(dataSource());
        en.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        en.setJpaProperties(getHibernateProperties());
        return en;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
    }

    public Properties getHibernateProperties() throws IOException {
           try {
               Properties properties = new Properties();
               InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
               properties.load(is);
               return properties;
           }catch (IOException e){
               throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath!", e);
           }
    }
}