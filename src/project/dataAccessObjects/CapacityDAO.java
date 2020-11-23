package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.sql.*;
import java.util.*;

public class CapacityDAO {

    public List<CapacityUsageDO> getUsageGroupedByMovieTheatre()
    {
        String sql = "SELECT V.Name AS VenueName, M.Title AS MovieTitle," +
                " CT.Description AS CapacityType," +
                " SUM(C.QuantityAvailable) AS AvailableCapacity," +
                " SUM(UC.QuantityUsed) AS UsedCapacity" +
                " FROM ((Venue AS V" +
                " JOIN Theatre AS T ON V.VenueCode = T.VenueCode)" +
                " JOIN (Movie AS M" +
                " JOIN (Capacity AS C" +
                " JOIN (Showing AS S" +
                " JOIN UsedCapacity AS UC ON S.ShowingID = UC.ShowingID)" +
                " ON (C.CapacityTypeCode = UC.CapacityTypeCode)" +
                " AND (C.TheatreID = UC.TheatreID))" +
                " ON M.MovieID = S.MovieID) ON T.TheatreID = C.TheatreID)" +
                " JOIN CapacityType AS CT ON UC.CapacityTypeCode = CT.CapacityTypeCode" +
                " GROUP BY M.Title, V.Name, CT.Description;";
        return returnList(sql);
    }

    private List<CapacityUsageDO> returnList(String sql)
    {
        List<CapacityUsageDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        CapacityUsageDO usage = new CapacityUsageDO();
                        usage.setMovieTitle(rs.getString("MovieTitle"));
                        usage.setVenueName(rs.getString("VenueName"));
                        usage.setCapacityType(rs.getString("CapacityType"));
                        usage.setAvailableCapacity(rs.getInt("AvailableCapacity"));
                        usage.setUsedCapacity(rs.getInt("UsedCapacity"));
                        Double percentUsed = (double)rs.getInt("UsedCapacity")
                                /(double)rs.getInt("AvailableCapacity");
                        usage.setPercentUsed(percentUsed);
                        list.add(usage);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
