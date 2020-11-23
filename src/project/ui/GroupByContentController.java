package project.ui;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import org.controlsfx.control.SearchableComboBox;
import project.dataAccessObjects.MovieDAO;
import project.dataAccessObjects.ShowingDAO;
import project.dataAccessObjects.ShowingStatusDAO;
import project.dataAccessObjects.TheatreDAO;
import project.dataObjects.MovieDO;
import project.dataObjects.ShowingDO;
import project.dataObjects.ShowingStatusDO;
import project.dataObjects.TheatreDO;
import project.util.DateTimePicker;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GroupByContentController {
	public TableView<MovieDO> movieTable;

	public ComboBox seatType;
	public TableColumn<MovieDO, String> colTitle;
	public TableColumn<MovieDO, Number> colMovieID;
	public TableColumn<MovieDO, String> colAdvRating;
	public TableColumn<MovieDO, Number> colShowingCount;


	public ObservableList<MovieDO> movies;

	public Text panelTitle;
	public Text panelSubTitle;


	public void initialize() {



		colTitle.setCellValueFactory(param -> param.getValue().titleProperty());
		colMovieID.setCellValueFactory(param -> param.getValue().movieIDProperty());
		colAdvRating.setCellValueFactory(param -> param.getValue().advisoryRating());
		colShowingCount.setCellValueFactory(param -> param.getValue().numberOfShowingsProperty());


		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);


	}


	public void activate(){

		setMovies(FXCollections.observableList(new MovieDAO().getAllWithShowingCount()));
		movieTable.getItems().setAll(movies);

	}



	public ObservableList<MovieDO> getShowings()
	{
		return movies;
	}

	public void setMovies(ObservableList<MovieDO> movie)
	{
		this.movies = movie;
	}


}
