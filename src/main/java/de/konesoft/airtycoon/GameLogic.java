/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon;

import de.konesoft.airtycoon.model.Airplane;
import de.konesoft.airtycoon.model.Airport;
import de.konesoft.airtycoon.model.Player;
import de.konesoft.airtycoon.functions.XmlReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameLogic {

    private static GameLogic singletonGameLogic;
    private final XmlReader xml = new XmlReader();
    private Player player;

    private GameLogic() {
    }

    public static GameLogic getSingletonGameLogic() {

        if (singletonGameLogic == null) {
            singletonGameLogic = new GameLogic();
        }
        return singletonGameLogic;
    }

    public Player getPlayer() {
        return player;
    }

    public ObservableList loadSavegames() {

        ObservableList<Player> savegame;
        savegame = FXCollections.observableArrayList(xml.getPlayers());
        return savegame;
    }

    public ObservableList loadFleet() {

        ObservableList<Airplane> playerFleet;
        playerFleet = FXCollections.observableArrayList(player.getFleet());
        return playerFleet;
    }

    public ObservableList xmlAirplanes() {

        ObservableList<Airplane> airplanes;
        airplanes = FXCollections.observableArrayList(xml.getAirplanes());
        return airplanes;
    }

    public ObservableList xmlAirports() {

        ObservableList<Airport> airports;
        airports = FXCollections.observableArrayList(xml.getAirports());
        return airports;
    }

    public void newPlayer(String name) {
        player = new Player(name);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
