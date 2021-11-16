package uk.ac.rgu.cm2100.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import uk.ac.rgu.cm2100.gui.mvc.Controller;
import uk.ac.rgu.cm2100.gui.mvc.Model;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        scene = new Scene(loadFXML("primary", null), 640, 480);
        scene.getStylesheets().add("styles/default.css"); //adds the default stylesheet to overcome character encoding issues on some platforms
        
        stage.setScene(scene);
        stage.show();
    }


    static void setRoot(String fxml, Model model) throws IOException {
        
        /* quick hack to strip the .fxml extension if provided by mistake */
        if (fxml.endsWith(".fxml")) {
            fxml = fxml.split(".")[0];
        }
        
        /* Use the loadFXML method to get the root (Parent) and set the model */
        scene.setRoot(loadFXML(fxml, model));
    }

    private static Parent loadFXML(String fxml, Model model) throws IOException {

        /* Create the FXMLLoader and load the given fxml file */
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        /* Load the fxml into a parent */
        Parent parent = fxmlLoader.load();

        /* Get the controller and set the model */
        Controller controller = fxmlLoader.getController();
        controller.setModel(model);

        /* Return the parent for rendering in the scene */
        return parent;
    }

    public static void main(String[] args) {
        launch();
    }

}