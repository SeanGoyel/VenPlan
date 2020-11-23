package project.dataAccessObjects;

import project.DBClass;
import project.dataObjects.ArtistDO;
import project.dataObjects.MovieDO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<MovieDO> getAll()
    {
        String sql = "SELECT * FROM Movie ORDER BY MovieID";
        return returnList(sql);
    }

    public List<MovieDO> getMoviesShownInAllVenueTheatres(String venueCode)
    {
        String sql = "WITH venueTheatre AS" +
                " (SELECT TheatreID from Theatre WHERE VenueCode = '" + venueCode + "')" +
                " SELECT DISTINCT S1.MovieID, M.Title" +
                " FROM Showing AS S1 JOIN Movie AS M ON S1.movieID = M.movieID" +
                " WHERE NOT EXISTS" +
                "   (SELECT * FROM venueTheatre as T" +
                "     WHERE NOT EXISTS" +
                "       (SELECT * FROM Showing AS S2" +
                "                 WHERE S2.movieID = S1.movieID" +
                "                 AND T.theatreID = S2.theatreID));";
        return returnList(sql);
    }

    public List<MovieDO> getAllWithShowingCount()
    {
        String sql =
                "SELECT M.MovieID, M.Title, M.AdvisoryRatingCode, COUNT(*) AS ShowingCount " +
                        " FROM Movie M, Showing S" +
                        " WHERE S.MovieID = M.MovieID " +
                        " GROUP BY M.MovieID, M.Title, M.AdvisoryRatingCode" +
                        " ORDER BY ShowingCount DESC";
        return returnListWithCount(sql);
    }

    private List<MovieDO> returnList(String sql)
    {
        List<MovieDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        MovieDO movie = new MovieDO();
                        movie.setMovieID(rs.getInt("MovieId"));
                        movie.setTitle(rs.getString("Title"));
                        if (DBClass.hasColumn(rs,"AdvisoryRatingCode"))
                            {
                                movie.setAdvisoryRating(rs.getString("AdvisoryRatingCode"));
                            }
                        list.add(movie);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }

    private List<MovieDO> returnListWithCount(String sql)
    {
        List<MovieDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        MovieDO movie = new MovieDO();
                        movie.setMovieID(rs.getInt("MovieId"));
                        movie.setTitle(rs.getString("Title"));
                        movie.setAdvisoryRating(rs.getString("AdvisoryRatingCode"));
                        movie.setNumberOfShowings(rs.getInt("ShowingCount"));
                        list.add(movie);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }
}
