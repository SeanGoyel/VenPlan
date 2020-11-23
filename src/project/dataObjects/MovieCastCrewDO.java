package project.dataObjects;

import javafx.beans.property.SimpleStringProperty;

public class MovieCastCrewDO {

    SimpleStringProperty roleCode = new SimpleStringProperty();
    SimpleStringProperty artistID = new SimpleStringProperty();
    SimpleStringProperty movieID = new SimpleStringProperty();
    SimpleStringProperty partPlayed = new SimpleStringProperty();
    SimpleStringProperty position = new SimpleStringProperty();
    SimpleStringProperty movieTitle = new SimpleStringProperty();
    SimpleStringProperty artistName = new SimpleStringProperty();
    SimpleStringProperty artistRole = new SimpleStringProperty();

    public String getArtistRole() {
        return artistRole.get();
    }

    public SimpleStringProperty artistRoleProperty() {
        return artistRole;
    }

    public void setArtistRole(String artistRole) {
        this.artistRole.set(artistRole);
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

    public String getMovieTitle() {
        return movieTitle.get();
    }

    public SimpleStringProperty movieTitleProperty() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle.set(movieTitle);
    }

    public String getRoleCode() {
        return roleCode.get();
    }

    public SimpleStringProperty roleCodeProperty() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode.set(roleCode);
    }

    public String getArtistID() {
        return artistID.get();
    }

    public SimpleStringProperty artistIDProperty() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID.set(artistID);
    }

    public String getMovieID() {
        return movieID.get();
    }

    public SimpleStringProperty movieIDProperty() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID.set(movieID);
    }

    public String getPartPlayed() {
        return partPlayed.get();
    }

    public SimpleStringProperty partPlayedProperty() {
        return partPlayed;
    }

    public void setPartPlayed(String partPlayed) {
        this.partPlayed.set(partPlayed);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }
}
