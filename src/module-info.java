module VetDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens vet.db to javafx.fxml;
    opens vet.db.pages to javafx.fxml;
    exports vet.db;
}