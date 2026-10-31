package vet.db.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    public ButtonBase btn1;
    public ButtonBase btn2;
    public Group rootGroup;
    public StackPane rootWindow;
    public VBox apt2;


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
    /**
     * This is easier than defining a method for each button.
     * Objects that can use this method are:
     * {@link ButtonBase}, {@link ChoiceBox}, {@link ComboBoxBase},
     * {@link ContextMenu}, {@link MenuItem}, {@link TextField}
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rootWindow.widthProperty().addListener((obs, oldVal, newVal) -> updateScale());
        rootWindow.heightProperty().addListener((obs, oldVal, newVal) -> updateScale());

        btn1.setOnAction(actionEvent -> {
            for (int i = 0; i < 20; i++) {
                apt2.getChildren().add(makePane());
            }
            System.out.println("Button1!");
        });

        btn2.setOnAction(actionEvent -> {
            apt2.getChildren().add(makePane());
            System.out.println("Button2!");
        });
    }


    private void updateScale() {
        // stretchy window, but looks good when maximized
        double w = rootGroup.getLayoutBounds().getWidth();
        double h = rootGroup.getLayoutBounds().getHeight();

        rootGroup.setScaleX(rootWindow.getWidth() / w);
        rootGroup.setScaleY(rootWindow.getHeight() / h);
    }
}
