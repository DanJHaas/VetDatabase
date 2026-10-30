package vet.db.pages;

import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.ContextMenu;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    public ButtonBase btn1;
    public ButtonBase btn2;

    /**
     * This is easier than defining a method for each button.
     * Objects that can use this method are:
     * {@link ButtonBase}, {@link ChoiceBox}, {@link ComboBoxBase},
     * {@link ContextMenu}, {@link MenuItem}, {@link TextField}
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn1.setOnAction(actionEvent -> {
            System.out.println("Button1!");
        });

        btn2.setOnAction(actionEvent -> {
            System.out.println("Button2!");
        });
    }
}
