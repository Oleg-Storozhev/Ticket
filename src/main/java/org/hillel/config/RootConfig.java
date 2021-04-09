package org.hillel.config;

import org.hillel.homework_2.Data;
import org.hillel.homework_2.HibernateService;
import org.hillel.service.JourneyService;

import org.hillel.service.StubJourneyService;
import org.hillel.service.inMemoryJourneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"org.hillel.config", "org.hillel.service", "org.hillel.hibernate"})
@PropertySource("application.properties")
public class RootConfig {

    @Bean
    public Data getDataSource() throws Exception {
        return new Data();
    }

    @Bean("HibernateService")
    public JourneyService getDbJourneyService() throws Exception {
        return new HibernateService(getDataSource());
    }

    @Bean("inMemoryJourneyService")
    public JourneyService getMemoryJourneyService(){
        return new inMemoryJourneyService();
    }

    @Bean("stubJourneyService")
    public JourneyService stubJourneyService(){
        return new StubJourneyService();
    }
}

