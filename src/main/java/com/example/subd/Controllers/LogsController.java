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
import com.example.subd.Models.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class LogsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private TextArea taDescr;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private ListView<String> lvLogs;
    private final ArrayList<Log> logs = new ArrayList<>();

    @FXML
    void initialize() {
        lName.setText(LoginHelper.employee.name);

        setButtonsActions();

        lvLogs.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lMessage.setText("");
            int index = lvLogs.getSelectionModel().getSelectedIndex();

            taDescr.setText(logs.get(index).descr);
        });

        setLvLogs();
    }

    private void setLvLogs() {
        lvLogs.getItems().clear();
        logs.clear();

        String query = "SELECT * FROM logs ORDER BY log_at DESC;";

        try {
            ResultSet res = DBHelper.executeWithResult(query);

            while (res.next()) {
                Log log = new Log();
                log.id = res.getInt(res.findColumn(DBHelper.Logs.COLUMN_ID));
                log.at = res.getString(res.findColumn(DBHelper.Logs.COLUMN_AT));
                log.descr = res.getString(res.findColumn(DBHelper.Logs.COLUMN_DESCR));

                logs.add(log);
            }

            for (Log log: logs) {
                lvLogs.getItems().add(log.at);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            lMessage.setText(e.getMessage());
        }
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.EMPLOYEEMAINMENU);
        });
    }
}
