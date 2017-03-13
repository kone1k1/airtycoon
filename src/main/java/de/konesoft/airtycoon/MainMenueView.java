/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainMenueView implements FxmlView<MainMenueViewModel>, Initializable {

    @FXML
    TextField playerNameTextField;

    @FXML
    ListView saveGameListView;

    @FXML
    Button loadGameButton;

    @FXML
    Button newGameButton;

    @FXML
    Button exitButton;

    @InjectViewModel
    private MainMenueViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        playerNameTextField.textProperty().bindBidirectional(viewModel.PlayerName());
        saveGameListView.itemsProperty().bindBidirectional(viewModel.SaveList());
        viewModel.SelectetPlayer().bind(saveGameListView.getSelectionModel().selectedItemProperty());
        newGameButton.disableProperty().bind(viewModel.getNewCommand().executableProperty().not());
        loadGameButton.disableProperty().bind(viewModel.getLoadCommand().executableProperty().not());

    }

    @FXML
    public void loadButtonPressed() {

        viewModel.getLoadCommand().execute();
    }

    @FXML
    public void newButtonPressed() {
        viewModel.getNewCommand().execute();
        final ViewTuple viewTuple = FluentViewLoader.fxmlView(GameInterfaceView.class).load();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.setScene(new Scene(viewTuple.getView()));
    }

    @FXML
    public void exitButtonPressed() {

        viewModel.getExitCommand().execute();
    }

}
