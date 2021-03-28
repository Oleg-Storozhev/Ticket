package hillel.org.Config;

import hillel.org.Service.JourneyService;
import hillel.org.Service.StubJourneyService;
import hillel.org.Service.TicketClient;
import hillel.org.Service.inMemoryJourneyService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hillel.org.Service")
public class RootConfig {
    @Bean("inMemoryJourneyService2")
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
