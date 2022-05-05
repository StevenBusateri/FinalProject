package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.demo1.Database.cars;

public class travelHelpApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Database.init();


        FXMLLoader fxmlLoader = new FXMLLoader(travelHelpApplication.class.getResource("travelHelpview.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);


        for (Car c:cars) {
            System.out.println(c);



         travelHelpController.BOX_MODEL.getItems().add(c.getModel());
                  travelHelpController.BOX_MAKE.getItems().add(c.getMake());
         travelHelpController.BOX_YEAR.getItems().add(c.getYear());

         }
        stage.show();

        System.out.println("\n\n\n");
        for (Car c:
             Database.getCarsWith("Ford",null,2006)) {
            System.out.println(c);
        }


    }



    public static void main(String[] args) {
        launch();
    }

}