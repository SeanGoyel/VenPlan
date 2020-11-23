package project.ui;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.security.cert.*;
import java.util.*;

public class MainDialogController {
    public TextFlow textFlowSQL;
    public HBox hbSQL;
    //public TextField tfTitle;
    //public TextField tfSubTitle;
    public SimpleStringProperty subtitleText = new SimpleStringProperty();
    //public Label mainTitle;
    //public Label mainSubTitle;
    public VBox topContainer;
    public Text mainTitle;
    public Text mainSubTitle;
    public Text queryText;
    public ScrollPane spSQL;

    @FXML
    private InsertContentController insertContentController;
    @FXML
    private DeleteContentController deleteContentController;
    @FXML
    private DivisionContentController divisionContentController;
    @FXML
    private GroupByContentController groupByContentController;
    @FXML
    private HavingContentController havingContentController;
    @FXML
    private JoinContentController joinContentController;
    @FXML
    private NestedContentController nestedContentController;
    @FXML
    private ProjectionContentController projectionContentController;
    @FXML
    private SelectionContentController selectionContentController;
    @FXML
    private UpdateContentController updateContentController;
    @FXML
    private WelcomeContentController welcomeContentController;
    @FXML
    private SampleContentController sampleContentController;  //temp

    public GridPane curVisiblePane;

    public ToggleButton tbInsert;
    public ToggleButton tbDelete;
    public ToggleButton tbUpdate;
    public ToggleButton tbSelection;
    public ToggleButton tbProjection;
    public ToggleButton tbJoin;
    public ToggleButton tbGroupBy;
    public ToggleButton tbHaving;
    public ToggleButton tbNested;
    public ToggleButton tbDivision;
    public ToggleButton tbWelcome;
    public ToggleButton tbSample;  //temp
    public GridPane gridInsert;
    public GridPane gridDelete;
    public GridPane gridUpdate;
    public GridPane gridSelection;
    public GridPane gridProjection;
    public GridPane gridJoin;
    public GridPane gridGroupBy;
    public GridPane gridHaving;
    public GridPane gridNested;
    public GridPane gridDivision;
    public GridPane gridWelcome;
    public GridPane gridSample;  //temp
    public ToggleGroup toggleGroupTasks;
    public static TextFlow sqlTextFlow = new TextFlow();
    //public static TextFlow sqlTextFlow = new TextFlow();

    public void initialize()
    {

        spSQL.setContent(sqlTextFlow);
        sqlTextFlow.setPrefSize(500, 800);
        mainSubTitle.textProperty().bind(subtitleTextProperty());
        tbWelcome.fire();

        //set up Title Formatting
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(6.0);
        dropShadow.setOffsetY(6.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.6, 0.5));
        mainTitle.setFill(Color.STEELBLUE);
        mainTitle.setEffect(dropShadow);
        mainTitle.setCache(true);
        mainTitle.setText("CPSC 304 Project");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, 32));


        mainSubTitle.setFill(Color.STEELBLUE);
        mainSubTitle.setFont(Font.font(null, FontWeight.BOLD, 16));
        queryText.setFill(Color.STEELBLUE);
        queryText.setFont(Font.font(null, FontWeight.BOLD, 14));
    }

    public void showTaskContent(ActionEvent actionEvent)
    {
        ToggleButton btn = (ToggleButton) actionEvent.getSource();
        String id = btn.getId();
        //make the current GridPane not visible
        if (curVisiblePane != null)
            {
                curVisiblePane.setVisible(false);
            }
        // clear out any prior SQL that is showing
        sqlTextFlow.getChildren().clear();

        switch (id)
            {
                case "tbInsert" -> {
                    curVisiblePane = gridInsert;
                    insertContentController.activate();
                    setSubtitleText("Insert Query");
                }
                case "tbDelete" -> {
                    curVisiblePane = gridDelete;
                    deleteContentController.activate();
                    setSubtitleText("Delete Query");
                }
                case "tbUpdate" -> {
                    curVisiblePane = gridUpdate;
                    updateContentController.activate();
                    setSubtitleText("Update Query");
                }
                case "tbSelection" -> {
                    curVisiblePane = gridSelection;
                    selectionContentController.activate();
                    setSubtitleText("Selection");
                }
                case "tbProjection" -> {
                    curVisiblePane = gridProjection;
                    projectionContentController.activate();
                    setSubtitleText("Projection/Projection Query");
                }
                case "tbJoin" -> {
                    curVisiblePane = gridJoin;
                    joinContentController.activate();
                    setSubtitleText("Join Query");
                }
                case "tbGroupBy" -> {
                    curVisiblePane = gridGroupBy;
                    groupByContentController.activate();
                    setSubtitleText("Group By Query");
                }
                case "tbHaving" -> {
                    curVisiblePane = gridHaving;
                    havingContentController.activate();
                    setSubtitleText("Having Query");
                }
                case "tbNested" -> {
                    curVisiblePane = gridNested;
                    nestedContentController.activate();
                    setSubtitleText("Nested Aggregation Query");
                }
                case "tbDivision" -> {
                    curVisiblePane = gridDivision;
                    divisionContentController.activate();
                    setSubtitleText("Division Query");
                }
                case "tbWelcome" -> {
                    curVisiblePane = gridWelcome;
                    welcomeContentController.activate();
                    setSubtitleText("Welcome");
                }
                case "tbSample" -> {
                    curVisiblePane = gridSample;
                    sampleContentController.activate();
                    setSubtitleText("Sample - Demonstrating Populating a Searchable Combo Box");
                }
            }

        curVisiblePane.setVisible(true);
    }

    public static void updateSQLDisplay(ArrayList<Text> list)
    {
        ObservableList sqlDisplay = sqlTextFlow.getChildren();
        sqlDisplay.setAll(list);

        // for (int i=0;i<list.size(); i++){
        //    list1.add(list.get(i));
        // }
    }
    // Getters and Setters

    public String getSubtitleText()
    {
        return subtitleText.get();
    }

    public SimpleStringProperty subtitleTextProperty()
    {
        return subtitleText;
    }

    public void setSubtitleText(String subtitleText)
    {
        this.subtitleText.set(subtitleText);
    }
}


