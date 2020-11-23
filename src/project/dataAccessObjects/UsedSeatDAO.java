package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.sql.*;

public class UsedSeatDAO {

    public int deleteUsedSeat(SeatMapDO seat) throws SQLException
    {
        // when we delete a usedSeat we need to update the UsedCapacity
        // we will do a transaction

        String sqlSeat = "DELETE FROM UsedSeat WHERE ShowingID = " + seat.getShowingID() +
                " AND CapacityTypeCode = '" + seat.getCapacityTypeCode() + "'" +
                " AND SeatID = " + seat.getSeatID();
        String sqlCapacity = "UPDATE UsedCapacity SET QuantityUsed = QuantityUsed -1 " +
                " WHERE ShowingID = " + seat.getShowingID() +
                " AND CapacityTypeCode = '" + seat.getCapacityTypeCode() + "'";

        try
            {
                DBClass.setAutoCommitOFF();
                DBClass.execSQLUpdate(sqlSeat);
                DBClass.execSQLUpdate(sqlCapacity);
                DBClass.performCommit();
                DBClass.setAutoCommitON();
                return 1;
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
                DBClass.performRollback();
                return 0;
            }
    }
}
