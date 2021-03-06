package de.konesoft.airtycoon;

import de.konesoft.airtycoon.model.Airliner;
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

	public void newPlayer(String name) {
		player = new Player(name);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public ObservableList<Player> loadSavegames() {

		ObservableList<Player> savegame;
		savegame = FXCollections.observableArrayList(xml.getPlayers());
		return savegame;
	}

	public ObservableList<Airliner> loadFleet() {

		ObservableList<Airliner> playerFleet;
		playerFleet = FXCollections.observableArrayList(player.getPlayerFleet());
		return playerFleet;
	}

	public ObservableList<Airliner> xmlAirplanes() {

		ObservableList<Airliner> airplanes;
		airplanes = FXCollections.observableArrayList(xml.getAirplanes());
		return airplanes;
	}

	public ObservableList<Airport> xmlAirports() {

		ObservableList<Airport> airports;
		airports = FXCollections.observableArrayList(xml.getAirports());
		return airports;
	}

}
