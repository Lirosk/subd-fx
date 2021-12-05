package com.example.subd.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
            Utils.setStage(btnBack, Urls.LOGIN);
        });
        btnSignUp.setOnAction(actionEvent -> {
            // if empty string
            if (Objects.equals(tfName.getText(), "")) {
                lMessage.setTextFill(Color.color(1, 0, 0));
                lMessage.setText("Cant be empty");
            }
            // if name too long
            else if (tfName.getText().length() > 20) {
                lMessage.setTextFill(Color.color(1, 0, 0));
                lMessage.setText("Max len is 20");
            }
            // if name is already taken
            else if (DBHelper.existsInTable(DBHelper.Users.NAME, DBHelper.Users.COLUMN_NAME, tfName.getText())) {
                lMessage.setTextFill(Color.color(1, 0, 0));
                lMessage.setText("Pick another name");
            }
            // if it is ok
            else {
                String query = String.format(
                        "INSERT INTO %s(%s) VALUES('%s');",
                        DBHelper.Users.NAME, DBHelper.Users.COLUMN_NAME,
                        tfName.getText().replace("'", "''")
                );

                try {
                    // insert user
                    DBHelper.executeQuery(query);
                    lMessage.setTextFill(Color.color(0, 1, 0));
                    lMessage.setText("Successfully signed up");
                    try {
                        // insert log
                        DBHelper.insertLog(tfName.getText() + " just signed up");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    lMessage.setTextFill(Color.color(1, 0, 0));
                    lMessage.setText(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

}
