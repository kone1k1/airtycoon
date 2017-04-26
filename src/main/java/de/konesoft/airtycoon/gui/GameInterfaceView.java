package de.konesoft.airtycoon.gui;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameInterfaceView implements FxmlView<GameInterfaceViewModel>, Initializable {

    @InjectViewModel
    private GameInterfaceViewModel viewModel;

    @FXML
    private Label lblPlayerName;

    @FXML
    private Label lblPlayerMoney;

    @FXML
    private Label lblPlayerCredit;

    @FXML
    private Slider sldPlayerOrderCredit;

    @FXML
    private ListView lstBuyablePlanes;

    @FXML
    private Button btnGetCredit;

    @FXML
    private ListView lstPlayerFleet;

    @FXML
    private Label lblPlaneManufactor;

    @FXML
    private Label lblPlaneType;

    @FXML
    private Label lblKmCount;

    @FXML
    private ProgressBar pbFuel;

    @FXML
    private ProgressBar pbRepearState;

    @FXML
    private TextArea txtPlaneInfo;

    @FXML
    private ImageView imgPlane;

    @FXML
    private Slider sldFuel;

    @FXML
    private ChoiceBox cbFleet;

    @FXML
    private Label lblPosition;

    @FXML
    private Slider sldTicketPrice;

    @FXML
    private ProgressBar pbTicketCount;

    @FXML
    private ChoiceBox cbFlightTarget;

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
        imgPlane.imageProperty().bindBidirectional(viewModel.PlaneImgProperty());
        lblKmCount.textProperty().bind(viewModel.PlaneKmCountProperty());
        pbFuel.progressProperty().bind(viewModel.PlaneFuelProperty());
        txtPlaneInfo.textProperty().bind(viewModel.PlaneDescriptionProperty());
        pbRepearState.progressProperty().bind(viewModel.PlaneStateProperty());
        sldFuel.maxProperty().bind(viewModel.PlaneMaxFuelProperty());
        sldFuel.valueProperty().bindBidirectional(viewModel.PlaneTankFuelProperty());
        //FlyManagement
        cbFleet.setItems(viewModel.PlayerFleetProperty());
        viewModel.SelectedFlyPlaneProperty().bind(cbFleet.getSelectionModel().selectedItemProperty());
        lblPosition.textProperty().bind(viewModel.PlanePositionProperty());
        sldTicketPrice.valueProperty().bindBidirectional(viewModel.TicketPriceProperty());
        pbTicketCount.progressProperty().bind(viewModel.TicketSoldProperty());
        cbFlightTarget.setItems(viewModel.AirportsProperty());
        viewModel.SelectedTargetProperty().bind(cbFlightTarget.getSelectionModel().selectedItemProperty());
        cbFlightTarget.getSelectionModel().selectLast();
    }

    @FXML
    private void getCreditButtonPressed() {
        viewModel.orderCredit();
    }

    @FXML
    private void payCreditButtonPressed() {
        viewModel.payCredit();
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
        lstPlayerFleet.getSelectionModel().selectPrevious();
    }

    @FXML
    private void refuelPlaneButtonPressed() {
        viewModel.refuelPlane();
        lstPlayerFleet.getSelectionModel().selectPrevious();
    }

    @FXML
    private void flyPlaneButtonPressed() {
        viewModel.flyPlane();
    }
}
