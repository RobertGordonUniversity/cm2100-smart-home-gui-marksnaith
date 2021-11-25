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

        if (device != null) {
            this.lblStatus.setText(device.getDeviceStatus().toString());
        }
    }

    @Override
    public void setModel(Home model) {
        this.model = model;

        /* Convert the devices from the Model into an ObservableList */
        ObservableList<Device> devices = FXCollections.observableArrayList(model.getDevices());

        /* Set the devices in the devices ListView */
        this.lstDevices.setItems(devices);

        /* Stream the commands from the Model to add buttons to the scene */
        this.model.getCommands().forEach((c) -> {
            
            /* Create a new button */
            Button btn = new Button(c);
            
            /* Assign an event handler */
            btn.setOnAction((evt) -> this.model.executeCommand(c));
            
            /* Add the button to the box of buttons */
            this.boxButtons.getChildren().add(btn);
        });

        /* Register a PropertyChangeSupportListener with the Model */
        this.model.addPropertyChangeSupportListener((evt) -> {
            if (evt.getPropertyName().equals("devices")) {
                
                /* Add the devices to the list of devices, converting to an observable ArrayList */
                this.lstDevices.setItems(FXCollections.observableArrayList(model.getDevices()));
                
                /* Emulate a mouse click to see the updated status */
                this.lstDevicesClicked();
            } else if (evt.getPropertyName().equals("commandsMap")) {
                
                /* Clear all the buttons in the box */
                this.boxButtons.getChildren().clear();
                
                /* Add all the buttons to the box, reflecting the updated model
                        - same method as above */
                this.model.getCommands().forEach((c) -> {
                    Button btn = new Button(c);
                    btn.setOnAction((evt2) -> this.model.executeCommand(c));
                    this.boxButtons.getChildren().add(btn);
                });
            }
        });
    }

}
