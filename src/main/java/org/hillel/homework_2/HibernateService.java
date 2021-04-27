package org.hillel.homework_2;

import org.hillel.Journey;
import org.hillel.homework_1.Const;
import org.hillel.service.JourneyService;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class HibernateService implements JourneyService {
    private Data data;

    public HibernateService(Data data){
        this.data = data;
    }


    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        Connection connection = null;
        List<Journey> journeys = new ArrayList<>();
        try {
            connection = data.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from tickets " +
                            "where stationFrom = ? and stationTo = ? and departure >= ? and arrival <= ?")) {
                statement.setString(1, stationFrom);
                statement.setString(2, stationTo);
                statement.setObject(3, dateFrom);
                statement.setObject(4, dateTo);
                ResultSet rs = statement.executeQuery();
                if(rs == null){
                    System.out.println("Result set is null");
                } else {
                    while (rs.next()) {
                        System.out.println("==================\nStation From: " + rs.getString(Const.TICKETS_STATION_FROM) + "\nStation To: " + rs.getString(Const.TICKETS_STATION_TO) + "\ndeparture: " + rs.getString(Const.TICKETS_DEPARTURE) + "\narrival: " + rs.getString(Const.TICKETS_ARRIVAL) + "\n==================");
                    }
                }
            }
        } catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        finally {
            data.close(connection);
        }
        return journeys;
    }
}