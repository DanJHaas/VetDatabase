module VetDB {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires bcrypt;
    requires bytes;
    requires org.slf4j;

    opens vet.db to javafx.fxml;
    opens vet.db.pages to javafx.fxml;
    exports vet.db;
    exports vet.db.database;
    opens vet.db.database to javafx.fxml;
    exports vet.db.pages;
}