package com.example.subd.Controllers;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.Models.Branch;
import com.example.subd.Models.Employee;
import com.example.subd.Models.FutureGoal;
import com.example.subd.Models.FutureGoalToEmployee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FutureGoalsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lAuthor;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private ListView<String> lvFutureGoals;
    private final ArrayList<FutureGoalToEmployee> futureGoalsToEmployees = new ArrayList<>();

    @FXML
    void initialize() {
        lName.setText(LoginHelper.user.name);

        setButtonsActions();

        lvFutureGoals.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lMessage.setText("");
            int index = lvFutureGoals.getSelectionModel().getSelectedIndex();
            lAuthor.setText(futureGoalsToEmployees.get(index).employee.name);
        });

        setLvSubscriptions();
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.USERMAINMENU);
        });
    }

    private void setLvSubscriptions() {
        String query =
                """
                                SELECT\s
                                     b.future_goal_id,
                                     a.descr,
                                     c.employee_id,
                                     c.name
                                FROM\s
                                    future_goals as a
                                INNER JOIN\s
                                    future_goals_to_employees as b ON a.future_goal_id = b.future_goal_id
                                INNER JOIN\s
                                    employees as c ON c.employee_id = b.employee_id
                                ORDER BY a.descr;
                        """;

        try {
            lvFutureGoals.getItems().clear();

            ResultSet res = DBHelper.executeWithResult(query);

            while (res.next()) {
                FutureGoal futureGoal = new FutureGoal();
                futureGoal.id = res.getInt(res.findColumn(DBHelper.FutureGoals.COLUMN_ID));
                futureGoal.descr = res.getString(res.findColumn(DBHelper.FutureGoals.COLUMN_DESCR));

                Employee employee = new Employee();
                employee.id = res.getInt(res.findColumn(DBHelper.Employees.COLUMN_ID));
                ;
                employee.name = res.getString(res.findColumn(DBHelper.Employees.COLUMN_NAME));

                FutureGoalToEmployee futureGoalToEmployee = new FutureGoalToEmployee();
                futureGoalToEmployee.futureGoal = futureGoal;
                futureGoalToEmployee.employee = employee;

                futureGoalsToEmployees.add(futureGoalToEmployee);
            }

            for (FutureGoalToEmployee futureGoalToEmployee : futureGoalsToEmployees) {
                lvFutureGoals.getItems().add(futureGoalToEmployee.futureGoal.descr);
            }
        } catch (SQLException e) {
            lMessage.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
