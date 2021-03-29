package org.hillel.config;

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

    @Bean("inMemoryJourneyService")
    public JourneyService getMemoryJourneyService(){
        return new inMemoryJourneyService();
    }

    @Bean("stubJourneyService")
    public JourneyService stubJourneyService(){
        return new StubJourneyService();
    }
}

