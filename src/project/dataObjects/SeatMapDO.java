package project.dataObjects;

import javafx.beans.property.*;

public class SeatMapDO {
     SimpleIntegerProperty seatID = new SimpleIntegerProperty();
     SimpleIntegerProperty mapRow = new SimpleIntegerProperty();
     SimpleIntegerProperty mapColumn = new SimpleIntegerProperty();
     SimpleStringProperty rowCode = new SimpleStringProperty();
     SimpleStringProperty seatCode = new SimpleStringProperty();
     SimpleBooleanProperty isUsed = new SimpleBooleanProperty();
     SimpleStringProperty capacityTypeCode = new SimpleStringProperty();
     SimpleIntegerProperty showingID = new SimpleIntegerProperty();
    SimpleStringProperty capacityType = new SimpleStringProperty();
    public String toString(){
        return String.valueOf(getSeatID());
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

    public int getMapRow()
    {
        return mapRow.get();
    }

    public SimpleIntegerProperty mapRowProperty()
    {
        return mapRow;
    }

    public void setMapRow(int mapRow)
    {
        this.mapRow.set(mapRow);
    }

    public int getMapColumn()
    {
        return mapColumn.get();
    }

    public SimpleIntegerProperty mapColumnProperty()
    {
        return mapColumn;
    }

    public void setMapColumn(int mapColumn)
    {
        this.mapColumn.set(mapColumn);
    }

    public String getRowCode()
    {
        return rowCode.get();
    }

    public SimpleStringProperty rowCodeProperty()
    {
        return rowCode;
    }

    public void setRowCode(String rowCode)
    {
        this.rowCode.set(rowCode);
    }

    public String getSeatCode()
    {
        return seatCode.get();
    }

    public SimpleStringProperty seatCodeProperty()
    {
        return seatCode;
    }

    public void setSeatCode(String seatCode)
    {
        this.seatCode.set(seatCode);
    }

    public boolean isIsUsed()
    {
        return isUsed.get();
    }

    public SimpleBooleanProperty isUsedProperty()
    {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed)
    {
        this.isUsed.set(isUsed);
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
}
