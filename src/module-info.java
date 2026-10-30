module VetDB {
    requires javafx.controls;
    requires javafx.fxml;

    exports vet.db;
    opens vet.db to javafx.fxml;
}