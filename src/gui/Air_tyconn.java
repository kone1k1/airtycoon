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
        xml_reader xml = new xml_reader();
        Airport[] airports = new Airport[xml.getNumber_airports()];
        Airplane[] airplanes = new Airplane[xml.getNumber_airplanes()];
        System.arraycopy(xml.getAirports(), 0, airports, 0, xml.getNumber_airports());
        System.arraycopy(xml.getAirplanes(), 0, airplanes, 0, xml.getNumber_airplanes());
        //Ladeabschnitt ende

        Player lisa = new Player("Lisa");

        lisa.setFleet(airplanes[0]);
        lisa.getAirplane(0).printLocation();
        lisa.getAirplane(0).fly(airports[2]);
        lisa.getAirplane(0).printLocation();

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
