package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.sql.*;
import java.util.*;

public class TheatreDAO {

    public List<TheatreDO> getAll()
    {
        String sql = "SELECT T.*, V.Name AS VenueName FROM Theatre as T" +
                " JOIN Venue as V ON T.VenueCode = V.VenueCode";
        return returnList(sql);
    }

    public List<TheatreDO> getTheatreByID(int id)
    {
        String sql = "SELECT T.*, V.Name AS VenueName FROM Theatre as T" +
                " JOIN Venue as V ON T.VenueCode = V.VenueCode" +
                " WHERE T.TheatreID = " + id;
        return returnList(sql);
    }

    private List<TheatreDO> returnList(String sql)
    {
        List<TheatreDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        TheatreDO theatre = new TheatreDO();
                        theatre.setTheatreID(rs.getInt("TheatreID"));
                        theatre.setVenueCode(rs.getString("VenueCode"));
                        theatre.setDisplayName(rs.getString("DisplayName"));
                        theatre.setMapColumns(rs.getInt("MapColumns"));
                        theatre.setMapRows(rs.getInt("MapRows"));
                        theatre.setVenueName(rs.getString("VenueName"));
                        theatre.setTheatreFullString( "(" + theatre.getTheatreID() + ") " +
                                theatre.getDisplayName() + " at " + theatre.getVenueCode());
                        list.add(theatre);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
