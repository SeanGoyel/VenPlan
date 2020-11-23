package project.ui;

import javafx.event.ActionEvent;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import project.dataAccessObjects.*;
import javafx.collections.*;
import javafx.scene.control.*;
import project.dataObjects.*;
import project.util.DateTimePicker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class ProjectionContentController {

    public TableView showingsTable;
    public DateTimePicker dtpStartDTM;
    public DateTimePicker dtpEndDTM;
    public ComboBox advRating;
    public TableColumn<ShowingDO, String> colVenueName;
    public TableColumn<ShowingDO, String> colMovieTitle;
    public TableColumn<ShowingDO, Timestamp> colStartDtm;
    public TableColumn<ShowingDO, Timestamp> colEndDtm;
    public ObservableList<ShowingDO> showings;
    public Text panelTitle;
    public Text panelSubTitle;

    //this is called when the app first starts
    public void initialize() {
        showingsTable.setPlaceholder(new Label("No showings found."));

        colVenueName.setCellValueFactory(param -> param.getValue().venueNameProperty());
        colMovieTitle.setCellValueFactory(param -> param.getValue().movieTitleProperty());
        colStartDtm.setCellValueFactory(param -> param.getValue().startDTMProperty());
        colEndDtm.setCellValueFactory(param -> param.getValue().endDTMProperty());


        advRating.getItems().add("14 Accompaniment (14A)");
        advRating.getItems().add("18 Accompaniment (18A)");
        advRating.getItems().add("General (G)");
        advRating.getItems().add("Restricted (R)");
        advRating.getItems().add("Unrated (U)");


        dtpStartDTM.setFormat("dd-MM-yyyy HH:mm");
        dtpEndDTM.setFormat("dd-MM-yyyy HH:mm");

        //format panel titles
        panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        panelTitle.setFill(Color.STEELBLUE);
        panelSubTitle.setFill(Color.STEELBLUE);

    }

    public void saveButton(ActionEvent actionEvent) {
        Alert alert;

        if (advRating.getValue() == null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You must select an Advisory Rating!");
            alert.showAndWait();
            return;
        }

        if (dtpEndDTM.getValue() != null && dtpStartDTM.getValue() != null) {


            String advisoryRating = advRating.getValue().toString();

            switch (advisoryRating) {
                case "14 Accompaniment (14A)":
                    advisoryRating = "14A";
                    break;
                case "18 Accompaniment (18A)":
                    advisoryRating = "18A";
                    break;
                case "General (G)":
                    advisoryRating = "G";
                    break;
                case "Parental Guidance (PG)":
                    advisoryRating = "PG";
                    break;
                case "Restricted (R)":
                    advisoryRating = "R";
                    break;
                case "Unrated (U)":
                    advisoryRating = "U";
                    break;
            }

            dtpStartDTM.setValue(dtpStartDTM.getConverter()
                    .fromString(dtpStartDTM.getEditor().getText()));
            dtpEndDTM.setValue(dtpEndDTM.getConverter()
                    .fromString(dtpEndDTM.getEditor().getText()));

            Timestamp newStart = Timestamp.valueOf(dtpStartDTM.getDateTimeValue());
            Timestamp newEnd = Timestamp.valueOf(dtpEndDTM.getDateTimeValue());


            setShowings(FXCollections.observableList(new ShowingDAO().getProjection(advisoryRating, newStart, newEnd)));
            showingsTable.getItems().setAll(showings);


            if (showings.isEmpty()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("No showings fit the criteria!");
                alert.showAndWait();
                return;
            }


        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You must select a Start and End date!");
            alert.showAndWait();
            return;
        }

        colStartDtm.setCellFactory((tableColumn) -> {
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

        colEndDtm.setCellFactory((tableColumn) -> {
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
    }
    //this is called when the panel is made visible
    //from the main dialog
    public void activate() {



    }

    public void setShowings(ObservableList<ShowingDO> showings) {
        this.showings = showings;
    }

    public ObservableList<ShowingDO> getShowings() {
        return this.showings;
    }





}
