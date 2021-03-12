package hillel.org;

import java.time.LocalDate;
import java.util.Objects;

public class Journey {
    private final String stationFrom;
    private final String stationTo;
    private final LocalDate departure;
    private final LocalDate arrival;
    private final String route;

    public Journey(final String stationFrom, final String stationTo, final LocalDate departure, final LocalDate arrival) {
        if (stationFrom == null) throw new IllegalArgumentException("Station From must be set");
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
        this.route = stationFrom + " -> " + stationTo;
    }

    public int HashCode(){
        return Objects.hash(stationFrom, stationTo, departure, arrival);
    }
    public String getStationFrom(){
        return stationFrom;
    }
    public String getStationTo(){
        return stationTo;
    }
    public LocalDate getdeparture(){
        return departure;
    }
    public LocalDate getarrival(){
        return arrival;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", route='" + route + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(stationFrom, journey.stationFrom) && Objects.equals(stationTo, journey.stationTo) && Objects.equals(departure, journey.departure) && Objects.equals(arrival, journey.arrival) && Objects.equals(route, journey.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationFrom, stationTo, departure, arrival, route);
    }
}

