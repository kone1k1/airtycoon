package de.konesoft.airtycoon;

import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.saxsys.mvvmfx.ViewTuple;

public class MainApp extends Application {

    public String test = "test";

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Airmanager Tycoon 0.1");
        
        //Für testzwecke direkt Spieler laden
        final ViewTuple viewTuple = FluentViewLoader.fxmlView(GameInterfaceView.class).load();

        final Parent root = viewTuple.getView();

        stage.setScene(new Scene(root));
        stage.show();
    }

}
