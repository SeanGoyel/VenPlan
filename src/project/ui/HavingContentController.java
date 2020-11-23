package project.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import project.dataAccessObjects.VenueDAO;
import project.dataObjects.*;


public class HavingContentController {
	public TableView<VenueDO> venueTable;
	public TextField tfTheatreCount;
	public TableColumn<VenueDO, String> colTheatreCount;
	public TableColumn<VenueDO, String> colVenueName;
	public ObservableList<VenueDO> venues;

	public Text panelTitle;
	public Text panelSubTitle;



	//this is called when the app first starts
	public void initialize(){
		venueTable.setPlaceholder(new Label("No venues found."));
		colTheatreCount.setCellValueFactory(e -> e.getValue().numOfTheatresProperty());
		colVenueName.setCellValueFactory(e -> e.getValue().venueNameProperty());
		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);

	}
	
	//this is called when the panel is made visible
	//from the main dialog
	public void activate(){

	}

	public void submit() {
		Alert alert;

		if (tfTheatreCount.getText().equals("")) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must type in a theatre count!");
			alert.showAndWait();
			return;
		}
		String numOfTheatres = tfTheatreCount.getText();

		setVenues(FXCollections.observableList(new VenueDAO().getHaving(numOfTheatres)));
		venueTable.getItems().setAll(venues);

		if (venues.isEmpty()) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("No venues fit the criteria!");
			alert.showAndWait();
			return;
		}


	}

	public void setVenues(ObservableList<VenueDO> venues) {
		this.venues = venues;
	}
}
