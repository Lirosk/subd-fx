package com.example.subd.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class TimeToChooseWhoYouAreController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnUser;

    @FXML
    void initialize() {
        setButtonsActions();

    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            LoginHelper.user = null;
            LoginHelper.employee = null;
            Utils.setStage(btnBack, Urls.LOGIN);
        });

        btnUser.setOnAction(actionEvent -> {
            LoginHelper.employee = null;
            Utils.setStage(btnUser, Urls.USERMAINMENU);
            try {
                DBHelper.insertLog(LoginHelper.user.name + " user logged");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btnEmployee.setOnAction(actionEvent -> {
            LoginHelper.user = null;
            Utils.setStage(btnEmployee, Urls.EMPLOYEEMAINMENU);
            try {
                DBHelper.insertLog(LoginHelper.employee.name + " employee logged");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
