package com.example.subd.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.Models.User;
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
            // check for user in users
            else {
                ResultSet res = null;
                try {
                    res = DBHelper.executeWithResult(
                            String.format(
                                    "SELECT * FROM %s WHERE %s = '%s'",
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

                        // insert log
                        try {
                            DBHelper.insertLog(tfName.getText() + " logged");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        // set new stage
                        Utils.setStage(btnLogin, Urls.USERMAINMENU);
                    }
                    //there is not such user
                    else {
                        lMessage.setText("You need to be signed up");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        btnSignUp.setOnAction(actionEvent -> {
            Utils.setStage(btnSignUp, Urls.SIGNUP);
        });
    }

}
