package project.ui;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import org.controlsfx.control.*;
import project.dataAccessObjects.*;
import project.dataObjects.*;
import project.util.*;

import java.awt.*;
import java.util.*;

public class DivisionContentController {
    //public TextField tfTitle;
	// TextField tfSubTitle;
	public TableView<MovieDO> moviesTable;
	public SearchableComboBox<VenueDO> scbVenue;
	public TableColumn<MovieDO, Number> colMovieID;
	public TableColumn<MovieDO, String> colMovieTitle;

	public ObservableList<VenueDO> venues;
	public ObservableList<MovieDO> movies;
    public Text panelTitle;
	public Text panelSubTitle;
	public Text panelVenue;

	//this is called when the app first starts
	public void initialize(){
		colMovieTitle.setCellValueFactory(param-> param.getValue().movieTitleProperty());
		colMovieID.setCellValueFactory(param-> param.getValue().movieIDProperty());

		moviesTable.setPlaceholder(new Label("No movies found."));

		scbVenue.setCellFactory((p) -> {
			final ListCell<VenueDO> cell = new ListCell<>() {
				@Override
				protected void updateItem(VenueDO v, boolean bln)
				{
					super.updateItem(v, bln);

					if (v != null)
						{
							setText(v.toString());
						}
					else
						{
							setText("");
						}
				}
			};

			return cell;
		});

	// create listener for change in venue
		scbVenue.valueProperty().addListener((observableValue, oldValue, newValue) -> {
			if (Objects.isNull(newValue))
				{

				}
			else
				{
					populateMovieTable(observableValue.getValue().getVenueCode());
				}
		});

		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelVenue.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);
		panelVenue.setFill(Color.STEELBLUE);
	}
	
	//this is called when the panel is made visible
	//from the main dialog
	public void activate(){
		venues = FXCollections.observableList(new VenueDAO().getAll());
		scbVenue.getItems().setAll(venues);
	}
	private void populateMovieTable(String venueCode){
		movies = FXCollections.observableList(new MovieDAO().getMoviesShownInAllVenueTheatres(venueCode));
		moviesTable.getItems().setAll(movies);
		panelVenue.setText(" (" + venueCode + ")");

	}
}
