package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.sql.*;
import java.util.*;

public class SeatMapDAO {

    public List<SeatMapDO> getAll(int showingID)
    {
        String sql = "SELECT TMP.ShowingID, TMP.CapacityTypeCode, TMP.SeatID," +
                " TMP.Description as CapacityType, TMP.RowCode, TMP.SeatCode, TMP.MapRow," +
                " TMP.MapColumn, US.SeatID as UsedSeatID" +
                " FROM UsedSeat as US" +
                " RIGHT JOIN " +
                " (SELECT UC.ShowingID as ShowingID, S.*, CT.Description" +
                " FROM CapacityType AS CT" +
                " INNER JOIN (UsedCapacity AS UC" +
                " JOIN Seat AS S ON (UC.CapacityTypeCode = S.CapacityTypeCode)"  +
                " AND (UC.TheatreID = S.TheatreID))" +
                " ON CT.CapacityTypeCode = S.CapacityTypeCode) AS TMP" +
                " ON US.SeatID = TMP.SeatID AND US.TheatreID = TMP.TheatreID" +
                " AND US.ShowingID = TMP.ShowingID" +
                " WHERE TMP.ShowingID = " + showingID +
                " ORDER BY MapRow, MapColumn;" ;
        return returnList(sql);
    }

    private List<SeatMapDO> returnList(String sql)
    {
        List<SeatMapDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        SeatMapDO map = new SeatMapDO();
                        map.setSeatID(rs.getInt("SeatID"));
                        map.setCapacityTypeCode(rs.getString("CapacityTypeCode"));
                        map.setMapRow(rs.getInt("MapRow"));
                        map.setMapColumn(rs.getInt("MapColumn"));
                        map.setRowCode(rs.getString("RowCode"));
                        map.setSeatCode(rs.getString("SeatCode"));
                        map.setShowingID(rs.getInt("ShowingID"));
                        map.setCapacityType(rs.getString("CapacityType"));
                        // this is how you deal with a null value in a record set
                        // read the value then immediately rs.wasNull() and take
                        // appropriate action
                        // in this case if the UsedSeatID is null, the seat is not used
                        int usedSeat = rs.getInt("UsedSeatID");
                        if (rs.wasNull())
                            {
                                map.setIsUsed(false);
                            }
                        else
                            {
                                map.setIsUsed(true);
                            }

                        list.add(map);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
