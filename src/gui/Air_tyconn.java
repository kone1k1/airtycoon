/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import elements.*;
import functions.xml_reader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author mastercs
 */
public class Air_tyconn extends Application {

    public static void main(String[] args) {
        //Ladeabschnitt
        launch(args);

        //Ladeabschnitt ende
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("menue.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Air Tycoon 0.1");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
