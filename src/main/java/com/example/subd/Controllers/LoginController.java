package com.example.subd.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.MainApplication;
import com.example.subd.Models.Employee;
import com.example.subd.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lMessage;

    @FXML
    private TextField tfName;

    @FXML
    void initialize() {
        btnLogin.setOnAction(actionEvent -> {
            // if name is empty
            if (Objects.equals(tfName.getText(), "")) {
                lMessage.setText("Cant be empty");
            }
            else {
                ResultSet res = null;
                String unformatted_query = "SELECT * FROM %s WHERE %s = '%s'";
                // check in users
                try {
                    res = DBHelper.executeWithResult(
                            String.format(
                                    unformatted_query,
                                    DBHelper.Users.NAME, DBHelper.Users.COLUMN_NAME,
                                    tfName.getText().replace("'", "''")
                            )
                    );

                    // if it is ok
                    if (res.next()) {
                        User user = new User();
                        user.id = res.getInt(res.findColumn(DBHelper.Users.COLUMN_ID));
                        user.name = res.getString(res.findColumn(DBHelper.Users.COLUMN_NAME));
                        LoginHelper.user = user;

//                        // insert log
//                        try {
//                            DBHelper.insertLog(tfName.getText() + " user logged");
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//
//                        // set new stage
//                        Utils.setStage(btnLogin, Urls.USERMAINMENU);
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                // check in employees
                try {
                    res = DBHelper.executeWithResult(
                            String.format(
                                    unformatted_query,
                                    DBHelper.Employees.NAME, DBHelper.Employees.COLUMN_NAME,
                                    tfName.getText().replace("'", "''")
                            )
                    );

                    // if it ok
                    if (res.next()) {
                        Employee employee = new Employee();
                        employee.id = res.getInt(res.findColumn(DBHelper.Employees.COLUMN_ID));
                        employee.name = res.getString(res.findColumn(DBHelper.Employees.COLUMN_NAME));
                        employee.type = res.getString(res.findColumn(DBHelper.Employees.COLUMN_TYPE));
                        LoginHelper.employee = employee;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

                if (LoginHelper.user == null && LoginHelper.employee == null) {
                    lMessage.setText("You must be signed up");
                }
                else if (LoginHelper.user != null && LoginHelper.employee != null) {
                    Utils.setStage(btnLogin, Urls.TIME_TO_CHOOSE_WHO_YOU_ARE);
                }
                else if (LoginHelper.user != null) {
                    Utils.setStage(btnLogin, Urls.USERMAINMENU);
                }
                else if (LoginHelper.employee != null) {
                    Utils.setStage(btnLogin, Urls.EMPLOYEEMAINMENU);
                }
            }

        });
        btnSignUp.setOnAction(actionEvent -> {
            Utils.setStage(btnSignUp, Urls.SIGNUP);
        });
    }

}
