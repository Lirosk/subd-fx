package com.example.subd.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserMainMenuController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnBranches;

    @FXML
    private Button btnFutureGoals;

    @FXML
    private Button btnPromotions;

    @FXML
    private Button btnSubscriptions;

    @FXML
    private Label lName;

    @FXML
    void initialize() {
        lName.setText(LoginHelper.name);

        btnSubscriptions.setOnAction(actionEvent -> {
            Utils.setStage(btnSubscriptions, Urls.SUBSCRIPTIONS);
        });
    }

}
