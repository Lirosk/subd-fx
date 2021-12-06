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
import com.example.subd.Models.Subscription;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class SubscriptionsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnTakeIt;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private Label lSubscriptionDescr;

    @FXML
    private Label lSubscriptionName;

    @FXML
    private Label lSubscriptionPrice;

    @FXML
    private ListView<String> lvSubscriptions;
    ArrayList<Subscription> subscriptions = new ArrayList<>();

    @FXML
    void initialize() {
        lName.setText(LoginHelper.user.name);

        setButtonsActions();

        setLvSubscriptions();
        lvSubscriptions.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            int selectedSubscriptionIndex = lvSubscriptions.getSelectionModel().getSelectedIndex();
            Subscription selectedSubscription = subscriptions.get(selectedSubscriptionIndex);
            lSubscriptionName.setText(selectedSubscription.name);
            lSubscriptionDescr.setText(selectedSubscription.descr);
            lSubscriptionPrice.setText(Integer.toString(selectedSubscription.price));
            lMessage.setText("");
        });
    }

    private void setLvSubscriptions() {
        try {
            ResultSet res = DBHelper.executeWithResult(
                    String.format(
                            "SELECT %s, %s, %s, %s FROM %s;",
                            DBHelper.Subscriptions.COLUMN_ID, DBHelper.Subscriptions.COLUMN_NAME, DBHelper.Subscriptions.COLUMN_DESCR, DBHelper.Subscriptions.COLUMN_PRICE,
                            DBHelper.Subscriptions.NAME
                    )
            );

            while (res.next()){
                int id = res.getInt(res.findColumn(DBHelper.Subscriptions.COLUMN_ID));
                String name = res.getString(res.findColumn(DBHelper.Subscriptions.COLUMN_NAME));
                String descr = res.getString(res.findColumn(DBHelper.Subscriptions.COLUMN_DESCR));
                int price = res.getInt(res.findColumn(DBHelper.Subscriptions.COLUMN_PRICE));

                Subscription subscription = new Subscription();
                subscription.id = id;
                subscription.name = name;
                subscription.descr = descr;
                subscription.price = price;

                subscriptions.add(subscription);
            }

            res.close();

            for (Subscription subscription : subscriptions) {
                lvSubscriptions.getItems().add(subscription.name);
            }
        }
        catch (SQLException e) {
            lMessage.setText("Error on selecting subscriptions");
            e.printStackTrace();
        }
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.USERMAINMENU);
        });

        btnTakeIt.setOnAction(actionEvent -> {
            // if nothing selected
            int index = lvSubscriptions.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                lMessage.setTextFill(Color.color(1, 0, 0));
                lMessage.setText("Nothing selected");
            }
            else
            {
                // insert user to subscription
                String query = String.format(
                        "UPDATE %s SET %s = %d WHERE %s = %d;",
                        DBHelper.UsersToSubscriptions.NAME,
                        DBHelper.UsersToSubscriptions.COLUMN_SUBSCRIPTION_ID, subscriptions.get(index).id,
                        DBHelper.UsersToSubscriptions.COLUMN_USER_ID, LoginHelper.user.id
                );
                try {
                    // inserting to users_to_subscriptions
                    DBHelper.executeQuery(query);
                    try {
                        // logging
                        DBHelper.insertLog(LoginHelper.user.name + " changed subscription to one with id " + subscriptions.get(index).id);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    lMessage.setTextFill(Color.color(1, 0, 0));
                    lMessage.setText("Unexpected error");
                }
                lMessage.setTextFill(Color.color(0, 1, 0));
                lMessage.setText("You successfully changed the subscription");
            }
        });
    }

}
