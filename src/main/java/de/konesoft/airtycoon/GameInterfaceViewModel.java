package de.konesoft.airtycoon;

import de.konesoft.airtycoon.GameLogic;
import de.konesoft.airtycoon.logic.functions.Calculator;
import de.konesoft.airtycoon.model.Airplane;
import de.konesoft.airtycoon.model.Airport;
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

public class GameInterfaceViewModel implements ViewModel {

    private final GameLogic gameLogic = new GameLogic();

    //Spieleramanagement
    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty playerMoney = new SimpleStringProperty();
    private final StringProperty playerCredit = new SimpleStringProperty();
    private ListProperty<Airplane> buyablePlanes;
    private final IntegerProperty playerOrderCredit = new SimpleIntegerProperty();
    private final ObjectProperty selectedBuyPlane = new SimpleObjectProperty();

    //FleetManagement
    private final ListProperty<Airplane> playerFleet = new SimpleListProperty<>();
    private final ObjectProperty<Airplane> selectedFleetPlane = new SimpleObjectProperty<>();
    private final StringProperty planeManufactor = new SimpleStringProperty();
    private final StringProperty planeType = new SimpleStringProperty();
    private final StringProperty planeKmCount = new SimpleStringProperty();
    private final DoubleProperty planeFuel = new SimpleDoubleProperty();
    private final DoubleProperty planeState = new SimpleDoubleProperty();
    private final IntegerProperty planeMaxFuel = new SimpleIntegerProperty();
    private final IntegerProperty planeTankFuel = new SimpleIntegerProperty();
    private final DoubleProperty planeRefuel = new SimpleDoubleProperty();

    //FlyManagement
    private final StringProperty planePosition = new SimpleStringProperty();
    private final ObjectProperty<Airplane> selectedFlyPlane = new SimpleObjectProperty<>();
    private final ObjectProperty<Airport> selectedTarget = new SimpleObjectProperty<>();
    private final ListProperty<Airport> airports = new SimpleListProperty<>();
    private final IntegerProperty ticketPrice = new SimpleIntegerProperty();
    private final DoubleProperty ticketSold = new SimpleDoubleProperty();

    public GameInterfaceViewModel() {

        gameLogic.newPlayer("mastercs");
        initLoad();
        reloadPlayerInterface();

        ticketPrice.addListener((observable, oldValue, newValue) -> {

            if (selectedFlyPlane.get() != null && selectedTarget.get() != selectedFlyPlane.get().getPosition()) {
                short buffer = Calculator.passangerAmount(selectedFlyPlane.get(), selectedTarget.get(), (short) ticketPrice.get());

                ticketSold.set(buffer / (double) selectedFlyPlane.get().getMax_pax());
            }

        });

        selectedFleetPlane.addListener((observable, oldValue, newValue) -> {
            reloadFleetManagementInterface();
        });
        selectedFlyPlane.addListener((observable, oldValue, newValue) -> {
            reloadFlyManagementInterface();
        });

    }

    // Alles was unveränderbar ist und initial geladen werden kann
    private void initLoad() {

        playerName.setValue(gameLogic.getPlayer().getName());
        airports.set(gameLogic.xmlAirports());
        buyablePlanes = new SimpleListProperty<>(gameLogic.xmlAirplanes());
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

            planeKmCount.set(selectedFleetPlane.get().getFlightdistance() + " KM");
            planeManufactor.set(selectedFleetPlane.get().getManufacturer());
            planeType.set(selectedFleetPlane.get().getType());
            planeFuel.set((double) selectedFleetPlane.get().getFuel() / selectedFleetPlane.get().getMaxFuel());
            planeState.set(selectedFleetPlane.get().getRepearstate() / 127F);
            planeMaxFuel.set(selectedFleetPlane.get().getMaxFuel());
            planeTankFuel.set(selectedFleetPlane.get().getFuel());

        } else {

            planeKmCount.set("");
            planeManufactor.set("");
            planeType.set("");
            planeFuel.set(0);
            planeState.set(0);
            planeMaxFuel.set(40000);
            planeTankFuel.set(0);
        }
    }

    private void reloadFlyManagementInterface() {

        if (selectedFlyPlane.get() != null) {
            planePosition.set("Position: " + selectedFlyPlane.get().getPosition().getName());
        } else {
            planePosition.set("Position:");

        }
    }

    public void orderCredit() {

        gameLogic.getPlayer().getAccount().orderCredit(playerOrderCredit.getValue());
        reloadPlayerInterface();
    }

    public void buyPlane() {

        if (selectedBuyPlane.get() != null) {
            gameLogic.getPlayer().buy_plane((Airplane) selectedBuyPlane.get());
            reloadPlayerInterface();
        }

    }

    public void sellPlane() {

        if (selectedFleetPlane.get() != null) {
            gameLogic.getPlayer().sell_plane(selectedFleetPlane.get());
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }

    }

    public void refuelPlane() {

        if (selectedFleetPlane.get() != null) {
            selectedFleetPlane.get().setFuel((short) planeTankFuel.get());
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }
    }

    public void repairPlane() {

        if (selectedFleetPlane.get() != null && gameLogic.getPlayer().getAccount().transaction((int) (selectedFleetPlane.get().getPrice() * 0.1))) {
            selectedFleetPlane.get().repair();
            reloadPlayerInterface();
            reloadFleetManagementInterface();
        }

    }

    public void flyPlane() {

        if (selectedFlyPlane.get() != null && selectedFlyPlane.get().getPosition() != selectedTarget.get()) {

            selectedFlyPlane.get().setPax(Calculator.passangerAmount(selectedFlyPlane.get(), selectedTarget.get(), (short) ticketPrice.get()));
            gameLogic.getPlayer().getAccount().deposit(selectedFlyPlane.get().getPax() * ticketPrice.get());
            selectedFlyPlane.get().fly(selectedTarget.get());
            reloadFlyManagementInterface();
            reloadFleetManagementInterface();
            reloadPlayerInterface();
        }

    }

    public ListProperty<Airplane> getBuyablePlanes() {
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

    public StringProperty PlaneKmCountProperty() {
        return planeKmCount;
    }

    public StringProperty PlaneTypeProperty() {
        return planeType;
    }

    public ObjectProperty<Airplane> SelectedFleetPlaneProperty() {
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
        return planePosition;
    }

    public ObjectProperty<Airplane> SelectedFlyPlaneProperty() {
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
