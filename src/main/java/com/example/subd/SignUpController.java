package com.example.subd;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lMessage;

    @FXML
    private TextField tfName;

    @FXML
    void initialize() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setScene(btnBack, Urls.LOGIN);
        });
        btnSignUp.setOnAction(actionEvent -> {
            if (Objects.equals(tfName.getText(), "")) {
                lMessage.setText("Cant be empty");
            }
            else if (tfName.getText().length() > 20) {
                lMessage.setText("Max len is 20");
            }
            else if (DBHelper.existsInTable(DBHelper.Users.NAME, DBHelper.Users.COLUMN_NAME, tfName.getText())) {
                lMessage.setText("Pick another name");
            }
            else {
                String query = String.format(
                        "INSERT INTO %s(%s) VALUES('%s');",
                        DBHelper.Users.NAME, DBHelper.Users.COLUMN_NAME,
                        tfName.getText().replace("'", "''")
                );
                try {
                    DBHelper.executeQuery(query);
                    lMessage.setText("Successfully signed up");
                    try {
                        DBHelper.insertLog(tfName.getText() + " just signed up");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    lMessage.setText(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

}
