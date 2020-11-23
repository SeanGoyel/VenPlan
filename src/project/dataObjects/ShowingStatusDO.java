package project.dataObjects;

import javafx.beans.property.*;

public class ShowingStatusDO {
   SimpleStringProperty showingStatusCode = new SimpleStringProperty();
   SimpleStringProperty codeDescription = new SimpleStringProperty();

    public String toString(){
        return getCodeDescription();
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

    public String getCodeDescription()
    {
        return codeDescription.get();
    }

    public SimpleStringProperty codeDescriptionProperty()
    {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription)
    {
        this.codeDescription.set(codeDescription);
    }
}
