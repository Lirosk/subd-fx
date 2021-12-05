package com.example.subd.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.Models.Subscription;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SubscriptionsController {

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
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.USERMAINMENU);
        });

        setLvSubscriptions();
        lvSubscriptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int selectedSubsriptionIndex= lvSubscriptions.getSelectionModel().getSelectedIndex();
                Subscription selectedSubscription = subscriptions.get(selectedSubsriptionIndex);
                lSubscriptionName.setText(selectedSubscription.name);
                lSubscriptionDescr.setText(selectedSubscription.descr);
                lSubscriptionPrice.setText(Integer.toString(selectedSubscription.price));
            }
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

}
