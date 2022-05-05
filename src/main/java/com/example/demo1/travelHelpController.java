package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class travelHelpController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField startLoco;
    @FXML
    private TextField endLoco;
    @FXML
    private Text time;
    @FXML
    private ComboBox boxYear;
    @FXML

    private ComboBox boxMake;
    @FXML

    private ComboBox boxModel;

    @FXML
    private Text stopCount;

    public static ComboBox BOX_YEAR;
    public static ComboBox BOX_MAKE;
    public static ComboBox BOX_MODEL;


    @FXML
    public Text cost;

    @FXML

    public Text galCount;

    @FXML
    public Text ymm;

    @FXML
    protected void onHelloButtonClick() {

        double tripDistance = Database.getDistance(startLoco.getText(), endLoco.getText());

        if (tripDistance != -1) {

            time.setText("DISTANCE " + tripDistance + " Miles");
            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            Car c = Database.getCarFromDatabase(boxMake.getValue().toString(), boxModel.getValue().toString(), (int) boxYear.getValue());
                       ymm.setText(c.getYear() + " " + c.getMake() + " " + c.getModel());

            cost.setText("Total Cost: " + formatter.format(c.cal(tripDistance)));
            stopCount.setText("STOP COUNT: " + Math.ceil(c.tankNeed));

            galCount.setText("GAL COUNT: " + Math.ceil(c.gasNeed));
        } else {
            System.out.println("Invalid Location");
        }

    }

    @FXML
    public void drop() {
        boxModel.getItems().clear();
        boxMake.getItems().clear();


        if (boxYear.getValue() != "Year") {
            for (Car c : Database.getCarsWith(null, null, (int) boxYear.getSelectionModel().getSelectedItem())) {


                boxModel.getItems().add(c.getModel());
                boxMake.getItems().add(c.getMake());

            }
        }


    }

    public void fill() {
        boxYear.getItems().add("test");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BOX_YEAR = boxYear;
        BOX_MODEL = boxModel;
        BOX_MAKE = boxMake;
    }
}