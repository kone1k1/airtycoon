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
import javafx.event.ActionEvent;
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

    private Air_tyconn application;
    private final xml_reader xml = new xml_reader();

    @FXML
    TextField txtPlayername;

    @FXML
    ListView lstSaves;

    public void setApp(Air_tyconn application) {
        this.application = application;
        lstSaves.setItems(application.loadSavegames());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void newGame(ActionEvent event) {
        if (txtPlayername.getText().length() > 0) {
            application.gotoGameinterface(txtPlayername.getText());
        }
    }

    @FXML
    private void loadGame() {
        application.loadGame((Player) lstSaves.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void exitProg() {
        System.exit(0);
    }

}
