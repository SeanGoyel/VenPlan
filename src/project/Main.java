package project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application {
    public static ModuleLayer.Controller appController;
    @Override
    public void start(Stage primaryStage) throws Exception{

       Parent root = FXMLLoader.load(getClass().getResource("ui/mainDialog.fxml"));

        primaryStage.setTitle("CPSC 304 Project");
        primaryStage.setScene(new Scene(root, 1050, 800));
        root.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        DBClass.makeDBConnection();
        launch(args);
    }
}