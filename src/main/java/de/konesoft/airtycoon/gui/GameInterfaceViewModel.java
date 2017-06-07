package de.konesoft.airtycoon.gui;

import de.konesoft.airtycoon.GameLogic;
import de.konesoft.airtycoon.functions.Calculator;
import de.konesoft.airtycoon.model.Airliner;
import de.konesoft.airtycoon.model.Airport;
import de.konesoft.airtycoon.model.Map;
import de.saxsys.mvvmfx.ViewModel;
import java.text.NumberFormat;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class GameInterfaceViewModel implements ViewModel {

    private final GameLogic gameLogic = GameLogic.getSingletonGameLogic();

    //Spieleramanagement
    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty playerMoney = new SimpleStringProperty();
    private final StringProperty playerCredit = new SimpleStringProperty();
    private final ListProperty buyablePlanes = new SimpleListProperty(gameLogic.xmlAirplanes());
    private final IntegerProperty playerOrderCredit = new SimpleIntegerProperty();
    private final ObjectProperty selectedBuyPlane = new SimpleObjectProperty();

    //FleetManagement
    private final ListProperty<Airliner> playerFleet = new SimpleListProperty<>();
    private final ObjectProperty<Airliner> selectedFleetPlane = new SimpleObjectProperty<>();
    private final StringProperty planeManufactor = new SimpleStringProperty();
    private final StringProperty planeType = new SimpleStringProperty();
    private final StringProperty planeKmCount = new SimpleStringProperty();
    private final StringProperty planeDescription = new SimpleStringProperty();
    private final ObjectProperty<Image> planeImg = new SimpleObjectProperty<>();
    private final DoubleProperty planeFuel = new SimpleDoubleProperty();
    private final DoubleProperty planeState = new SimpleDoubleProperty();
    private final IntegerProperty planeMaxFuel = new SimpleIntegerProperty();
    private final IntegerProperty planeTankFuel = new SimpleIntegerProperty();
    private final DoubleProperty planeRefuel = new SimpleDoubleProperty();

    //FlyManagement
    private final StringProperty planePositionLabel = new SimpleStringProperty();
    private final ObjectProperty<Airliner> selectedFlyPlane = new SimpleObjectProperty<>();
    private final ObjectProperty<Airport> selectedTarget = new SimpleObjectProperty<>();
    private final ListProperty<Airport> airports = new SimpleListProperty<>();
    private final IntegerProperty ticketPrice = new SimpleIntegerProperty();
    private final DoubleProperty ticketSold = new SimpleDoubleProperty();

    public GameInterfaceViewModel() {

        initLoad();
        reloadPlayerInterface();

        ticketPrice.addListener((observable, oldValue, newValue) -> {

            if (selectedFlyPlane.get() != null && selectedTarget.get().getPosition() != selectedFlyPlane.get().getPosition()) {
                short buffer = Calculator.calcPassengerAmount(selectedFlyPlane.get(), selectedTarget.get(), (short) ticketPrice.get());
                ticketSold.set(buffer / (double) selectedFlyPlane.get().getMaxPassengers());
            }
        });
        selectedFleetPlane.addListener((observable, oldValue, newValue) -> {
            reloadFleetManagementInterface();
        });
        selectedFlyPlane.addListener((observable, oldValue, newValue) -> {
            reloadFlyManagementInterface();
        });
    }

    private void initLoad() {

        gameLogic.newPlayer("mastercs");
        playerName.setValue(gameLogic.getPlayer().getName());
        airports.set(gameLogic.xmlAirports());
    }

    private void reloadPlayerInterface() {

        //SpielerManagement
        playerMoney.setValue(NumberFormat.getInstance().format(gameLogic.getPlayer().getAccount().getMoney()) + " €");
        playerCredit.setValue(NumberFormat.getInstance().format(gameLogic.getPlayer().getAccount().getCredit()) + " €");

        //FleetManagement
        playerFleet.set(gameLogic.loadFleet());
    }

    private void reloadFleetManagementInterface() {

        if (selectedFleetPlane.get() != null) {
            planeKmCount.set(selectedFleetPlane.get().getFlightDistance() + " km");
            planeManufactor.set(selectedFleetPlane.get().getManufactor());
            planeType.set(selectedFleetPlane.get().getType());
            planeFuel.set((double) selectedFleetPlane.get().getFuel() / selectedFleetPlane.get().getMaxFuel());
            planeState.set(selectedFleetPlane.get().getRepearState() / 127F);
            planeDescription.set(selectedFleetPlane.get().getDescription());
            planeMaxFuel.set(selectedFleetPlane.get().getMaxFuel());
            planeTankFuel.set(selectedFleetPlane.get().getFuel());
            planeImg.set(new Image("/img/" + selectedFleetPlane.get().getType() + ".jpg"));
        } else {
            planeKmCount.set("");
            planeManufactor.set("");
            planeType.set("");
            planeFuel.set(0);
            planeDescription.set("");
            planeState.set(0);
            planeMaxFuel.set(40000);
            planeTankFuel.set(0);
            planeImg.set(new Image("/img/default.jpg"));
        }
    }

    private void reloadFlyManagementInterface() {

        if (selectedFlyPlane.get() != null) {
            planePositionLabel.set("Position: " + selectedFlyPlane.get().getPosition().getLatitude());
        } else {
            planePositionLabel.set("Position:");
        }
    }

    public void orderCredit() {

        gameLogic.getPlayer().getAccount().orderCredit(playerOrderCredit.getValue());
        reloadPlayerInterface();
    }

    public void payCredit() {

        gameLogic.getPlayer().getAccount().returnCredit(playerOrderCredit.getValue());
        reloadPlayerInterface();
    }

    public void buyPlane() {

        if (selectedBuyPlane.get() != null) {
            gameLogic.getPlayer().buyPlane((Airliner) selectedBuyPlane.get());
            reloadPlayerInterface();
        }
    }

    public void sellPlane() {

        if (selectedFleetPlane.get() != null) {
            gameLogic.getPlayer().sellPlane(selectedFleetPlane.get());
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }
    }

    public void refuelPlane() {

        if (selectedFleetPlane.get() != null) {
            selectedFleetPlane.get().refuel(planeTankFuel.get());
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }
    }

    public void repairPlane() {

        if (selectedFleetPlane.get() != null && gameLogic.getPlayer().getAccount().pay((int) (selectedFleetPlane.get().getPrice() * 0.1))) {
            selectedFleetPlane.get().repair();
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }

    }

    public void flyPlane() {
        Map.getMapInstance().drawMap();
        if (selectedFlyPlane.get() != null && selectedFlyPlane.get().getPosition() != selectedTarget.get().getPosition()) {
            selectedFlyPlane.get().loadPassengers(Calculator.calcPassengerAmount(selectedFlyPlane.get(), selectedTarget.get(), (short) ticketPrice.get()));
            gameLogic.getPlayer().getAccount().deposit(selectedFlyPlane.get().getPassengers() * ticketPrice.get());
            selectedFlyPlane.get().fly(selectedTarget.get().getPosition());
            reloadFlyManagementInterface();
            reloadFleetManagementInterface();
            reloadPlayerInterface();
        }

    }

    public ListProperty getBuyablePlanes() {
        return buyablePlanes;
    }

    public IntegerProperty PlayerOrderCreditProperty() {
        return playerOrderCredit;
    }

    public StringProperty PlayerNameProperty() {
        return playerName;
    }

    public StringProperty PlayerMoneyProperty() {
        return playerMoney;
    }

    public StringProperty PlayerCreditProperty() {
        return playerCredit;
    }

    public ListProperty PlayerFleetProperty() {
        return playerFleet;
    }

    public ObjectProperty SelectedBuyPlaneProperty() {
        return selectedBuyPlane;
    }

    public StringProperty PlaneManufactorProperty() {
        return planeManufactor;
    }

    public ObjectProperty<Image> PlaneImgProperty() {
        return planeImg;
    }

    public StringProperty PlaneKmCountProperty() {
        return planeKmCount;
    }

    public StringProperty PlaneTypeProperty() {
        return planeType;
    }

    public StringProperty PlaneDescriptionProperty() {
        return planeDescription;
    }

    public ObjectProperty<Airliner> SelectedFleetPlaneProperty() {
        return selectedFleetPlane;
    }

    public DoubleProperty PlaneFuelProperty() {
        return planeFuel;
    }

    public DoubleProperty PlaneStateProperty() {
        return planeState;
    }

    public DoubleProperty PlaneRefuelProperty() {
        return planeRefuel;
    }

    public IntegerProperty PlaneMaxFuelProperty() {
        return planeMaxFuel;
    }

    public IntegerProperty PlaneTankFuelProperty() {
        return planeTankFuel;
    }

    public StringProperty PlanePositionProperty() {
        return planePositionLabel;
    }

    public ObjectProperty<Airliner> SelectedFlyPlaneProperty() {
        return selectedFlyPlane;
    }

    public ListProperty<Airport> AirportsProperty() {
        return airports;
    }

    public IntegerProperty TicketPriceProperty() {
        return ticketPrice;
    }

    public DoubleProperty TicketSoldProperty() {
        return ticketSold;
    }

    public ObjectProperty<Airport> SelectedTargetProperty() {
        return selectedTarget;
    }

}
