module VetDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens vet.db to javafx.fxml;
    opens vet.db.pages to javafx.fxml;
    exports vet.db;
}