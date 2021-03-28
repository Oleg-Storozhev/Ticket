package hillel.org.Service;

import hillel.org.Hibernate.Entities.JourneyEntity;
import hillel.org.Journey;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {
    Collection <Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival);

    @Transactional
    Long createJourney(JourneyEntity entity);
}
