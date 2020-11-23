package project.dataAccessObjects;

import project.*;
import project.dataObjects.*;

import java.rmi.registry.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.Date;

public class ShowingDAO {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<ShowingDO> getAll()
    {
        return this.getAll(false);
    }

    public List<ShowingDO> getAll(boolean isReservedOnly)
    {
        String sql = "SELECT S.*, M.Title, V.Name as VenueName, T.DisplayName AS TheatreName," +
                " U.Description AS ShowingStatus" +
                " FROM Showing S" +
                " JOIN Theatre as T ON S.TheatreID = T.TheatreID" +
                " JOIN Movie as M ON S.MovieID = M.MovieID" +
                " JOIN ShowingStatus as U ON S.ShowingStatusCode = U.ShowingStatusCode" +
                " JOIN Venue as V ON T.VenueCode = V.VenueCode" +
                " WHERE IsReservedSeating = " + isReservedOnly +
                " ORDER BY S.ShowingID";
        return returnList(sql);
    }


    public List<ShowingDO> getProjection(String advisoryRating, Timestamp startTime, Timestamp endTime) {
        String sql = "SELECT V.Name, M.Title, S.StartDtm, S.EndDtm " +
                " FROM Showing S"  +
                " JOIN Movie AS M ON S.MovieID = M.MovieID"  +
                " JOIN Theatre AS T ON S.TheatreID = T.TheatreID"  +
                " JOIN Venue as V ON T.VenueCode = V.VenueCode" +
                " WHERE S.StartDtm > " + "'" + startTime + "'" + " AND S.EndDtm < " + "'" + endTime + "'" + " AND M.ADVISORYRATINGCODE = " + "'" + advisoryRating + "'";
        return returnProjectionList(sql);
    }

    public List<ShowingDO> getByID(int id)
    {
        String sql = "SELECT S.*, M.Title, V.Name as VenueName, T.DisplayName AS TheatreName," +
                " U.Description AS ShowingStatus" +
                " FROM Showing S" +
                " JOIN Theatre as T ON S.TheatreID = T.TheatreID" +
                " JOIN Movie as M ON S.MovieID = M.MovieID" +
                " JOIN Venue as V ON T.VenueCode = V.VenueCode" +
                " JOIN ShowingStatus as U ON S.ShowingStatusCode = U.ShowingStatusCode" +
                " WHERE ShowingID = " + id;
        return returnList(sql);
    }

    public int updateShowingByID(int id, LocalDateTime startDTM, LocalDateTime endDTM,
                                 String statusCode)
    {
        String sql = "UPDATE Showing SET ";
        String where = " WHERE ShowingID = " + id;
        String set = "";
        boolean isUsed = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (!(startDTM == null))
            {
                set = "StartDtm = '" + startDTM.format(formatter) + "'";
                isUsed = true;
            }
        if (!(endDTM == null))
            {
                if (isUsed)
                    {
                        set = set + ", ";
                    }
                set = set + " EndDtm = '" + endDTM.format(formatter) + "'";
            }
        if (!(statusCode == null))
            {
                if (isUsed)
                    {
                        set = set + ",";
                    }
                set = set + " ShowingStatusCode = '" + statusCode + "'";
            }
        sql = sql + set + where;
        return DBClass.execSQLUpdate(sql);
    }

    public int getNextShowingID() {
        int out = 0;
        String sql = "SELECT MAX(S.ShowingID) AS MaxID" +
                " FROM Showing S";

        try {
            ResultSet rs = DBClass.execSQLQuery(sql);
            rs.next();
            out = rs.getInt("MaxID");
        } catch (Exception e) {
            out = 404;
        }

        return out + 1;
    }

    public int addShowing(int showingId, int theatreID, int movieID, LocalDateTime startDTM, LocalDateTime endDTM,
                                 int isReservedSeating, String statusCode)
    {
        String sql = "INSERT INTO Showing ";
        String values = " VALUES (";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String newValues = showingId + ", " + theatreID + ", " + movieID + ", '" +  startDTM.format(formatter)
                + "', '" + endDTM.format(formatter) + "', '" + isReservedSeating + "', '" + statusCode + "')";

        sql = sql + values + newValues;
        return DBClass.execSQLUpdate(sql);
    }

    private List<ShowingDO> returnList(String sql)
    {
        List<ShowingDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        ShowingDO s = new ShowingDO();
                        s.setShowingID(rs.getInt("ShowingID"));
                        s.setVenueName(rs.getString("VenueName"));
                        s.setTheatreID(rs.getInt("TheatreID"));
                        s.setMovieID(rs.getInt("MovieID"));
                        s.setIsReservedSeating(rs.getBoolean("IsReservedSeating"));
                        s.setShowingStatusCode(rs.getString("ShowingStatusCode"));
                        s.setMovieTitle(rs.getString("Title"));
                        s.setTheatreName(rs.getString("TheatreName"));
                        s.setShowingStatus(rs.getString("ShowingStatus"));
                        s.setStartDTM(rs.getTimestamp("StartDtm"));
                        s.setEndDTM(rs.getTimestamp("EndDtm"));
                        long diff = ChronoUnit.MINUTES.between(s.getStartDTM().toInstant(),
                                                               s.getEndDTM().toInstant());
                        s.setShowingLength((int) diff);
                        s.setShowingFullString(
                                "(" + s.getShowingID() + ") " +
                                        s.getVenueName() + ", " +
                                        s.getTheatreName() + ", " +
                                        s.getMovieTitle() + ", " +
                                        dateFormat.format(s.getStartDTM().toLocalDateTime()));

                        list.add(s);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }

    private List<ShowingDO> returnProjectionList(String sql)
    {
        List<ShowingDO> list = new ArrayList<>();
        try
        {
            ResultSet rs = DBClass.execSQLQuery(sql);
            while (rs.next())
            {
                ShowingDO s = new ShowingDO();
                s.setVenueName(rs.getString("Name"));
                s.setMovieTitle(rs.getString("Title"));
                s.setStartDTM(rs.getTimestamp("StartDtm"));
                s.setEndDTM(rs.getTimestamp("EndDtm"));
                long diff = ChronoUnit.MINUTES.between(s.getStartDTM().toInstant(),
                        s.getEndDTM().toInstant());
                s.setShowingLength((int) diff);
                s.setShowingFullString(
                        "(" + s.getShowingID() + ") " +
                                s.getVenueName() + ", " +
                                s.getTheatreName() + ", " +
                                s.getMovieTitle() + ", " +
                                dateFormat.format(s.getStartDTM().toLocalDateTime()));

                list.add(s);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }


}
