package com.example.subd.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.subd.Helpers.LoginHelper;
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
    private Button btnMyFutureGoals;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lName;

    @FXML
    void initialize() {
        lName.setText(LoginHelper.employee.name);

    }

}
