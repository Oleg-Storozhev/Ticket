package org.hillel.service;
import org.hillel.Journey;

import java.time.LocalDate;
import java.util.*;

public final class StubJourneyService implements JourneyService {
    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }
}
