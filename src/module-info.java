module VetDB {
    requires javafx.controls;
    requires javafx.fxml;

    opens vet.db to javafx.fxml;
    opens vet.db.pages to javafx.fxml;
    exports vet.db;
}