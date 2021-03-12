package hillel.org.Service;
import hillel.org.Journey;

import java.time.LocalDate;
import java.util.*;

public final class inMemoryJourneyServiceTable {
    private final String id;
    public inMemoryJourneyServiceTable(final String identify) {
        System.out.println("call constructor InMemoryJourneyService");
        id  = identify;
    }

    private Map <String, List<Journey>> storage = new HashMap<>();
    {
        storage.put("Odessa -> Lviv", CreateJourney("Odessa", "Lviv"));
        storage.put("Kiev -> Odessa", CreateJourney("Kiev", "Odessa"));
        storage.put("Lviv -> Kiev", CreateJourney("Lviv", "Kiev"));
    }

    private static List<Journey> CreateJourney(String From, String To){
        return Arrays.asList(
                new Journey(From, To, LocalDate.now(), LocalDate.now().plusDays(1)),
                new Journey(From, To, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                new Journey(From, To, LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
    }
    // @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        if(storage == null || storage.isEmpty()) return Collections.emptyList();
        List <Journey> journeys = storage.get(stationFrom + " -> " + stationTo);
        if(journeys == null || journeys.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for(Journey item: journeys){
            if(item.getdeparture().equals(departure) && item.getarrival().equals(arrival)){
                out.add(item);
            }
        }
        return Collections.unmodifiableList(out);
    }
}
