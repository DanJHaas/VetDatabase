package vet.db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private int appWidth = 960;
    private int appHeight = 540;

    public int getAppHeight() {
        return appHeight;
    }

    public int getAppWidth() {
        return appWidth;
    }

    @Override
    public void start(Stage stage) throws IOException {


        /*
        * Need a class to hold program state Ex. AppState()
        * should ideally be a static class that is repopulated
        * on a new login
        *
        * Need to see how she wants us to handle Databases
        * before I can make a concrete proposal for state management
        *
        * AppState appState = new AppState()
        *
        * appState getters and setters
        *
        * maybe stores maps and arrays of data to populate
        * the MainPage controller states
        * */
        //TODO: make a dataclass that holds the overall state of the program

        UIPage page = new UIPage("main");
        Scene sc = new Scene(page.getPage(), getAppWidth(), getAppHeight());


        stage.setMinWidth(getAppWidth());
        stage.setMinHeight(getAppHeight());

        stage.setScene(sc);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}