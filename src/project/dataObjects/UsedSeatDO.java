package project.dataObjects;

import javafx.beans.property.*;

public class UsedSeatDO {
    SimpleIntegerProperty showingID = new SimpleIntegerProperty();
    SimpleIntegerProperty seatID = new SimpleIntegerProperty();
    SimpleStringProperty capacityTypeCode = new SimpleStringProperty();
    SimpleIntegerProperty theatreID = new SimpleIntegerProperty();

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

    public int getSeatID()
    {
        return seatID.get();
    }

    public SimpleIntegerProperty seatIDProperty()
    {
        return seatID;
    }

    public void setSeatID(int seatID)
    {
        this.seatID.set(seatID);
    }

    public String getCapacityTypeCode()
    {
        return capacityTypeCode.get();
    }

    public SimpleStringProperty capacityTypeCodeProperty()
    {
        return capacityTypeCode;
    }

    public void setCapacityTypeCode(String capacityTypeCode)
    {
        this.capacityTypeCode.set(capacityTypeCode);
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


}
