package vet.db;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.TextField;
import vet.db.database.Database;
import vet.db.pages.UIPage;

/* This file is the controller for the Login page */

    public class loginController {
        @FXML
        private TextField username;
        @FXML
        private TextField password;

        @FXML
        private Button submitButton;


        /* ON entering a correct password and username it switches the loader to the main page.
           Can and will make this better, is just a proof of concept until we have users and such.
           The way it parses and gets information is clunky but it does work
           TODO::I did put a users table in the db to test this so we will have to drop that
         */
        @FXML
        private void loginSubmit() {
            //In a try block for now because if the query fails ie: no username It outputs to console wrong username or password
            try {
                String queryString = "SELECT username,password_hash FROM users WHERE username = '%s'";
                record loginValidation(String username, String password){};
                List<loginValidation> login = Database.query(String.format(queryString,username.getText()),
                        rs-> new loginValidation(rs.getString("username"),rs.getString("password_hash")));

                if(login.get(0).password.equals(password.getText())){
                    UIPage uiPage = new UIPage("main");
                    Parent newRoot = uiPage.getPage();

                    if (newRoot != null) {
                        submitButton.getScene().setRoot(newRoot);
                    }

                }else{
                    System.out.println("WRONG PASSWORD");
                };

            }catch (Exception e){
                System.out.println("WRONG PASSWORD OR USERNAME");

            }


        }

}
