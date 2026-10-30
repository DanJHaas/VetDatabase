package vet.db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {

        GridPane gp = new GridPane();
        Scene sc = new Scene(gp, 256, 256);

        gp.setHgap(15);
        gp.add(new Label("Welcome to the vet db!"), 0, 0);
        gp.add(new Button("Click Me!"), 1, 0);

        Rectangle leftBorder = new Rectangle((sc.getWidth()/3),sc.getHeight(),Color.BLUEVIOLET);
        leftBorder.heightProperty().bind(sc.heightProperty());
        gp.getChildren().add(leftBorder);

        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}