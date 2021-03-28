package hillel.org.Service;
import hillel.org.Hibernate.Entities.JourneyEntity;
import hillel.org.Journey;

import java.time.LocalDate;
import java.util.*;

public final class StubJourneyService implements JourneyService {
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        return Collections.emptyList();
    }

    @Override
    public Long createJourney(JourneyEntity entity) {
        return null;
    }
}
