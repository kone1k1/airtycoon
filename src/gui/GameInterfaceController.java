/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author mastercs
 */
public class GameInterfaceController implements Initializable {

    private Air_Tycoon application;

    @FXML
    ListView fleetList;

    @FXML
    TextArea txtPlaneInfo;

    @FXML
    Label lblPlayerName;

    @FXML
    Label lblPlayerBank;

    @FXML
    Slider sldCredit;

    public void setApp(Air_Tycoon application) {
        this.application = application;
        updateInterface();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void updateInterface() {
        lblPlayerName.setText(application.getPlayer().getName());
        lblPlayerBank.setText(Integer.toString(application.getPlayer().getAccount().getMoney()));
    }
}
