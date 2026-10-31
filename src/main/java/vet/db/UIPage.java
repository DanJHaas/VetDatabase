package vet.db;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class UIPage {

    private String pageName;


    public UIPage(String pageName) {
        this.pageName = pageName;
    }

    private String getQualifiedPageName() {
        return String.format("/vet/db/pages/%s.fxml", pageName);
    }

    public Parent getPage() throws IOException {
        try {

            URL page = getClass().getResource(getQualifiedPageName());
            return FXMLLoader.load(Objects.requireNonNull(page));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
