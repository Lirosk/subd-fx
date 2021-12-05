package com.example.subd.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            // if it is ok
            else if (DBHelper.existsInTable(
                    DBHelper.Users.NAME,
                    DBHelper.Users.COLUMN_NAME,
                    tfName.getText()
            )) {
                // insert  log
                try {
                    DBHelper.insertLog(tfName.getText() + " logged");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // move to next stage
                LoginHelper.name = tfName.getText();
                Utils.setStage(btnLogin, Urls.USERMAINMENU);
            }
            else {
                lMessage.setText("You need to be signed up");
            }
        });
        btnSignUp.setOnAction(actionEvent -> {
            Utils.setStage(btnSignUp, Urls.SIGNUP);
        });
    }

}
