package com.example.subd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {
    public static void setScene(Parent element, String url) {
        element.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Utils.class.getResource(url));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
