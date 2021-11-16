module uk.ac.rgu.cm2100.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;

    opens uk.ac.rgu.cm2100.gui to javafx.fxml;
    exports uk.ac.rgu.cm2100.gui;
}
