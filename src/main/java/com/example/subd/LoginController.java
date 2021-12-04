package com.example.subd;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
            if (Objects.equals(tfName.getText(), "")) {
                lMessage.setText("Cant be empty");
            }

            if (DBHelper.existsInTable(
                    DBHelper.Users.NAME,
                    DBHelper.Users.COLUMN_NAME,
                    tfName.getText()
            )) {
                tfName.clear();
            }
        });
        btnSignUp.setOnAction(actionEvent -> {
            Utils.setScene(btnSignUp, Urls.SIGNUP);
        });
    }

}
