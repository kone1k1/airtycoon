/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import functions.Calculator;
import main.Airplane;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import main.Airport;

/**
 * FXML Controller class
 *
 * @author mastercs
 */
public class GameInterfaceController implements Initializable {

    private AirTycoon application;

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

    @FXML
    ChoiceBox cbFleet;

    @FXML
    ChoiceBox cbFlightTarget;

    @FXML
    Slider sldTicketPrice;

    @FXML
    Label lblPosition;

    @FXML
    ProgressBar pbTicketCount;

    public void setApp(AirTycoon application) {

        this.application = application;
        initialUpdate();
        updateFlyInterface();
        updateFleetInterface();
        updatePlayerInterface();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // ListView der Flotte des Spieler
        lstFleet.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            updateFleetInterface();
        });
        // ComboBox der Flotte der Spieler (Flugansicht)
        cbFleet.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            updatePosition();
        });
        sldTicketPrice.valueProperty().addListener((observable) -> {
            updateTicketCount();
        });

    }

    //Alles was nicht veränderbar ist und initial geladen werden soll
    private void initialUpdate() {

        /* Spieler Ansicht */
        lblPlayerName.setText(application.getPlayer().getName());
        lstPlanes.setItems(application.loadXmlAirplanes());

        /* Flug Anicht */
        cbFleet.setItems(application.loadFleet());
        if (cbFleet.getItems() != null) {
            cbFleet.getSelectionModel().select(0);
        }
        cbFlightTarget.setItems(application.loadXmlAirports());
        if (cbFlightTarget.getItems() != null) {
            cbFlightTarget.getSelectionModel().select(0);
        }

    }

    private void updatePlayerInterface() {

        lblPlayerBank.setText(NumberFormat.getInstance().format(application.getPlayer().getAccount().getMoney()) + " €");
        lblCredit.setText(NumberFormat.getInstance().format(application.getPlayer().getAccount().getCredit()) + " €");
        lstFleet.getItems().clear();
        lstFleet.setItems(application.loadFleet());

    }

    private void updateFleetInterface() {

        if (lstFleet.getSelectionModel().getSelectedItem() != null) {

            lblPlaneManufactor.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getManufacturer());
            lblPlaneType.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getType());
            lblKmCount.setText(NumberFormat.getInstance().format(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getFlightdistance()) + " km");
            txtPlaneInfo.setText(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getTextinfo());
            sldFuel.setMax(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getMaxFuel());
            sldFuel.setValue(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getFuel());
            pbRepearState.setProgress(application.getPlayer().getAirplane(lstFleet.getSelectionModel().getSelectedIndex()).getRepearstate() / 127F);
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

    private void updateFlyInterface() {

        cbFleet.getItems().clear();
        cbFleet.setItems(application.loadFleet());
        if (cbFleet.getItems() != null) {
            cbFleet.getSelectionModel().select(0);
        }
    }

    private void updatePosition() {

        if (cbFleet.getSelectionModel().getSelectedItem() != null) {

            lblPosition.setText("Standort: " + application.getPlayer().getAirplane(cbFleet.getSelectionModel().getSelectedIndex()).getPosition().getName());
        } else {

            lblPosition.setText("Standort:");
        }

    }

    private void updateTicketCount() {
        if (!cbFlightTarget.getSelectionModel().getSelectedItem().equals(application.getPlayer().getAirplane(cbFleet.getSelectionModel().getSelectedIndex()).getPosition())) {
            float buffer = (Calculator.passangerAmount((Airplane) cbFleet.getSelectionModel().getSelectedItem(), (Airport) cbFlightTarget.getSelectionModel().getSelectedItem(), (short) sldTicketPrice.getValue()));
            pbTicketCount.setProgress(buffer / application.getPlayer().getAirplane(cbFleet.getSelectionModel().getSelectedIndex()).getMax_pax());
        }

    }

    @FXML
    private void buyPlane() {

        if (lstPlanes.getSelectionModel().getSelectedItem() != null) {

            application.getPlayer().buy_plane((Airplane) lstPlanes.getSelectionModel().getSelectedItem());
            updatePlayerInterface();
            updateFlyInterface();
        }
    }

    @FXML
    private void getCredit() {

        application.getPlayer().getAccount().orderCredit((int) sldCredit.getValue());
        updatePlayerInterface();
    }

    @FXML
    private void sellPlane() {

        if (lstFleet.getSelectionModel().getSelectedItem() != null) {

            application.getPlayer().sell_plane((Airplane) lstFleet.getSelectionModel().getSelectedItem());
            updatePlayerInterface();
            updateFlyInterface();
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

    @FXML
    private void flyPlane() {

        if (cbFleet.getSelectionModel().getSelectedItem() != null) {

            application.getPlayer().getAirplane(cbFleet.getSelectionModel().getSelectedIndex()).fly((Airport) cbFlightTarget.getSelectionModel().getSelectedItem());
            updatePosition();
            updateFleetInterface();
        }

    }
}
