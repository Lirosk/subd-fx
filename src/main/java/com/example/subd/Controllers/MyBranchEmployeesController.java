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
import com.example.subd.Models.Employee;
import com.example.subd.Models.EmployeeToBranch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MyBranchEmployeesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lBranch;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private Label lType;

    @FXML
    private ListView<String> lvEmployees;
    private final ArrayList<Employee> employees = new ArrayList<>();

    private Branch branch = null;

    @FXML
    void initialize() {
        lName.setText(LoginHelper.employee.name);

        setButtonsActions();

        branch = getBranch(LoginHelper.employee.id);
        if (branch == null) {
            lMessage.setText("Cannot determine your branch");
        }
        else {
            lBranch.setText(branch.name + "\n\t@" + branch.addr);
            setLvEmployees(branch.id);
        }

        lvEmployees.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lMessage.setText("");
            int index = lvEmployees.getSelectionModel().getSelectedIndex();
            lType.setText(employees.get(index).type);
        });
    }

    private void setLvEmployees(int branch_id) {
        employees.clear();
        lvEmployees.getItems().clear();

        String query = String.format(
                """
                        SELECT\s
                            b.employee_id,\s
                            b.employee_name,
                            b.employee_type
                        FROM
                            (SELECT * FROM employees_to_branches WHERE branch_id = %d) as a
                        INNER JOIN employees as b ON a.employee_id = b.employee_id
                        ORDER BY employee_name;
                """,
                branch_id);

        try {
            ResultSet res = DBHelper.executeWithResult(query);

            while (res.next()) {
                Employee employee = new Employee();
                employee.id = res.getInt(res.findColumn(DBHelper.Employees.COLUMN_ID));
                employee.name = res.getString(res.findColumn(DBHelper.Employees.COLUMN_NAME));
                employee.type = res.getString(res.findColumn(DBHelper.Employees.COLUMN_TYPE));

                employees.add(employee);
            }

            for (Employee employee: employees) {
                lvEmployees.getItems().add(employee.name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            lMessage.setText(e.getMessage());
        }
    }

    private Branch getBranch(int employee_id) {
        String query = String.format(
                """
                        SELECT\s
                            b.branch_id,
                            b.branch_name,\s
                            b.branch_addr\s
                        FROM
                            (
                                SELECT\s
                                    branch_id\s
                                FROM\s
                                    employees_to_branches\s
                                WHERE employee_id = %d
                            ) as a
                        INNER JOIN branches as b ON a.branch_id = b.branch_id;
                """,
                employee_id);
        try {

            ResultSet res = DBHelper.executeWithResult(query);

            if (res.next()) {
                Branch branch = new Branch();
                branch.id = res.getInt(res.findColumn(DBHelper.Branches.COLUMN_ID));
                branch.name = res.getString(res.findColumn(DBHelper.Branches.COLUMN_NAME));
                branch.addr = res.getString(res.findColumn(DBHelper.Branches.COLUMN_ADDR));

                return branch;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            lMessage.setText(e.getMessage());
        }
        return null;
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.EMPLOYEEMAINMENU);
        });
    }

}
