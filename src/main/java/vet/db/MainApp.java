package vet.db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vet.db.pages.UIPage;

import java.io.IOException;

public class MainApp extends Application {

    private final static int appWidth = 960;
    private final static int appHeight = 540;

    public static int getAppHeight() {
        return appHeight;
    }

    public static int getAppWidth() {
        return appWidth;
    }

    @Override
    public void start(Stage stage) {


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

        UIPage page = new UIPage("login");
        Scene sc = new Scene(page.getPage(), appWidth, appHeight);


        stage.setMinWidth(appWidth);
        stage.setMinHeight(appHeight);

        stage.setScene(sc);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}