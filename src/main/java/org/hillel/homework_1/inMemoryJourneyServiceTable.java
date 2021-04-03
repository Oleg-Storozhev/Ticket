
package org.hillel.homework_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class inMemoryJourneyServiceTable {

    Connection con = null;
    PreparedStatement statement = null;
    DatabaseHandler db = new DatabaseHandler();

    public void find(String stationFrom, String stationTo) {
        try{
            con = db.getDbConnection();
            statement = con.prepareStatement(
                    "SELECT * FROM tickets WHERE stationFrom = ? AND stationTo = ?");
            statement.setString(1,stationFrom);
            statement.setString(2,stationTo);
            ResultSet rs = statement.executeQuery();
            if(rs == null){
                System.out.println("Result set is null");
            } else {
                while (rs.next()) {
                    System.out.println("==================\nStation From: " + rs.getString(Const.TICKETS_STATION_FROM) + "\nStation To: " + rs.getString(Const.TICKETS_STATION_TO) + "\ndeparture: " + rs.getString(Const.TICKETS_DEPARTURE) + "\narrival: " + rs.getString(Const.TICKETS_ARRIVAL) + "\n==================");
                }
            }
        } catch(SQLException | ClassNotFoundException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
