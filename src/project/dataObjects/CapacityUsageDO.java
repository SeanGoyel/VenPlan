package project.dataObjects;

import javafx.beans.property.*;

public class CapacityUsageDO {
    SimpleStringProperty venueName = new SimpleStringProperty();
    SimpleStringProperty movieTitle = new SimpleStringProperty();
    SimpleStringProperty capacityType = new SimpleStringProperty();
    SimpleIntegerProperty availableCapacity = new SimpleIntegerProperty();
    SimpleIntegerProperty usedCapacity = new SimpleIntegerProperty();
    SimpleDoubleProperty percentUsed = new SimpleDoubleProperty();

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

    public String getMovieTitle()
    {
        return movieTitle.get();
    }

    public SimpleStringProperty movieTitleProperty()
    {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle)
    {
        this.movieTitle.set(movieTitle);
    }

    public String getCapacityType()
    {
        return capacityType.get();
    }

    public SimpleStringProperty capacityTypeProperty()
    {
        return capacityType;
    }

    public void setCapacityType(String capacityType)
    {
        this.capacityType.set(capacityType);
    }

    public int getAvailableCapacity()
    {
        return availableCapacity.get();
    }

    public SimpleIntegerProperty availableCapacityProperty()
    {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity)
    {
        this.availableCapacity.set(availableCapacity);
    }

    public int getUsedCapacity()
    {
        return usedCapacity.get();
    }

    public SimpleIntegerProperty usedCapacityProperty()
    {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity)
    {
        this.usedCapacity.set(usedCapacity);
    }

    public double getPercentUsed()
    {
        return percentUsed.get();
    }

    public SimpleDoubleProperty percentUsedProperty()
    {
        return percentUsed;
    }

    public void setPercentUsed(double percentUsed)
    {
        this.percentUsed.set(percentUsed);
    }
}
