package hillel.org.Service;

import hillel.org.Journey;
import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {
    Collection <Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival);
}
