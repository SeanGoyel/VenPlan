package project.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import org.controlsfx.control.SearchableComboBox;
import project.dataAccessObjects.*;
import project.dataObjects.*;



public class JoinContentController {
	public TableView<MovieCastCrewDO> movieTable;
	public SearchableComboBox<ArtistDO> artists;
	public SearchableComboBox roles;
	public TableColumn<MovieCastCrewDO, String> colArtistName;
	public TableColumn<MovieCastCrewDO, String> colRoleName;
	public TableColumn<MovieCastCrewDO, String> colMovieName;
	public ObservableList<MovieCastCrewDO> movies;
	public ObservableList<ArtistDO> artistsList;
	public ObservableList<MovieCastCrewDO> rolesList;
	public Text panelTitle;
	public Text panelSubTitle;

	//this is called when the app first starts
	public void initialize(){
		movieTable.setPlaceholder(new Label("No movies found."));
		colArtistName.setCellValueFactory(e -> e.getValue().artistNameProperty());
		colRoleName.setCellValueFactory(e -> e.getValue().artistRoleProperty());
		colMovieName.setCellValueFactory(e -> e.getValue().movieTitleProperty());

		roles.getItems().add("Actor");
		roles.getItems().add("Actress");
		roles.getItems().add("Cinematographer");
		roles.getItems().add("Composer");
		roles.getItems().add("Director");
		roles.getItems().add("Editor");
		roles.getItems().add("Producer");
		roles.getItems().add("Production Designer");
		roles.getItems().add("Self");
		roles.getItems().add("Writer");

		//format panel titles
		panelTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelSubTitle.setFont(Font.font(null, FontWeight.BOLD, 14));
		panelTitle.setFill(Color.STEELBLUE);
		panelSubTitle.setFill(Color.STEELBLUE);

	}
	
	//this is called when the panel is made visible
	//from the main dialog
	public void activate(){
		setArtists(FXCollections.observableList(new ArtistDAO().getAll()));
		artists.getItems().setAll(artistsList);
	}

	public void submit() {
		Alert alert;
		if (artists.getValue() == null) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must choose an artist!");
			alert.showAndWait();
			return;
		}
		if (roles.getValue().equals("")) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You must choose a role!");
			alert.showAndWait();
			return;
		}
		try {
			String artistName = artists.getValue().toString();
			String roleName = roles.getValue().toString();
			setMovies(FXCollections.observableList(new MovieCastCrewDAO().getJoins(artistName, roleName)));
			movieTable.getItems().setAll(movies);
		} catch (NullPointerException ignored) {

		}

		if (movies.isEmpty()) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("The artist selected does not play this role in any movies!");
			alert.showAndWait();
			return;
		}
	}

	public void setMovies(ObservableList<MovieCastCrewDO> movies) {
		this.movies = movies;
	}


	public void setArtists(ObservableList<ArtistDO> artists)
	{
		this.artistsList = artists;
	}


	public void setRoles(ObservableList<MovieCastCrewDO> roles)
	{
		this.rolesList = roles;
	}
}
