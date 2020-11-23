package project.dataObjects;

import javafx.beans.property.*;

public class ArtistDO {
     SimpleIntegerProperty artistID = new SimpleIntegerProperty();
     SimpleStringProperty givenName = new SimpleStringProperty();
     SimpleStringProperty lastName = new SimpleStringProperty();
     SimpleStringProperty displayNameByLast = new SimpleStringProperty();
     SimpleStringProperty displayNameByGiven = new SimpleStringProperty();


    public String toString(){
        return getDisplayNameByLast();
    }

    //getters and setters

    public int getArtistID()
    {
        return artistID.get();
    }

    public SimpleIntegerProperty artistIDProperty()
    {
        return artistID;
    }

    public void setArtistID(int artistID)
    {
        this.artistID.set(artistID);
    }

    public String getGivenName()
    {
        return givenName.get();
    }

    public SimpleStringProperty givenNameProperty()
    {
        return givenName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName.set(givenName);
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName.set(lastName);
    }

    public String getDisplayNameByLast()
    {
        return displayNameByLast.get();
    }

    public SimpleStringProperty displayNameByLastProperty()
    {
        return displayNameByLast;
    }

    public void setDisplayNameByLast(String displayNameByLast)
    {
        this.displayNameByLast.set(displayNameByLast);
    }

    public String getDisplayNameByGiven()
    {
        return displayNameByGiven.get();
    }

    public SimpleStringProperty displayNameByGivenProperty()
    {
        return displayNameByGiven;
    }

    public void setDisplayNameByGiven(String displayNameByGiven)
    {
        this.displayNameByGiven.set(displayNameByGiven);
    }
}
