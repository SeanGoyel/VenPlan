package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.sql.*;
import java.util.*;

public class ShowingStatusDAO {

    public List<ShowingStatusDO> getAll()
    {
        String sql = "SELECT * FROM ShowingStatus ORDER BY Description";
        return returnList(sql);
    }

    public List<ShowingStatusDO> getByCode(String code)
    {
        String sql = "SELECT * FROM ShowingStatus WHERE ShowingStatusCode = '" + code + "'";
        return returnList(sql);
    }

    private List<ShowingStatusDO> returnList(String sql)
    {
        List<ShowingStatusDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        ShowingStatusDO status = new ShowingStatusDO();
                        status.setShowingStatusCode(rs.getString("ShowingStatusCode"));
                        status.setCodeDescription(rs.getString("Description"));

                        list.add(status);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
