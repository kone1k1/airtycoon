/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import elements.Airplane;
import elements.Player;
import functions.xml_reader;
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

/**
 *
 * @author mastercs
 */
public class Air_Tycoon extends Application {

    private Stage stage;
    private final xml_reader xml = new xml_reader();
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
            stage.setTitle("AirTycoon 0.1");
            gotoMainmenu();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Air_Tycoon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoGameinterface(String name) {
        try {
            GameInterfaceController gameInterface = (GameInterfaceController) replaceSceneContent("GameInterface.fxml");
            this.player = new Player(name);
            gameInterface.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Air_Tycoon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoMainmenu() {
        try {
            MenueController mainMenu = (MenueController) replaceSceneContent("menue.fxml");
            mainMenu.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Air_Tycoon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Air_Tycoon.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Air_Tycoon.class.getResource(fxml));
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
        Player[] playerlist = new Player[xml.getPlayers().length];
        System.arraycopy(xml.getPlayers(), 0, playerlist, 0, xml.getPlayers().length);
        savegame = FXCollections.observableArrayList(playerlist);
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

    public void loadGame(Player pl) {
        try {
            System.out.println(pl.toString());
        } catch (Exception e) {
        }

    }

    public Player getPlayer() {
        return player;
    }

}
