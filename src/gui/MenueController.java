/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author mastercs
 */
public class MenueController implements Initializable {

    @FXML
    private void exitProg() {
        txt_area.setText("Hallo Welt");
    }
    @FXML
    TextArea txt_area;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
