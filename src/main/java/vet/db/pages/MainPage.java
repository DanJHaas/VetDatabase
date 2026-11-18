package vet.db.pages;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.DriverManager;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        /*
            #TODO: Abstract database away into a more readable form
            class VetDatabase
                String dbUrl = "jdbc:sqlite:my.db"; // we can change the db name
                updateQuery(String query); // this will be for creating tables if needed
                executeQuery(String query) -> ResultSet; // this is for select queries (see example below)

         */
        String dbUrl = "jdbc:sqlite:my.db";

        try (var conn = DriverManager.getConnection(dbUrl)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

            String[] queries = new String[] {
                    "create table example(id integer primary key, name varchar(50) not null);",
                    "insert into example values(40, 'my_name_is_me');",
                    "select * from example;"};

            for(String q : queries) {
                System.out.println("Query: " + q);

                try (PreparedStatement statement = conn.prepareStatement(q)) {
                    if(q.startsWith("create") || q.startsWith("insert")) {
                        statement.executeUpdate();
                    } else {
                        ResultSet results = statement.executeQuery();
                        while(results.next()) {
                            System.out.println(results.getString("name"));
                        }
                    }

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }



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
