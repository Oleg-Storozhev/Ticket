package hillel.org.Config;

import hillel.org.Service.JourneyService;
import hillel.org.Service.StubJourneyService;
import hillel.org.Service.inMemoryJourneyService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean
    public JourneyService inMemoryJourneyService2(){
        return new inMemoryJourneyService2("1");
    }

    @Bean
    public JourneyService stubService(){
        return new StubJourneyService();
    }

    @Bean
    public JourneyService inMemoryJourneyService(){
        return new StubJourneyService();
    }
}
