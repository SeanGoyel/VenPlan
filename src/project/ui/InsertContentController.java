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

public class InsertContentController {
	public TableView<MovieDO> movieTable;
	public TextField tfShowingID;
	public SearchableComboBox<TheatreDO> scbTheatre;
	public TextField tfMovie;
	public DateTimePicker dtpStartDTM;
	public DateTimePicker dtpEndDTM;
	public SearchableComboBox<ShowingStatusDO> scbStatus;
	public ComboBox seatType;
	public TableColumn<MovieDO, String> colTitle;
	public TableColumn<MovieDO, Number> colMovieID;
	public TableColumn<MovieDO, String> colAdvRating;
	public ObservableList<ShowingStatusDO> statuses;
	public ObservableList<TheatreDO> theatres;

	public ObservableList<MovieDO> movies;

	public ObservableList<MovieDO> selectedMovies;
	public Text panelTitle;
	public Text panelSubTitle;

	public void initialize() {

		TableView.TableViewSelectionModel<MovieDO> selectionModel =
				movieTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);

		selectedMovies = selectionModel.getSelectedItems();

		selectedMovies.addListener(new ListChangeListener<MovieDO>() {
			@Override
			public void onChanged(Change<? extends MovieDO> change) {

				if (change.getList().size() == 0) {
					return;
				}
				MovieDO selected = change.getList().get(0);
				tfMovie.textProperty().setValue(selected.getTitle());

			}
		});


		colTitle.setCellValueFactory(param -> param.getValue().titleProperty());
		colMovieID.setCellValueFactory(param -> param.getValue().movieIDProperty());
		colAdvRating.setCellValueFactory(param -> param.getValue().advisoryRating());

		//Showing ID
		tfShowingID.setPromptText("Enter unused ID number");

		//Seating Type
		seatType.getItems().add("Reserved Seating");
		seatType.getItems().add("General Admission");


		//Status
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

		//Theatres
		scbTheatre.setCellFactory((p) -> {
			final ListCell<TheatreDO> cell = new ListCell<>() {
				@Override
				protected void updateItem(TheatreDO a, boolean bln)
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

		dtpStartDTM.setFormat("dd-MM-yyyy HH:mm");
		dtpEndDTM.setFormat("dd-MM-yyyy HH:mm");

		colTitle.setReorderable(false);
		colMovieID.setReorderable(false);
		colAdvRating.setReorderable(false);

		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);

	}
	

	public void activate(){

		setTheatres(FXCollections.observableList(new TheatreDAO().getAll()));
		scbTheatre.getItems().setAll(theatres);

		setStatuses(FXCollections.observableList(new ShowingStatusDAO().getAll()));
		scbStatus.getItems().setAll(statuses);

		setMovies(FXCollections.observableList(new MovieDAO().getAll()));
		movieTable.getItems().setAll(movies);
		selectedMovies = movieTable.getSelectionModel().getSelectedItems();
	}


	public void getNextID(ActionEvent actionEvent)
	{
		tfShowingID.setText(Integer.toString(new ShowingDAO().getNextShowingID()));

	}


	public void saveButton(ActionEvent actionEvent)
	{
		Alert alert;

		if (tfShowingID.getText().isEmpty())
		{
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must input a Showing ID!");
			alert.showAndWait();
			return;
		}

		int showingID = Integer.parseInt(tfShowingID.getText());
		ShowingDAO currShowings = new ShowingDAO();
		if(!(currShowings.getByID(showingID).isEmpty())) {

			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("This Showing ID is already in use!");
			alert.showAndWait();
			return;
		}

		MovieDO selected;
		int currentMovieID;

		if (selectedMovies.size() == 1)
		{
			selected = selectedMovies.get(0);
			currentMovieID = selected.getMovieID();
		} else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must select a Movie!");
			alert.showAndWait();
			return;
		}

		if (scbTheatre == null) {

			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must select a Theatre!");
			alert.showAndWait();
			return;
		}
		int theatreID = scbTheatre.getValue().getTheatreID();


		dtpStartDTM.setValue(dtpStartDTM.getConverter()
				.fromString(dtpStartDTM.getEditor().getText()));
		dtpEndDTM.setValue(dtpEndDTM.getConverter()
				.fromString(dtpEndDTM.getEditor().getText()));


		if (scbStatus.getValue() == null)
		{
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must select a Status!");
			alert.showAndWait();
			return;
		}

		String statusCode = scbStatus.getValue().getShowingStatusCode();

		if (seatType.getValue() == null)
		{
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must select a Seating Type!");
			alert.showAndWait();
			return;
		}

		int isReservedSeating = 1;
		if(seatType.getValue() == "General Admission") isReservedSeating = 0;

		Timestamp newStart = Timestamp.valueOf(dtpStartDTM.getDateTimeValue());
		Timestamp newEnd = Timestamp.valueOf(dtpEndDTM.getDateTimeValue());

		LocalDateTime sqlStart = newStart.toLocalDateTime();
		LocalDateTime sqlEnd = newEnd.toLocalDateTime(); ;

			int recordsUpdated = new ShowingDAO().addShowing(showingID,theatreID,currentMovieID,sqlStart,sqlEnd,
					isReservedSeating, statusCode);

			if (recordsUpdated == 1)
			{

				System.out.println("Success");
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Database Updated");
				alert.setHeaderText("Success");
				alert.setContentText("Database has been updated successfully");
			}
			else
			{
				System.out.println("Error entering new showing");
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("Database has NOT been updated");
			}
			alert.showAndWait();
			resetSelectedItemFields();

	}

	public void resetSelectedItemFields()
	{
		tfShowingID.setText(null);
		tfMovie.setText(null);
		scbTheatre.setValue(null);
		scbStatus.setValue(null);
		tfShowingID.setText(null);
		dtpEndDTM.setValue(null);
		dtpStartDTM.setValue(null);
		seatType.setValue(null);
	}

	public ObservableList<MovieDO> getShowings()
	{
		return movies;
	}

	public void setMovies(ObservableList<MovieDO> movie)
	{
		this.movies = movie;
	}

	public ObservableList<ShowingStatusDO> getStatuses()
	{
		return statuses;
	}

	public void setStatuses(ObservableList<ShowingStatusDO> statuses)
	{
		this.statuses = statuses;
	}


	public void setTheatres(ObservableList<TheatreDO> theatres)
	{
		this.theatres = theatres;
	}
}
