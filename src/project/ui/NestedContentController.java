package project.ui;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import project.dataAccessObjects.*;
import project.dataObjects.*;

public class NestedContentController {
	public TableView<CapacityUsageDO> usageTable;
	public TableColumn<CapacityUsageDO, String> colVenueName;
	public TableColumn<CapacityUsageDO, String> colMovieTitle;
	public TableColumn<CapacityUsageDO, String> colCapacityType;
	public TableColumn<CapacityUsageDO, Number> colAvailableCapacity;
	public TableColumn<CapacityUsageDO, Number> colUsedCapacity;
	public TableColumn<CapacityUsageDO, Double> colGraphicalDisplay;
	//public TextField tfTitle;
	//public TextField tfSubTitle;

	public ObservableList<CapacityUsageDO> usages;
	public Text panelTitle;
	public Text panelSubTitle;

	//this is called when the app first starts
	public void initialize(){

		// these value factories link the value from the CapacityUsageDO
		// to the appropriate table column
		colVenueName.setCellValueFactory(param -> param.getValue().venueNameProperty());
		colMovieTitle.setCellValueFactory(param-> param.getValue().movieTitleProperty());
		colCapacityType.setCellValueFactory(param-> param.getValue().capacityTypeProperty());
		colAvailableCapacity.setCellValueFactory(param -> param.getValue().availableCapacityProperty());
		colUsedCapacity.setCellValueFactory(param -> param.getValue().usedCapacityProperty());
		colGraphicalDisplay.setCellValueFactory(param -> param.getValue().percentUsedProperty().asObject());
		colGraphicalDisplay.setCellFactory(ProgressBarTableCell.forTableColumn());

		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);


	}
	
	//this is called when the panel is made visible
	//from the main dialog
	public void activate(){
		//get all the Capacity Usages and add them to the table
		setUsages(FXCollections.observableList(new CapacityDAO().getUsageGroupedByMovieTheatre()));
		usageTable.getItems().setAll(usages);

	}
//getters and setters
	public ObservableList<CapacityUsageDO> getUsages()
	{
		return usages;
	}

	public void setUsages(ObservableList<CapacityUsageDO> usages)
	{
		this.usages = usages;
	}
}
