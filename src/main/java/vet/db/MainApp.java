package vet.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Wrap this method in a page loading class Ex. UIPage(String pageName)
            // TODO: create UIPage(String pageName)
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vet/db/pages/main.fxml")));
            Scene sc = new Scene(root, 800, 600);


            stage.setMinWidth(800);
            stage.setMinHeight(500);

            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}