package project.ui;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import javafx.util.Duration;
import project.dataAccessObjects.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.controlsfx.control.*;
import project.dataObjects.*;

import java.util.*;

public class WelcomeContentController {

    @FXML
    private Circle c2;

    @FXML
    private Circle c1;

    //this is called when the app first starts
    public void initialize() {
     rotate();
    }

    //this is called when the panel is made visible
    //from the main dialog
    public void activate() {

    }

    private void rotate() {
        setRotate(c1, true,360,23);
        setRotate(c2, true,180,18);

    }

    private void setRotate(Circle c, boolean reverse, int angle, int duration) {

        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(40);
        rt.play();
    }


}