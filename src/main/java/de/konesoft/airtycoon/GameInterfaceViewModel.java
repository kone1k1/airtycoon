/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon;

import de.konesoft.airtycoon.model.Airplane;
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
    private final ListProperty<Airplane> buyablePlanes;
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
    private final DoubleProperty planeRefuel = new SimpleDoubleProperty();
    
    public GameInterfaceViewModel() {
        
        gameLogic.newPlayer("test");
        
        reloadFromModel();
        
        buyablePlanes = new SimpleListProperty<>(gameLogic.xmlAirplanes());
        selectedFleetPlane.addListener((observable, oldValue, newValue) -> {
            reloadOnSelection();
        });
    }
    
    public void orderCredit() {
        gameLogic.getPlayer().getAccount().orderCredit(playerOrderCredit.getValue());
        reloadFromModel();
    }
    
    public void buyPlane() {
        gameLogic.getPlayer().buy_plane((Airplane) selectedBuyPlane.get());
        reloadFromModel();
    }
    
    public void sellPlane() {
        gameLogic.getPlayer().sell_plane(selectedFleetPlane.get());
        reloadFromModel();
        reloadOnSelection();
    }
    
    public void repairPlane() {
        if (gameLogic.getPlayer().getAccount().transaction((int) (selectedFleetPlane.get().getPrice() * 0.1))) {
            selectedFleetPlane.get().repair();
            reloadFromModel();
            reloadOnSelection();
        }

        
    }
    
    public void reloadFromModel() {

        //SpielerManagement
        playerName.setValue(gameLogic.getPlayer().getName());
        playerMoney.setValue(NumberFormat.getInstance().format(gameLogic.getPlayer().getAccount().getMoney()) + " €");
        playerCredit.setValue(NumberFormat.getInstance().format(gameLogic.getPlayer().getAccount().getCredit()) + " €");

        //FleetManagement
        playerFleet.set(gameLogic.loadFleet());
        
    }
    
    public void reloadOnSelection() {
        
        if (selectedFleetPlane.get() != null) {
            
            planeKmCount.set(selectedFleetPlane.get().getFlightdistance() + " KM");
            planeManufactor.set(selectedFleetPlane.get().getManufacturer());
            planeType.set(selectedFleetPlane.get().getType());
            planeFuel.set((double) selectedFleetPlane.get().getFuel() / selectedFleetPlane.get().getMaxFuel());
            planeState.set(selectedFleetPlane.get().getRepearstate() / 127F);
            planeMaxFuel.set(selectedFleetPlane.get().getMaxFuel());
            planeFuel.set(selectedFleetPlane.get().getFuel());
        } else {
            
            planeKmCount.set("");
            planeManufactor.set("");
            planeType.set("");
            planeFuel.set(0);
            planeState.set(0);
            planeMaxFuel.set(40000);
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
    
}
