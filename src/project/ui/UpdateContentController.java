package project.ui;

import javafx.collections.*;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import org.controlsfx.control.*;
import project.dataAccessObjects.*;
import project.dataObjects.*;
import project.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.controlsfx.control.*;

import java.sql.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class UpdateContentController {
    public boolean isDirty;
    //public String currentNewStatusCode;
    public TableView<ShowingDO> showingsTable;
    public TableColumn<ShowingDO, Number> colID;
    public TableColumn<ShowingDO, String> colVenue;
    public TableColumn<ShowingDO, String> colTheatre;
    public TableColumn<ShowingDO, String> colMovie;
    public TableColumn<ShowingDO, Timestamp> colStartDTM;
    public TableColumn<ShowingDO, Timestamp> colEndDTM;
    public TableColumn<ShowingDO, Number> colLength;
    public TableColumn<ShowingDO, String> colStatus;
    public GridPane gridEdits;
    public TextField tfShowingID;
    public TextField tfTheatre;
    public TextField tfVenue;
    public TextField tfMovie;
    public TextField tfLength;
    public DateTimePicker dtpStartDTM;
    public DateTimePicker dtpEndDTM;
    public SearchableComboBox<ShowingStatusDO> scbStatus;
    public ObservableList<ShowingStatusDO> statuses;
    public ObservableList<ShowingDO> showings;
    public ObservableList<ShowingDO> selectedItems;
    public TextField tfStartDTM;
    public TextField tfEndDTM;
    public TextField tfStatus;
    public Text panelTitle;
    public Text panelSubTitle;

    //this is called when the app first starts
    public void initialize()
    {
        //*************************************************************************
        //SET UP THE TABLE TO DISPLAY THE SHOWINGS
        //*************************************************************************
        //allow user to select only a single row at a time
        TableView.TableViewSelectionModel<ShowingDO> selectionModel =
                showingsTable.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        //the currently selected item in the observable list selectedItems
        selectedItems = selectionModel.getSelectedItems();

        //this listener waits for the selection to change and updates the
        //fields on the right of the display with the values from the selected item
        selectedItems.addListener(new ListChangeListener<ShowingDO>() {
            @Override
            public void onChanged(Change<? extends ShowingDO> change)
            {
                String dateFormat = "dd-MM-yyyy HH:mm";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                if (change.getList().size()==0) { return;}
                ShowingDO selected = change.getList().get(0);
                tfLength.textProperty().setValue(String.valueOf(selected.getShowingLength()));
                tfMovie.textProperty().setValue(selected.getMovieTitle());
                tfTheatre.textProperty().setValue(selected.getTheatreName());
                tfVenue.textProperty().setValue(selected.getVenueName());
                tfShowingID.textProperty().setValue(String.valueOf(selected.getShowingID()));
                tfStatus.textProperty().setValue(selected.getShowingStatus());
                tfStartDTM.textProperty().setValue(selected.getStartDTM().toLocalDateTime().format(formatter));
                tfEndDTM.textProperty().setValue((selected.getEndDTM()).toLocalDateTime().format(formatter));

                dtpStartDTM.dateTimeValueProperty().setValue(selected.getStartDTM().toLocalDateTime());
                dtpEndDTM.dateTimeValueProperty().setValue(selected.getEndDTM().toLocalDateTime());
                //scbStatus.setValue(selected.getShowingStatus());
            }
        });
        // these value factories link the value from the ShowingDO
        // to the appropriate table column
        colID.setCellValueFactory(param -> param.getValue().showingIDProperty());
        colVenue.setCellValueFactory(param -> param.getValue().venueNameProperty());
        colTheatre.setCellValueFactory(param -> param.getValue().theatreNameProperty());
        colMovie.setCellValueFactory(param -> param.getValue().movieTitleProperty());
        colStartDTM.setCellValueFactory(param -> param.getValue().startDTMProperty());
        colEndDTM.setCellValueFactory(param -> param.getValue().endDTMProperty());
        colLength.setCellValueFactory(param -> param.getValue().showingLengthProperty());
        colStatus.setCellValueFactory(param -> param.getValue().showingStatusProperty());

        // these cell factories set up the formatting of the date time stripping off
        // seconds and milliseconds for the display
        colStartDTM.setCellFactory((tableColumn) -> {
            TableCell<ShowingDO, Timestamp> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(Timestamp item, boolean empty)
                {
                    super.updateItem(item, empty);

                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty)
                        {
                            String s = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(item);
                            this.setText(s);
                        }
                }
            };

            return tableCell;
        });
        colEndDTM.setCellFactory((tableColumn) -> {
            TableCell<ShowingDO, Timestamp> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(Timestamp item, boolean empty)
                {
                    super.updateItem(item, empty);

                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty)
                        {
                            String s = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(item);
                            this.setText(s);
                        }
                }
            };

            return tableCell;
        });

        //*************************************************************************
        //SET UP THE SEARCHABLE COMBO BOX FOR SELECTING A SHOWING STATUS
        //*************************************************************************

        scbStatus.setCellFactory((p) -> {
            final ListCell<ShowingStatusDO> cell = new ListCell<>() {
                @Override
                protected void updateItem(ShowingStatusDO a, boolean bln)
                {
                    super.updateItem(a, bln);

                    if (a != null)
                        {
                            setText(a.toString());
                        }
                    else
                        {
                            setText(null);
                        }
                }
            };

            return cell;
        });
        
        //format panel titles
        panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
                panelTitle.setFill(Color.STEELBLUE);
        panelSubTitle.setFill(Color.STEELBLUE);
      


        //*************************************************************************
        //SET UP THE DATETIME PICKERS BOX TO FORMAT THE DATE
        //*************************************************************************
        dtpStartDTM.setFormat("dd-MM-yyyy HH:mm");
        dtpEndDTM.setFormat("dd-MM-yyyy HH:mm");
        //*************************************************************************

        //there a bug I can't find that freezes the table after the first click
        // when reorderable is allowed, so I'm disabling for now
        // resize and sort seem OK

        colID.setReorderable(false);
        colVenue.setReorderable(false);
        colStatus.setReorderable(false);
        colStartDTM.setReorderable(false);
        colEndDTM.setReorderable(false);
        colMovie.setReorderable(false);
        colTheatre.setReorderable(false);
        colLength.setReorderable(false);
    }

    public void activate()
    {
        //this is called when the panel is made visible
        //from the main dialog

        //get the showing statuses and add them to the searchable combo box
        setStatuses(FXCollections.observableList(new ShowingStatusDAO().getAll()));
        scbStatus.getItems().setAll(statuses);

        //get all the showings and add them to the table
        setShowings(FXCollections.observableList(new ShowingDAO().getAll()));
        showingsTable.getItems().setAll(showings);
        selectedItems = showingsTable.getSelectionModel().getSelectedItems();
    }

    //Button Actions

    public void saveButton(ActionEvent actionEvent)
    {
        Alert alert;
        ShowingDO selected;
        int currentShowingID;
        // the tableview control maintains an observable collection of selected items
        // since we have limited the selectdion policy for the control to allow the user
        // to only select one row at a time, we know the selected item is the first one
        // in the collection
        if (selectedItems.size() == 1)
            {
                selected = selectedItems.get(0);
                // get the showing ID of the currently selected row
                currentShowingID = selected.getShowingID();
            }
        else
            {
                // if there is no selected item then return
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You must select a row before you save changes");
                alert.showAndWait();
                return;
            }

        // apparently there is a bug in the DateTimePicker where the value of the datetime is not
        // set when the user changes the date or time without picking a date from the calendar
        // so the following code sets it. NOTE I don't have any error handling here to check for
        // a valid date
        dtpStartDTM.setValue(dtpStartDTM.getConverter()
                                     .fromString(dtpStartDTM.getEditor().getText()));
        dtpEndDTM.setValue(dtpEndDTM.getConverter()
                                   .fromString(dtpEndDTM.getEditor().getText()));

        // first check to see which new values the user has set has changed and send update to db
        // only for those fields

        Timestamp newStart = Timestamp.valueOf(dtpStartDTM.getDateTimeValue());
        Timestamp newEnd = Timestamp.valueOf(dtpEndDTM.getDateTimeValue());
        String newStatusCode = null;
        if (!(scbStatus.getValue() == null))
            {
                newStatusCode = scbStatus.getValue().getShowingStatusCode();
            }



        // get the original values for the selected item so we can compare to see if
        // any have changed
        boolean isUpdated = false;
        Timestamp originalStart = selected.getStartDTM();
        Timestamp originalEnd = selected.getEndDTM();
        String originalStatusCode = selected.getShowingStatusCode();

        // compare the original values to the new values and set the variable if
        // it has changed
        LocalDateTime sqlStart = null;
        LocalDateTime sqlEnd = null;
        String sqlStatusCode = null;
        if (!(originalStart.equals(newStart)))
            {
                sqlStart = newStart.toLocalDateTime();
                isUpdated = true;
            }
        if (!(originalEnd.equals(newEnd)))
            {
                sqlEnd = newEnd.toLocalDateTime();
                isUpdated = true;
            }
        if ((newStatusCode != null) && !(originalStatusCode.equals(newStatusCode)))
            {
                sqlStatusCode = newStatusCode;
                isUpdated = true;
            }
        if (isUpdated)
            {
                // update the changed fields in db if nay have been updated,
                // we update all but the update method in the DAO ignores any null fields
                // recordsUpdated will = 1 if the record has been updated.
                int recordsUpdated = new ShowingDAO().updateShowingByID(currentShowingID,
                                                                        sqlStart, sqlEnd,
                                                                        sqlStatusCode);

                if (recordsUpdated == 1)
                    {
                        //success
                        //notify the user and clear the data
                        System.out.println("Success");
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Database Updated");
                        alert.setHeaderText("Success");
                        alert.setContentText("Database has been updated successfully");
                    }
                else
                    {
                        System.out.println("Error on update");
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Database has NOT been updated");
                    }
                alert.showAndWait();
                //update the UI table
                setShowings(FXCollections.observableList(new ShowingDAO().getAll()));
                showingsTable.getItems().setAll(showings);
                resetSelectedItemFields();
            }
    }

    public void resetSelectedItemFields()
    {
        tfShowingID.setText(null);
        tfTheatre.setText(null);
        tfVenue.setText(null);
        tfMovie.setText(null);
        tfLength.setText(null);
        tfStartDTM.setText(null);
        tfEndDTM.setText(null);
        tfStatus.setText(null);
    }

    public void cancelButton(ActionEvent actionEvent)
    {
        //clear fields and set back to selected row values
        scbStatus.setValue(null);
    }

    //getters and setters
    public ObservableList<ShowingDO> getShowings()
    {
        return showings;
    }

    public void setShowings(ObservableList<ShowingDO> showings)
    {
        this.showings = showings;
    }

    public ObservableList<ShowingStatusDO> getStatuses()
    {
        return statuses;
    }

    public void setStatuses(ObservableList<ShowingStatusDO> statuses)
    {
        this.statuses = statuses;
    }
}
