package com.example.subd.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmployeeMainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogs;

    @FXML
    private Button btnMyBranchEmployees;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lName;

    @FXML
    void initialize() {
        lName.setText(LoginHelper.employee.name);

        setButtonsActions();

    }

    private void setButtonsActions(){
        btnSignOut.setOnAction(actionEvent -> {
            Utils.setStage(btnSignOut, Urls.LOGIN);
            LoginHelper.employee = null;
        });

        btnUsers.setOnAction(actionEvent -> {
            Utils.setStage(btnUsers, Urls.USERS);
        });

        btnMyBranchEmployees.setOnAction(actionEvent -> {
            Utils.setStage(btnMyBranchEmployees, Urls.MYBRANCHEMPLOYEES);
        });

        btnLogs.setOnAction(actionEvent -> {
            Utils.setStage(btnLogs, Urls.LOGS);
        });
    }

}
