package project.dataAccessObjects;

import project.DBClass;

import project.dataObjects.VenueDO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {

    public List<VenueDO> getHaving(String numOfTheatres)
    {
        String sql = "SELECT COUNT(*) AS NumOfTheatres, V.Name " +
                " FROM Venue V" +
                " JOIN Theatre as T ON V.VenueCode = T.VenueCode" +
                " GROUP BY V.Name" +
                " HAVING COUNT(*) >= " + "'" + numOfTheatres + "'";

        return returnList(sql);
    }

    public List<VenueDO> getAll()
    {
        String sql = "SELECT * FROM Venue";
        return returnList(sql);
    }

    public List<VenueDO> returnList(String sql)
    {
        List<VenueDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        VenueDO v = new VenueDO();
                        v.setVenueName(rs.getString("Name"));
                        if (DBClass.hasColumn(rs, "NumOfTheatres"))
                            {
                                v.setNumOfTheatres(rs.getString("NumOfTheatres"));
                            }
                        if (DBClass.hasColumn(rs, "VenueCode"))
                            {
                                v.setVenueCode(rs.getString("VenueCode"));
                            }
                        list.add(v);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
