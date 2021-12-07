package com.example.subd.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.Models.Branch;
import com.example.subd.Models.Subscription;
import com.example.subd.Models.User;
import com.example.subd.Models.UserToSubscription;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private Label lSubscription;

    @FXML
    private ListView<String> lvUsers;
    private final ArrayList<UserToSubscription> usersToSubscriptions = new ArrayList<>();

    @FXML
    void initialize() {
        lName.setText(LoginHelper.employee.name);

        setButtonsActions();

        setLvUsers();

        lvUsers.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lMessage.setText("");
            int index = lvUsers.getSelectionModel().getSelectedIndex();

            UserToSubscription userToSubscription = usersToSubscriptions.get(index);
            lSubscription.setText((userToSubscription.subscription != null ? userToSubscription.subscription.name : "none"));
        });
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.EMPLOYEEMAINMENU);
        });
    }

    private void setLvUsers() {
        usersToSubscriptions.clear();
        lvUsers.getItems().clear();

        String query = """
                SELECT
                	a.user_id,
                    a.user_name,
                    c.subscription_id,
                    c.subscription_name,
                    c.subscription_descr
                FROM\s
                	users as a
                LEFT JOIN
                	users_to_subscriptions as b ON a.user_id = b.user_id
                LEFT JOIN
                	subscriptions as c ON b.subscription_id = c.subscription_id
                ORDER BY user_name;
                """;
        try {
            ResultSet res = DBHelper.executeWithResult(query);

            while (res.next()) {
                User user = new User();
                user.id = res.getInt(res.findColumn(DBHelper.Users.COLUMN_ID));
                user.name = res.getString(res.findColumn(DBHelper.Users.COLUMN_NAME));

                Subscription subscription = new Subscription();
                subscription.id = res.getInt(res.findColumn(DBHelper.Subscriptions.COLUMN_ID));
                subscription.name = res.getString(res.findColumn(DBHelper.Subscriptions.COLUMN_NAME));
                subscription.descr = res.getString(res.findColumn(DBHelper.Subscriptions.COLUMN_DESCR));

                if (subscription.id == 0 && subscription.name == null && subscription.descr == null) {
                    subscription = null;
                }

                UserToSubscription userToSubscription = new UserToSubscription();
                userToSubscription.user = user;
                userToSubscription.subscription = subscription;

                usersToSubscriptions.add(userToSubscription);
            }

            for (UserToSubscription userToSubscription: usersToSubscriptions){
                lvUsers.getItems().add(userToSubscription.user.name);
            }
        }
        catch (SQLException e){
            lMessage.setText(e.getMessage());
            e.printStackTrace();
        }
    }

}
