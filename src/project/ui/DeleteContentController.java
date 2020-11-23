package project.ui;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import org.controlsfx.control.*;
import project.dataAccessObjects.*;
import project.dataObjects.*;
import project.util.*;

import java.sql.*;
import java.util.*;

public class DeleteContentController {
    public SearchableComboBox<ShowingDO> scbShowings;
    public GridPane seatMapGrid;
    public Button btnOK;
    public BorderPane borderPane;
    public Text panelTitle;
    public Text panelSubTitle;
    public Button btnCancel;
    public VBox seatMapPanel;

    //this is called when the app first starts
    public void initialize()
    {
        scbShowings.setCellFactory((p) -> {
            final ListCell<ShowingDO> cell = new ListCell<>() {
                @Override
                protected void updateItem(ShowingDO s, boolean bln)
                {
                    super.updateItem(s, bln);

                    if (s != null)
                        {
                            setText(s.toString());
                        }
                    else
                        {
                            setText("");
                        }
                }
            };

            return cell;
        });

        scbShowings.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (Objects.isNull(newValue))
                {

                }
            else
                {
                    SeatMapSize seatMapSize = getMapSize(newValue.getTheatreID());
                    populateMapData(newValue.getShowingID(), seatMapSize);
                }
        });

        //format panel titles
        panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
        panelTitle.setFill(Color.STEELBLUE);
        panelSubTitle.setFill(Color.STEELBLUE);
    }

    //this is called when the panel is made visible
    //from the main dialog
    public void activate()
    {
        //remove the existing grid and start with a new one
        seatMapPanel.getChildren().clear();
        seatMapPanel.getChildren().add(seatMapGrid);
        populateShowingsData();
    }

    public void populateShowingsData()
    {
        // this gets an observable list of all the showing records from the db
        ObservableList<ShowingDO> showings =
                FXCollections.observableList(new ShowingDAO().getAll(true));
        // assign the observable list to the combo box
        scbShowings.getItems().removeAll();
        scbShowings.setItems(showings);
    }

    public void populateMapData(int showingID, SeatMapSize seatMapSize)
    {
        // get the theatreID from the selected showing
        ObservableList<SeatMapDO> seats =
                FXCollections.observableArrayList(new SeatMapDAO().getAll(showingID));
        populateMapGrid(seats, seatMapSize);
    }

    public SeatMapSize getMapSize(int theatreID)
    {
        SeatMapSize seatMapSize = new SeatMapSize();
        List<TheatreDO> theatres = new TheatreDAO().getTheatreByID(theatreID);

        TheatreDO theatre = theatres.get(0);
        seatMapSize.mapColumns = theatre.getMapColumns();
        seatMapSize.mapRows = theatre.getMapRows();
        return seatMapSize;
    }

    public void populateMapGrid(ObservableList<SeatMapDO> seats, SeatMapSize seatMapSize)
    {
        //remove the existing grid and start with a new one
        seatMapPanel.getChildren().clear();
        seatMapGrid = new GridPane();
        seatMapPanel.getChildren().add((seatMapGrid));

        Background redBackground = new Background(new BackgroundFill((Color.RED),
                                                                     CornerRadii.EMPTY,
                                                                     Insets.EMPTY));
        Background greenBackground = new Background(new BackgroundFill((Color.GREEN),
                                                                       CornerRadii.EMPTY,
                                                                       Insets.EMPTY));
        Background pinkBackground = new Background(new BackgroundFill((Color.PINK),
                                                                      CornerRadii.EMPTY,
                                                                      Insets.EMPTY));
        int mapRows = seatMapSize.mapRows;
        boolean isLargeSeat = false;
        String imageURL;
        String seatImageURL;
        String wheelchairImageURL;
        String blankSeatImageURL;
        String dBoxSeatImageURL;
        String loungerSeatImageURL;
        if (mapRows < 15)
            {
                isLargeSeat = true;
            }

        if (!isLargeSeat)
            {
                seatImageURL = getClass().getResource("/seat24.png").toExternalForm();
                wheelchairImageURL =
                        getClass().getResource("/wheelchairThin24.png").toExternalForm();
                blankSeatImageURL = getClass().getResource("/blank24.png").toExternalForm();
                dBoxSeatImageURL = getClass().getResource("/DBOX24.png").toExternalForm();
                loungerSeatImageURL =
                        getClass().getResource("/lounger24.png").toExternalForm();
            }
        else
            {
                //larger images 32x32
                seatImageURL = getClass().getResource("/seat32.png").toExternalForm();
                wheelchairImageURL =
                        getClass().getResource("/wheelchairThin32.png").toExternalForm();
                blankSeatImageURL = getClass().getResource("/blank32.png").toExternalForm();
                dBoxSeatImageURL = getClass().getResource("/DBOX32.png").toExternalForm();
                loungerSeatImageURL =
                        getClass().getResource("/lounger32.png").toExternalForm();
            }

        String tooltipText = "";
        int lastSeatRow = 0;
        int lastSeatColumn = -1;
        int nextSeatRow;
        int nextSeatColumn;
        //loop through all the seats in the collection
        for (SeatMapDO seat : seats)
            {
                nextSeatRow = seat.getMapRow();
                nextSeatColumn = seat.getMapColumn();

                // the collection only contains seats, in order to get everything
                // to line up correctly where there are empty rows or seat positions
                // we will the gaps with empty space
                if ((nextSeatRow == lastSeatRow &&
                        nextSeatColumn > lastSeatColumn + 1)
                        || (nextSeatRow > lastSeatRow))
                    {
                        fillEmptyPositions(lastSeatRow, lastSeatColumn, nextSeatRow,
                                           nextSeatColumn, seatMapSize, blankSeatImageURL);
                    }

                switch (seat.getCapacityTypeCode())
                    {
                        case "STD":
                            imageURL = seatImageURL;
                            break;
                        case "DBOX":
                            imageURL = dBoxSeatImageURL;
                            break;
                        case "WC":
                            imageURL = wheelchairImageURL;
                            break;
                        case "ZGL":
                            imageURL = loungerSeatImageURL;
                            break;
                        default:
                            imageURL = blankSeatImageURL;
                    }

                tooltipText = "Row: " + seat.getRowCode() + "  Seat: " + seat.getSeatCode();

                Image icon = new Image(imageURL);
                ImageView iconView = new ImageView(icon);
                iconView.setUserData(seat);
                iconView.setPickOnBounds(true);
                iconView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    // add code to delete the UsedSeat if it is Used, if not add code to insert
                    // the seat into the used list
                    updateSeatStatus((SeatMapDO) iconView.getUserData());

                    System.out.println(iconView.getUserData().toString());
                    event.consume();
                });

                StackPane stackpane = new StackPane();
                Background background = greenBackground;
                if (seat.isIsUsed())
                    {
                        background = redBackground;
                    }
                stackpane.setBackground(background);
                stackpane.getChildren().add(iconView);

                Tooltip seatTooltip = new Tooltip(tooltipText);
                Tooltip.install(stackpane, seatTooltip);

                seatMapGrid.add(stackpane, seat.getMapColumn(),
                                seatMapSize.mapRows - seat.getMapRow());

                //System.out.println("last seat: " + seat.getMapRow() + " :: " + seat
                // .getMapColumn());
                lastSeatColumn = seat.getMapColumn();
                lastSeatRow = seat.getMapRow();
            } // end for seat loop

        // now we finish off the final empty positions and rows
        fillEmptyPositions(lastSeatRow, lastSeatColumn,
                           seatMapSize.mapRows,
                           seatMapSize.mapColumns,
                           seatMapSize, blankSeatImageURL);

        // now add a row to show screen position
        TextField screen = new TextField();
        screen.setText("SCREEN");
        screen.setEditable(false);
        screen.setAlignment(Pos.CENTER);
        screen.setPrefWidth(50);
        seatMapGrid.add(screen, 2, seatMapSize.mapRows, seatMapSize.mapColumns - 4, 1);
    }

    private StackPane makeEmptyPosition(String blankSeatImageURL)
    {
        Background pinkBackground = new Background(new BackgroundFill((Color.PINK),
                                                                      CornerRadii.EMPTY,
                                                                      Insets.EMPTY));
        StackPane stackpane = new StackPane();
        Image icon = new Image(blankSeatImageURL);
        ImageView iconView = new ImageView(icon);
        stackpane.setBackground(pinkBackground);
        stackpane.getChildren().add(iconView);
        return stackpane;
    }

    public void fillEmptyPositions(int lastRow, int lastCol, int toRow, int toCol,
                                   SeatMapSize seatMapSize, String blankSeatImageURL)
    {
        int colMax;
        for (int row = lastRow; row <= toRow; row++)
            {
                if (row == toRow) { colMax = toCol; }
                else { colMax = seatMapSize.mapColumns;}

                for (int col = lastCol + 1; col < colMax; col++)
                    {
                        //System.out.println("Row: " + row + " :: Col: " + col);
                        seatMapGrid.add(makeEmptyPosition(blankSeatImageURL), col,
                                        seatMapSize.mapRows - row);
                        //seatMapGrid.add(new TextField(","+col), col, row);
                    }
                lastCol = -1;
            }
    }

    private void updateSeatStatus(SeatMapDO seat)
    {
        if (!seat.isIsUsed())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Used Seat is Required");
                alert.setHeaderText("Select a seat that is in use");
                Text text = new Text("\nSeat " + seat.getRowCode() + seat.getSeatCode() +
                                             " is currently NOT occupied. \nYou must select a " +
                                             "seat" +
                                             " that is in use. \n");
                text.setFontSmoothingType(FontSmoothingType.LCD);
                text.setWrappingWidth(400);
                text.setTextAlignment(TextAlignment.CENTER);

                alert.getDialogPane().setContent(text);
                alert.showAndWait();
                return;
            }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Used Seat");
        alert.setHeaderText("");
        Text text = new Text("\n You have selected seat " + seat.getRowCode() + seat.getSeatCode() +
                                     " a " + seat.getCapacityType() + " seat.\n" +
                                     "Please confirm that you want to delete it (set it to not " +
                                     "used).");

        text.setWrappingWidth(400);
        text.setFontSmoothingType(FontSmoothingType.LCD);
        text.setTextAlignment(TextAlignment.CENTER);
        alert.getDialogPane().setContent(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            {
                try
                    {
                        int retval = new UsedSeatDAO().deleteUsedSeat(seat);
                        // refresh the display
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.showAndWait();

                        SeatMapSize seatMapSize = getMapSize(scbShowings.getValue().getTheatreID());
                        populateMapData(scbShowings.getValue().getShowingID(), seatMapSize);
                    }
                catch (SQLException throwables)
                    {
                        throwables.printStackTrace();
                    }
            }
        else
            {
                return;
            }
    }
    //getters and setters
}



