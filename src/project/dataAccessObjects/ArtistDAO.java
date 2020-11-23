package project.dataAccessObjects;

import project.DBClass;
import project.dataObjects.ArtistDO;

import java.sql.*;
import java.util.*;

public class ArtistDAO {

    public List<ArtistDO> getAll()
    {
        String sql = "SELECT * FROM Artist ORDER BY LastName, GivenName";
        return returnList(sql);
      
    }

    public List<ArtistDO> getAllJoin() {
        String sql = "SELECT * FROM Artist ORDER BY LastName, GivenName";
        return returnJoinList(sql);
    }

    public List<ArtistDO>  getByID(int id)
    {
        String sql = "SELECT * FROM Artist WHERE ArtistID = " + id;
        return returnList(sql);
    }

    public List<ArtistDO>  getByName(String lastName, String givenName)
    {
        // this is not final - should be using 'like' and optional first name
        String sql = "SELECT * FROM Artist WHERE LastName = '"
                + lastName + " AND GivenName = '"
                + givenName + "' ORDER BY LastName, GivenName";
        return returnList(sql);
    }
    
    private List<ArtistDO> returnList(String sql)
    {
       List<ArtistDO> list = new ArrayList<>();
        try
            {
                ResultSet rs = DBClass.execSQLQuery(sql);
                while (rs.next())
                    {
                        ArtistDO artist = new ArtistDO();
                        artist.setArtistID(rs.getInt("ArtistID"));
                        artist.setGivenName(rs.getString("GivenName"));
                        artist.setLastName(rs.getString("LastName"));
                        artist.setDisplayNameByGiven(artist.getDisplayNameByGiven());
                        artist.setDisplayNameByGiven(artist.getGivenName() + " " + artist.getLastName());
                        artist.setDisplayNameByLast(artist.getLastName()+ ", " + artist.getGivenName());
                        list.add(artist);
                    }
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return list;
    }

    public List<ArtistDO> returnJoinList(String sql) {
        List<ArtistDO> list = new ArrayList<>();
        try
        {
            ResultSet rs = DBClass.execSQLQuery(sql);
            while (rs.next())
            {
                ArtistDO artist = new ArtistDO();
                artist.setArtistID(rs.getInt("ArtistID"));
                artist.setGivenName(rs.getString("GivenName"));
                artist.setLastName(rs.getString("LastName"));
               // artist.setDisplayNameByGiven(artist.getDisplayNameByGiven());
             //   artist.setDisplayNameByLast(artist.getLastName()+ ", " + artist.getGivenName());
               // artist.setDisplayNameByLast(artist.getGivenName()+ ", " + artist.getLastName());
                artist.setDisplayNameByGiven(artist.getLastName());
                artist.setDisplayNameByLast(artist.getGivenName());
                //artist.setDisplayNameByGiven(artist.getGivenName() + " " + artist.getLastName());

                list.add(artist);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
