package project.dataObjects;

import javafx.beans.property.*;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class ShowingDO {
    SimpleIntegerProperty showingID = new SimpleIntegerProperty();
    SimpleIntegerProperty theatreID = new SimpleIntegerProperty();
    SimpleIntegerProperty movieID = new SimpleIntegerProperty();
    SimpleObjectProperty<Timestamp> startDTM = new SimpleObjectProperty<>();
    SimpleObjectProperty<Timestamp> endDTM = new SimpleObjectProperty<>();
    SimpleBooleanProperty isReservedSeating = new SimpleBooleanProperty();
    SimpleStringProperty showingStatusCode = new SimpleStringProperty();
    SimpleStringProperty movieTitle = new SimpleStringProperty();
    SimpleStringProperty venueName = new SimpleStringProperty();
    SimpleStringProperty theatreName = new SimpleStringProperty();
    SimpleStringProperty showingStatus = new SimpleStringProperty();
    SimpleIntegerProperty showingLength = new SimpleIntegerProperty();
    SimpleStringProperty showingFullString = new SimpleStringProperty();

    public String toString()
    {
        return getShowingFullString();
    }

    public int getShowingID()
    {
        return showingID.get();
    }

    public SimpleIntegerProperty showingIDProperty()
    {
        return showingID;
    }

    public void setShowingID(int showingID)
    {
        this.showingID.set(showingID);
    }

    public int getTheatreID()
    {
        return theatreID.get();
    }

    public SimpleIntegerProperty theatreIDProperty()
    {
        return theatreID;
    }

    public void setTheatreID(int theatreID)
    {
        this.theatreID.set(theatreID);
    }

    public int getMovieID()
    {
        return movieID.get();
    }



    public void setMovieID(int movieID)
    {
        this.movieID.set(movieID);
    }

    public Timestamp getStartDTM()
    {
        return startDTM.get();
    }

    public SimpleObjectProperty<Timestamp> startDTMProperty()
    {
        return startDTM;
    }

    public void setStartDTM(Timestamp startDTM)
    {
        this.startDTM.set(startDTM);
    }

    public Timestamp getEndDTM()
    {
        return endDTM.get();
    }

    public SimpleObjectProperty<Timestamp> endDTMProperty()
    {
        return endDTM;
    }

    public void setEndDTM(Timestamp endDTM)
    {
        this.endDTM.set(endDTM);
    }

    public boolean isIsReservedSeating()
    {
        return isReservedSeating.get();
    }

    public SimpleBooleanProperty isReservedSeatingProperty()
    {
        return isReservedSeating;
    }

    public void setIsReservedSeating(boolean isReservedSeating)
    {
        this.isReservedSeating.set(isReservedSeating);
    }

    public String getShowingStatusCode()
    {
        return showingStatusCode.get();
    }

    public SimpleStringProperty showingStatusCodeProperty()
    {
        return showingStatusCode;
    }

    public void setShowingStatusCode(String showingStatusCode)
    {
        this.showingStatusCode.set(showingStatusCode);
    }

    public String getMovieTitle()
    {
        return movieTitle.get();
    }

    public SimpleStringProperty movieTitleProperty()
    {
        return movieTitle;
    }
    public SimpleIntegerProperty movieIDProperty()
    {
        return movieID;
    }

    public void setMovieTitle(String movieTitle)
    {
        this.movieTitle.set(movieTitle);
    }

    public String getTheatreName()
    {
        return theatreName.get();
    }

    public SimpleStringProperty theatreNameProperty()
    {
        return theatreName;
    }

    public void setTheatreName(String theatreName)
    {
        this.theatreName.set(theatreName);
    }

    public String getShowingStatus()
    {
        return showingStatus.get();
    }

    public SimpleStringProperty showingStatusProperty()
    {
        return showingStatus;
    }

    public void setShowingStatus(String showingStatus)
    {
        this.showingStatus.set(showingStatus);
    }

    public int getShowingLength()
    {
        return showingLength.get();
    }

    public SimpleIntegerProperty showingLengthProperty()
    {
        return showingLength;
    }

    public void setShowingLength(int showingLength)
    {
        this.showingLength.set(showingLength);
    }

    public String getVenueName()
    {
        return venueName.get();
    }

    public SimpleStringProperty venueNameProperty()
    {
        return venueName;
    }

    public void setVenueName(String venueName)
    {
        this.venueName.set(venueName);
    }

    public String getShowingFullString()
    {
        return showingFullString.get();
    }

    public SimpleStringProperty showingFullStringProperty()
    {
        return showingFullString;
    }

    public void setShowingFullString(String showingFullString)
    {
        this.showingFullString.set(showingFullString);
    }


}
