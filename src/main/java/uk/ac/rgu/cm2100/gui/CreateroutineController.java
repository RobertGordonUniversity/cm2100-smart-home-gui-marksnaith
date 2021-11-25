/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import uk.ac.rgu.cm2100.Home;
import uk.ac.rgu.cm2100.commands.Command;
import uk.ac.rgu.cm2100.commands.Routine;
import uk.ac.rgu.cm2100.gui.mvc.Controller;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class CreateroutineController extends Controller<Home> implements Initializable {

    @FXML
    private ListView lstCommands;
    
    @FXML
    private ListView lstRoutine;
    
    @FXML
    private Button btnAddCommand;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private TextField txtRoutineName;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnAddCommandClicked(){
        String command = (String)this.lstCommands.getSelectionModel().getSelectedItem();
    
        this.lstRoutine.getItems().add(command);
    
    }
    
    @FXML
    private void btnSaveRoutineClicked() throws IOException{
        
        String name = this.txtRoutineName.getText();
        
        ObservableList<String> currentCommands = this.lstRoutine.getItems();
        
        
        List<Command> commands = currentCommands.stream()
                                    .map((c) -> this.model.getCommand(c))
                                    .collect(Collectors.toList());
       
        
        Routine r = new Routine(commands.toArray(new Command[0]));
        
        this.model.addRoutine(name, r);
        
        App.setRoot("homescreen", this.model);
    }

    @Override
    public void setModel(Home model) {
        this.model = model;
        
        ObservableList<String> commands = FXCollections.observableArrayList(new ArrayList<>(this.model.getCommands()));
        
        this.lstCommands.setItems(commands);
    }

    
    
    
    
}
