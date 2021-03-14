package hillel.org.Service;

import hillel.org.Journey;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class TicketClient {
    private JourneyService journeyService;

    public TicketClient(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        return journeyService.find(stationFrom, stationTo, departure, arrival);
    }

    public void afterPropertiesSet() throws Exception{
        if(journeyService == null) throw new IllegalArgumentException("JourneyService not init");
        else{
            System.out.println("joureyService init successfully");
        }
    }

}