package project.dataObjects;

import javafx.beans.property.*;

public class TheatreDO {
    SimpleIntegerProperty theatreID = new SimpleIntegerProperty();
    SimpleStringProperty venueCode = new SimpleStringProperty();
    SimpleStringProperty displayName = new SimpleStringProperty();
    SimpleIntegerProperty mapRows = new SimpleIntegerProperty();
    SimpleIntegerProperty mapColumns    = new SimpleIntegerProperty();
    SimpleStringProperty venueName = new SimpleStringProperty();
    SimpleStringProperty theatreFullString = new SimpleStringProperty();

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

    public String getDisplayName()
    {
        return displayName.get();
    }

    public SimpleStringProperty displayNameProperty()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName.set(displayName);
    }

    public int getMapRows()
    {
        return mapRows.get();
    }

    public SimpleIntegerProperty mapRowsProperty()
    {
        return mapRows;
    }

    public void setMapRows(int mapRows)
    {
        this.mapRows.set(mapRows);
    }

    public int getMapColumns()
    {
        return mapColumns.get();
    }

    public SimpleIntegerProperty mapColumnsProperty()
    {
        return mapColumns;
    }

    public void setMapColumns(int mapColumns)
    {
        this.mapColumns.set(mapColumns);
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

    public String toString()
    {
        return getTheatreFullString();
    }

    public String getTheatreFullString()
    {
        return theatreFullString.get();
    }
    public void setTheatreFullString(String theatreFullString)
    {
        this.theatreFullString.set(theatreFullString);
    }
}
