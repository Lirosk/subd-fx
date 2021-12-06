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
import com.example.subd.Models.Employee;
import com.example.subd.Models.FutureGoal;
import com.example.subd.Models.FutureGoalToEmployee;
import com.example.subd.Models.Promotion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PromotionsController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lDescr;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private ListView<String> lvPromotions;
    private final ArrayList<Promotion> promotions = new ArrayList<>();


    @FXML
    void initialize() {
        lName.setText(LoginHelper.user.name);

        setButtonsActions();

        lvPromotions.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lMessage.setText("");
            int index = lvPromotions.getSelectionModel().getSelectedIndex();
            lDescr.setText(promotions.get(index).descr);
        });

        setLvPromotions();
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.USERMAINMENU);
        });
    }

    private void setLvPromotions() {
        lvPromotions.getItems().clear();
        String query = String.format("SELECT * FROM %s", DBHelper.Promotions.NAME);

        try {
            ResultSet res = DBHelper.executeWithResult(query);

            while (res.next()) {
                Promotion promotion = new Promotion();
                promotion.id = res.getInt(res.findColumn(DBHelper.Promotions.COLUMN_ID));
                promotion.name = res.getString(res.findColumn(DBHelper.Promotions.COLUMN_NAME));
                promotion.descr = res.getString(res.findColumn(DBHelper.Promotions.COLUMN_DESCR));

                promotions.add(promotion);
            }

            for (Promotion promotion: promotions) {
                lvPromotions.getItems().add(promotion.name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            lMessage.setText(e.getMessage());
        }
    }
}
