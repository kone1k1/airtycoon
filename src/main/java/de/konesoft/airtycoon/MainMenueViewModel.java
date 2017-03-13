/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon;

import de.konesoft.airtycoon.model.Player;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainMenueViewModel implements ViewModel {

    private final GameLogic gameLogic = new GameLogic();
    private final StringProperty playerName = new SimpleStringProperty("");
    private final ObjectProperty selectetPlayer = new SimpleObjectProperty();
    private final ListProperty saveList;

    private final Command loadCommand;
    private final Command newCommand;
    private final Command exitCommand;

    public MainMenueViewModel() {
        saveList = new SimpleListProperty(gameLogic.loadSavegames());

        loadCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                System.out.println("Spiele laden");
                gameLogic.setPlayer((Player) selectetPlayer.get());

            }
        }, selectetPlayer.isNotNull(), true);

        newCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                System.out.println("Neues Spiel");
                gameLogic.newPlayer(playerName.get());

            }
        }, playerName.greaterThan(""), true);

        exitCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                System.exit(0);
            }
        }, true);
    }

    public ObjectProperty SelectetPlayer() {
        return selectetPlayer;
    }

    public ListProperty SaveList() {
        return saveList;
    }

    public StringProperty PlayerName() {
        return playerName;
    }

    public String getPlayerName() {
        return playerName.get();
    }

    private void loadGame() {

    }

    public void newGame() {

    }

    public Command getLoadCommand() {
        return loadCommand;
    }

    public Command getNewCommand() {
        return newCommand;
    }

    public Command getExitCommand() {
        return exitCommand;
    }

}
