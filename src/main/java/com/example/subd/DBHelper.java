package com.example.subd;

import java.sql.*;

public class DBHelper {
    protected static String dbHost = "localhost";
    protected static String dbPort = "3306";
    protected static String dbUser = "root";
    protected static String dbPassword = "7693882";
    protected static String dbName = "subd";

    public static Connection getConnection(){
        Connection conn;

        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/subd", "root", "7693882");
            conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPassword);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet executeWithResult(String query) throws SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    public static void executeQuery(String query) throws SQLException {
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    public static boolean existsInTable(String tableName, String column, String value){
        String query = String.format(
                "SELECT * FROM %s WHERE %s='%s'",
                tableName,
                column, value.replace("'", "''"
                ));

        try (Connection conn = DBHelper.getConnection(); Statement statement = conn.createStatement()){
//            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.next();// resultSet.next() && resultSet.getInt(String.format("COUNT(%s)", column)) > 0;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static void insertLog(String msg) throws SQLException {
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        String query = String.format("INSERT INTO %s(%s, %s) VALUES('%s', '%s')",
                Logs.NAME, Logs.COLUMN_AT, Logs.COLUMN_DESCR,
                currentTime, msg.replace("'", "''"));

        executeQuery(query);
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Branches {
        public static final String NAME = "branches";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDR = "addr";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Promotions {
        public static final String NAME = "promotions";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCR = "descr";
    }

    ////////////////////////////////////////////////////////////////////////////////////


    public static class Subscriptions {
        public static final String NAME = "subscriptions";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCR = "descr";
        public static final String COLUMN_PRICE = "price";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Users {
        public static final String NAME = "users";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Books {
        public static final String NAME = "books";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Employees {
        public static final String NAME = "employees";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class FutureGoals {
        public static final String NAME = "future_goals";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESCR = "descr";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class Logs {
        public static final String NAME = "logs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_AT = "at";
        public static final String COLUMN_DESCR = "descr";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class UsersToEmployees {
        public static final String NAME = "users_to_employees";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class EmployeesToBranches {
        public static final String NAME = "employees_to_branch";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_EMPLOYEE_ID = "employee_id";
        public static final String COLUMN_BRANCH_ID = "branch_id";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class FutureGoalsToEmployees {
        public static final String NAME = "future_goals_to_employees";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_FUTURE_GOAL_ID = "future_goal_id";
        public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static class BooksToBranches {
        public static final String NAME = "books_to_branches";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_BRANCH_ID = "branch_id";
    }

    ////////////////////////////////////////////////////////////////////////////////////
}
