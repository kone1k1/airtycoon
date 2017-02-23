/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import elements.Player;
import functions.xml_reader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mastercs
 */
public class MenueController implements Initializable {

    private final xml_reader xml = new xml_reader();

    private Player[] playerlist;

    @FXML
    TextField txtPlayername;

    @FXML
    ListView lstSaves;

    ObservableList<Player> savegame;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlayers();

    }

    @FXML
    private void newGame() {
        if (txtPlayername.getText().length() > 0) {
            Player pl = new Player(txtPlayername.getText());
        } else {

        }
    }

    @FXML
    private void loadGame() {

    }

    private void loadPlayers() {
        playerlist = new Player[xml.getPlayers().length];
        System.arraycopy(xml.getPlayers(), 0, playerlist, 0, xml.getPlayers().length);
        savegame = FXCollections.observableArrayList(playerlist);
        lstSaves.setItems(savegame);
    }

    @FXML
    private void exitProg() {
        System.exit(0);
    }
}
