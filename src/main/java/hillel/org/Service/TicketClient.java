package hillel.org.Service;

import hillel.org.Hibernate.TransactionJourneyService;
import hillel.org.Hibernate.persistance.JourneyEntity;
import hillel.org.Journey;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@Component
public class TicketClient {
    private JourneyService journeyService;

    @Autowired(required = true)
    private Map<String, JourneyService> jouneyServices;

    @Autowired
    private Environment environment;

    @Autowired
    private TransactionJourneyService transactionJourneyService;

    @Autowired
    @Qualifier("inMemoryJourneyService")
    public void setJourneyService(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public TicketClient() {
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        return journeyService.find(stationFrom, stationTo, departure, arrival);
    }

    @PostConstruct
    public void init() throws Exception{
        if(journeyService == null) throw new IllegalArgumentException("JourneyService not init");
        else{
            System.out.println("joureyService init successfully");
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("bean is destroyed");
    }

    public Long createJourney(final JourneyEntity journeyEntity){
        return transactionJourneyService.createJourney(journeyEntity);
    }
}