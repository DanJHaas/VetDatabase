package vet.db;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vet.db.database.Clinic;
import vet.db.database.Database;
import vet.db.pages.UIPage;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainApp extends Application {

    public final static int appWidth = 960;
    public final static int appHeight = 540;


    @Override
    public void start(Stage stage) {


        // example data base query and update
        Database.tryParseSchema("schema.sql");
        Clinic.add("pet central", "312 makeitup ave", "111-555-5555");
        Clinic.add("care palace", "342 parade st", "222-555-5555");

        // when doing table joins then you can create a custom record for passing the data around
        List<Clinic> listOfThings = Database.query("SELECT * FROM clinics", Clinic::fromRow);

        for (Clinic clinic : listOfThings) {
            System.out.println(clinic.clinicId());
            System.out.println(clinic.name());
            System.out.println(clinic.address());
            System.out.println(clinic.phoneNumber());
        }


        String password = "Password1";
        String password_wrong = "Password1";

        byte[] hash = BCrypt.with(BCrypt.Version.VERSION_2Y).hash(12, password.getBytes(StandardCharsets.UTF_8));
        String storedHash = new String(hash, StandardCharsets.UTF_8);
        System.out.println(storedHash);

        BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verify(password_wrong.getBytes(StandardCharsets.UTF_8), storedHash.getBytes(StandardCharsets.UTF_8));
        System.out.println(result.verified);

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