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
import javafx.scene.control.Label;

public class UserMainMenuController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnFutureGoals;

    @FXML
    private Button btnPromotions;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnSubscriptions;

    @FXML
    private Button btnDeleteAccount;

    @FXML
    private Label lName;

    @FXML
    void initialize() {
        lName.setText(LoginHelper.user.name);

        setButtonsActions();
    }

    private void setButtonsActions() {
        btnSignOut.setOnAction(actionEvent -> {
            LoginHelper.user = null;
            Utils.setStage(btnSignOut, Urls.LOGIN);
        });

        btnSubscriptions.setOnAction(actionEvent -> {
            Utils.setStage(btnSubscriptions, Urls.SUBSCRIPTIONS);
        });

        btnBooks.setOnAction(actionEvent -> {
            Utils.setStage(btnBooks, Urls.BOOKS);
        });

        btnPromotions.setOnAction(actionEvent -> {
            Utils.setStage(btnPromotions, Urls.PROMOTIONS);
        });

        btnFutureGoals.setOnAction(actionEvent -> {
            Utils.setStage(btnFutureGoals, Urls.FUTUREGOALS);
        });

        btnDeleteAccount.setOnAction(actionEvent -> {
            String query = String.format(
                    "DELETE FROM %s WHERE %s = '%s'",
                    DBHelper.Users.NAME,
                    DBHelper.Users.COLUMN_NAME, LoginHelper.user.name.replace("'", "''")
            );

            try {
                DBHelper.executeQuery(query);
                DBHelper.insertLog(LoginHelper.user.name + " with id " + LoginHelper.user.id + " has been deleted");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            LoginHelper.user = null;
            Utils.setStage(btnDeleteAccount, Urls.LOGIN);
        });
    }

}
