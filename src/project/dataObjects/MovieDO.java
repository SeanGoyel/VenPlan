package project.dataObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MovieDO {

    SimpleIntegerProperty movieID = new SimpleIntegerProperty();
    SimpleStringProperty title = new SimpleStringProperty();
    SimpleStringProperty advisoryRating = new SimpleStringProperty();
    SimpleIntegerProperty numberOfShowings = new SimpleIntegerProperty();
    SimpleStringProperty roleName = new SimpleStringProperty();
    SimpleStringProperty artistName = new SimpleStringProperty();

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setAdvisoryRating(String advisoryRating) {
        this.advisoryRating.set(advisoryRating);
    }

    public SimpleStringProperty getAdvisoryRating() {return this.advisoryRating;}

    public SimpleStringProperty advisoryRating() {
        return advisoryRating;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }


    public int getMovieID() {
        return movieID.get();
    }


    public SimpleIntegerProperty movieIDProperty() {
        return movieID;
    }

    public SimpleStringProperty movieTitleProperty() {
        return title;
    }

    public void setMovieID(int movieID) {
        this.movieID.set(movieID);
    }


    public void setNumberOfShowings(int numberOfShowings) {
        this.numberOfShowings.set(numberOfShowings);
    }

    public int getNumberOfShowings() {
        return numberOfShowings.get();
    }

    public SimpleIntegerProperty numberOfShowingsProperty() {
        return numberOfShowings;
    }

    public SimpleStringProperty advisoryRatingProperty() {
        return advisoryRating;
    }

    public String getRoleName() {
        return roleName.get();
    }

    public SimpleStringProperty roleNameProperty() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName.set(roleName);
    }

    public String getArtistName() {
        return artistName.get();
    }

    public SimpleStringProperty artistNameProperty() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName.set(artistName);
    }

    //getters and setters


}
