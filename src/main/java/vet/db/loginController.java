package vet.db;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.IOException;
import vet.db.pages.UIPage;

/* This file is the controller for the Login page */


    public class loginController {
        @FXML
        private Button submitButton;


        /* uses UIpage to enter the application
        obviously needs the logic as any press of the login button
        will grant access to the website.
         */
        @FXML
        private void loginSubmit() {

            UIPage uiPage = new UIPage("main");
            Parent newRoot = uiPage.getPage();

            if (newRoot != null) {
                submitButton.getScene().setRoot(newRoot);
            }
        }

}
