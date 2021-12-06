package com.example.subd.Helpers;

import com.example.subd.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;

public class Utils {
    public static void setStage(Parent element, String url) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(url));
        Scene scene;
        try {
            scene = new Scene(loader.load());
        }
        catch (IOException e) {
            return;
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("subd");
        element.getScene().getWindow().hide();
        stage.show();
    }

    public static void showStage(String url) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(url));
        Scene scene;
        try {
            scene = new Scene(loader.load());
        }
        catch (IOException e) {
            return;
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("subd");
        stage.show();
    }
}
