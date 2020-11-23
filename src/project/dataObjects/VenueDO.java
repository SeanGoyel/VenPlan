package project.dataObjects;

import javafx.beans.property.SimpleStringProperty;

public class VenueDO {
    SimpleStringProperty venueName = new SimpleStringProperty();
    SimpleStringProperty venueCode = new SimpleStringProperty();
    SimpleStringProperty numOfTheatres = new SimpleStringProperty();

    public String toString(){
        return getVenueName();
    }

    public String getVenueName() {
        return venueName.get();
    }

    public SimpleStringProperty venueNameProperty() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName.set(venueName);
    }

    public String getNumOfTheatres() {
        return numOfTheatres.get();
    }

    public SimpleStringProperty numOfTheatresProperty() {
        return numOfTheatres;
    }

    public void setNumOfTheatres(String numOfTheatres) {
        this.numOfTheatres.set(numOfTheatres);
    }

    public String getVenueCode()
    {
        return venueCode.get();
    }

    public SimpleStringProperty venueCodeProperty()
    {
        return venueCode;
    }

    public void setVenueCode(String venueCode)
    {
        this.venueCode.set(venueCode);
    }
}
