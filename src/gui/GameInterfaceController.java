/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import elements.Airplane;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
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
    ListView lstFleet;

    @FXML
    ListView lstPlanes;

    @FXML
    TextArea txtPlaneInfo;

    @FXML
    Label lblPlayerName;

    @FXML
    Label lblPlayerBank;

    @FXML
    Label lblCredit;

    @FXML
    Label lblPlaneType;

    @FXML
    Label lblPlaneManufactor;

    @FXML
    Label lblKmCount;

    @FXML
    Slider sldCredit;

    @FXML
    ProgressBar pbFuel;

    @FXML
    ProgressBar pbRepearState;

    @FXML
    Slider sldFuel;

    public void setApp(Air_Tycoon application) {
        this.application = application;
        updateInterface();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ListView der Flotte des Spieler
        lstFleet.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            updateFleetInterface();
        });
    }

    private void updateInterface() {
        lblPlayerName.setText(application.getPlayer().getName());
        lblPlayerBank.setText(NumberFormat.getInstance().format(application.getPlayer().getAccount().getMoney()) + " €");
        lblCredit.setText(NumberFormat.getInstance().format(application.getPlayer().getAccount().getCredit()) + " €");
        lstFleet.getItems().clear();
        lstPlanes.getItems().clear();
        lstFleet.setItems(application.loadFleet());
        lstPlanes.setItems(application.loadXmlAirplanes());
    }

    private void updateFleetInterface() {
        if (lstFleet.getSelectionModel().getSelectedItem() != null) {
            lblPlaneManufactor.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getManufacturer());
            lblPlaneType.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getType());
            lblKmCount.setText(NumberFormat.getInstance().format(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getFlightdistance()) + " km");
            txtPlaneInfo.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getTextinfo());
            sldFuel.setMax(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getMaxFuel());
            sldFuel.setValue(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getFuel());
            pbRepearState.setProgress(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getRepearstate() / 127);
            pbFuel.setProgress(sldFuel.getValue() / sldFuel.getMax());
        } else {
            lblPlaneManufactor.setText("");
            lblPlaneType.setText("");
            lblKmCount.setText("");
            txtPlaneInfo.setText("");
            sldFuel.setValue(0);
            pbRepearState.setProgress(0);
            pbFuel.setProgress(0);
        }
    }

    @FXML
    private void buyPlane() {
        if (lstPlanes.getSelectionModel().getSelectedItem() != null) {
            application.getPlayer().buy_plane((Airplane) lstPlanes.getSelectionModel().getSelectedItem());
            updateInterface();
        }
    }

    @FXML
    private void getCredit() {
        application.getPlayer().getAccount().orderCredit((int) sldCredit.getValue());
        updateInterface();
    }

    @FXML
    private void sellPlane() {
        if (lstFleet.getSelectionModel().getSelectedItem() != null) {
            application.getPlayer().sell_plane((Airplane) lstFleet.getSelectionModel().getSelectedItem());
            updateInterface();
        }
    }

    @FXML
    private void refuelPlane() {
        if (lstFleet.getSelectionModel().getSelectedItem() != null) {
            application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).setFuel((short) sldFuel.getValue());
            updateFleetInterface();
        }
    }

    @FXML
    private void repairPlane() {
        if (lstFleet.getSelectionModel().getSelectedItem() != null) {
            application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).repair();
            updateFleetInterface();
        }

    }
}
