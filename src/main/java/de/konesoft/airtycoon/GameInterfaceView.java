/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

public class GameInterfaceView implements FxmlView<GameInterfaceViewModel>, Initializable {

    @InjectViewModel
    private GameInterfaceViewModel viewModel;

    @FXML
    Label lblPlayerName;

    @FXML
    Label lblPlayerMoney;

    @FXML
    Label lblPlayerCredit;

    @FXML
    Slider sldPlayerOrderCredit;

    @FXML
    ListView lstBuyablePlanes;

    @FXML
    Button btnGetCredit;

    @FXML
    ListView lstPlayerFleet;

    @FXML
    Label lblPlaneManufactor;

    @FXML
    Label lblPlaneType;

    @FXML
    Label lblKmCount;

    @FXML
    ProgressBar pbFuel;

    @FXML
    ProgressBar pbRepearState;

    @FXML
    Slider sldFuel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //PlayerManagement
        lstBuyablePlanes.setItems(viewModel.getBuyablePlanes());
        lblPlayerName.textProperty().bind(viewModel.PlayerNameProperty());
        lblPlayerMoney.textProperty().bind(viewModel.PlayerMoneyProperty());
        lblPlayerCredit.textProperty().bind(viewModel.PlayerCreditProperty());
        sldPlayerOrderCredit.valueProperty().bindBidirectional(viewModel.PlayerOrderCreditProperty());
        viewModel.SelectedBuyPlaneProperty().bind(lstBuyablePlanes.getSelectionModel().selectedItemProperty());
        //FleetManagement
        lstPlayerFleet.setItems(viewModel.PlayerFleetProperty());
        viewModel.SelectedFleetPlaneProperty().bind(lstPlayerFleet.getSelectionModel().selectedItemProperty());
        lblPlaneManufactor.textProperty().bind(viewModel.PlaneManufactorProperty());
        lblPlaneType.textProperty().bind(viewModel.PlaneTypeProperty());
        lblKmCount.textProperty().bind(viewModel.PlaneKmCountProperty());
        pbFuel.progressProperty().bind(viewModel.PlaneFuelProperty());
        pbRepearState.progressProperty().bind(viewModel.PlaneStateProperty());
        sldFuel.maxProperty().bind(viewModel.PlaneMaxFuelProperty());
        sldFuel.valueProperty().bindBidirectional(viewModel.PlaneFuelProperty());
    }

    @FXML
    private void getCreditButtonPressed() {
        viewModel.orderCredit();

    }

    @FXML
    private void buyPlaneButtonPressed() {
        viewModel.buyPlane();

    }

    @FXML
    private void sellPlaneButtonPressed() {
        viewModel.sellPlane();

    }

    @FXML
    private void repairPlaneButtonPressed() {
        viewModel.repairPlane();

    }
}
