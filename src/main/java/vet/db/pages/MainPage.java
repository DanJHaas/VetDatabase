package vet.db.pages;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import vet.db.Database;

import java.io.*;
import java.sql.DriverManager;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    // define fxids from SceneBuilder
    public ButtonBase btn1;
    public ButtonBase btn2;
    public Group rootGroup;
    public StackPane rootWindow;
    public VBox apt2;

    // example of adding entries to the expandable boxes
    // under the dropdown example tab
    // see buttons in the initialize() function below
    private TitledPane makePane() {
        TitledPane tp = new TitledPane();
        AnchorPane ap = new AnchorPane();
        Button btn = new Button("The Fun");

        ap.getChildren().add(btn);
        tp.setExpanded(false);
        tp.setText("test");
        tp.setContent(ap);
        return tp;
    }



    // handles scaling the window
    private void updateScale() {
        // stretchy window, but looks good when maximized
        double w = rootGroup.getLayoutBounds().getWidth();
        double h = rootGroup.getLayoutBounds().getHeight();

        rootGroup.setScaleX(rootWindow.getWidth() / w);
        rootGroup.setScaleY(rootWindow.getHeight() / h);
    }

    /**
     * This is easier than defining a method for each button.
     * Objects that can use this method are:
     * {@link ButtonBase}, {@link ChoiceBox}, {@link ComboBoxBase},
     * {@link ContextMenu}, {@link MenuItem}, {@link TextField}
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rootWindow.widthProperty().addListener((_, _, _) -> updateScale());
        rootWindow.heightProperty().addListener((_, _, _) -> updateScale());


        Database.update("create table if not exists example(id integer primary key, name varchar(50) not null);");
        Database.update("insert into example values(40, 'my_name_is_me');");
        Database.tryParseSchema("schema.sql");

        record Example(int id, String name) {}
        List<Example> listOfThings = Database.query("SELECT id,name FROM example",
                rs -> new Example(rs.getInt("id"), rs.getString("name"))
        );

        for (Example ex : listOfThings) {
            System.out.println(ex.id);
            System.out.println(ex.name);
        }


        Database.update("DROP TABLE example");




        btn1.setOnAction(_ -> {
            for (int i = 0; i < 20; i++) {
                apt2.getChildren().add(makePane());
            }
            System.out.println("Button1!");
        });

        btn2.setOnAction(_ -> {
            apt2.getChildren().add(makePane());
            System.out.println("Button2!");
        });
    }
}
