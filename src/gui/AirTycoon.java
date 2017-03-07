/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import main.Airplane;
import main.Player;
import functions.XmlReader;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import main.Airport;

/**
 *
 * @author mastercs
 */
public class AirTycoon extends Application {

    private Stage stage;
    private final XmlReader xml = new XmlReader();
    private Player player;

    public static void main(String[] args) {
        //Ladeabschnitt
        Application.launch(args);

        //Ladeabschnitt ende
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Air Tycoon Alpha 0.2");
            gotoMainmenu();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(AirTycoon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoGameinterface(String name) {
        try {
            GameInterfaceController gameInterface = (GameInterfaceController) replaceSceneContent("GameInterface.fxml");
            //prüfen ob schon ein Spieler geladen wurde
            if (this.player == null) {
                this.player = new Player(name);
            }
            gameInterface.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(AirTycoon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoMainmenu() {
        try {
            MenueController mainMenu = (MenueController) replaceSceneContent("menue.fxml");
            mainMenu.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(AirTycoon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = AirTycoon.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(AirTycoon.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public ObservableList loadSavegames() {

        ObservableList<Player> savegame;
        savegame = FXCollections.observableArrayList(xml.getPlayers());
        return savegame;
    }

    public ObservableList loadFleet() {

        ObservableList<Airplane> fleet;
        fleet = FXCollections.observableArrayList(player.getFleet());
        return fleet;
    }

    public ObservableList loadXmlAirplanes() {

        ObservableList<Airplane> airplanes;
        airplanes = FXCollections.observableArrayList(xml.getAirplanes());
        return airplanes;
    }

    public ObservableList loadXmlAirports() {

        ObservableList<Airport> airports;
        airports = FXCollections.observableArrayList(xml.getAirports());
        return airports;
    }

    public void loadGame(Player pl) {

        this.player = pl;
        gotoGameinterface(this.player.getName());

    }

    public Player getPlayer() {
        return player;
    }

}