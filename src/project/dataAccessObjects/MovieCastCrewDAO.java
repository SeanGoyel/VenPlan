package project.dataAccessObjects;

import project.DBClass;
import project.dataObjects.MovieCastCrewDO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieCastCrewDAO {
    String lastName;

    public List<MovieCastCrewDO> getAll() {
        String sql = "SELECT * FROM MovieCastCrew ";
        return returnList(sql);
    }

    public List<MovieCastCrewDO> getJoins(String artistName, String roleName) {
        if (roleName.equals("Production Designer")) {
            roleName = "Production_designer";
        }
        int index = artistName.lastIndexOf(",");
        ++index;
        ++index;
        String firstName = artistName.substring(index);
        --index;
        --index;
        lastName = artistName.substring(0, index);
        String sql = "SELECT M.Title, A.GivenName AS GivenName, R.RoleName " +
                " FROM MovieCastCrew MC" +
                " JOIN Role AS R ON R.RoleCode = MC.RoleCode" +
                " JOIN Artist AS A ON A.ArtistID = MC.ArtistID" +
                " JOIN Movie AS M ON M.MovieID = MC.MovieID" +
                " WHERE A.GivenName = " + "'" + firstName + "'" + " AND A.LastName = " + "'" + lastName + "'" + " AND R.RoleName = " + "'" + roleName + "'";
        return returnJoinList(sql);
    }

    public List<MovieCastCrewDO> returnList(String sql) {
        List<MovieCastCrewDO> list = new ArrayList<>();
        try {
            ResultSet rs = DBClass.execSQLQuery(sql);
            while (rs.next()) {
                MovieCastCrewDO movieCastCrew = new MovieCastCrewDO();
                movieCastCrew.setMovieID(rs.getString("MovieID"));
                list.add(movieCastCrew);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private List<MovieCastCrewDO> returnJoinList(String sql) {
        List<MovieCastCrewDO> list = new ArrayList<>();
        try {
            ResultSet rs = DBClass.execSQLQuery(sql);
            while (rs.next()) {
                MovieCastCrewDO movie = new MovieCastCrewDO();
                movie.setMovieTitle(rs.getString("Title"));
                movie.setArtistName(rs.getString("GivenName") + " " + lastName);
                String roleName = rs.getString("RoleName");
                if (roleName.equals("production_designer")) {
                    roleName = "Production Designer";
                }
                movie.setArtistRole( roleName.substring(0, 1).toUpperCase() + roleName.substring(1));
                list.add(movie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
