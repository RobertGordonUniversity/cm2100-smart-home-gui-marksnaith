/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import uk.ac.rgu.cm2100.Home;
import uk.ac.rgu.cm2100.devices.Device;
import uk.ac.rgu.cm2100.gui.mvc.Controller;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class HomescreenController extends Controller<Home> implements Initializable {

    @FXML
    private ListView lstDevices;

    @FXML
    private Button btnCreateRoutine;

    @FXML
    private Label lblStatus;
    
    @FXML
    private HBox boxButtons;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnCreateRoutineClicked() throws IOException {
        App.setRoot("createroutine", this.model);
    }

    @FXML
    private void lstDevicesClicked() {
        Device device = (Device) this.lstDevices.getSelectionModel().getSelectedItem();

        this.lblStatus.setText(device.getDeviceStatus().toString());
    }

    @Override
    public void setModel(Home model) {
        this.model = model;

        ObservableList<Device> devices = FXCollections.observableArrayList(model.getDevices());

        this.lstDevices.setItems(devices);

        this.model.getCommands().forEach((c) -> {
            Button btn = new Button(c);
            btn.setOnAction((evt) -> this.model.executeCommand(c));
            this.boxButtons.getChildren().add(btn);
        });
        
        this.model.addPropertyChangeSupportListener((evt) -> {
            if(evt.getPropertyName().equals("devices")){
                this.lstDevices.setItems(FXCollections.observableArrayList(model.getDevices()));
                this.lstDevicesClicked();
            }
        });
    }

}
