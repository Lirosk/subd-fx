package com.example.subd.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.subd.Helpers.DBHelper;
import com.example.subd.Helpers.LoginHelper;
import com.example.subd.Helpers.Urls;
import com.example.subd.Helpers.Utils;
import com.example.subd.Models.Book;
import com.example.subd.Models.Branch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class BooksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBookIt;

    @FXML
    private Label lMessage;

    @FXML
    private Label lName;

    @FXML
    private ListView<String> lvBooks;
    private ArrayList<Book> books = new ArrayList<>();

    @FXML
    private ListView<String> lvBranches;
//    private ArrayList<String> branches = new ArrayList<>();

    @FXML
    void initialize() {
        lName.setText(LoginHelper.user.name);

        setButtonsActions();

        setLvBooks();

        lvBooks.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            lvBranches.getItems().clear();
            int index = lvBooks.getSelectionModel().getSelectedIndex();

            for (Branch branch: books.get(index).branches) {
                lvBranches.getItems().add(branch.name + "\n\t" + branch.addr);
            }
        });
    }

    private void setLvBooks() {
//        """
//                SELECT
//                    a.book_id,
//                    title,
//                    author,
//                    b.branch_id,
//                    name,
//                    addr
//                FROM
//                    books as a
//                INNER JOIN
//                    books_to_branches as b ON a.book_id = b.book_id
//                INNER JOIN
//                    branches as c ON b.branch_id = c.branch_id
//                ORDER BY title;
//                """
        String query = String.format(
                """
                SELECT
                    a.%s,
                    %s,
                    %s,
                    b.%s,
                    %s,
                    %s
                FROM
                    %s as a
                INNER JOIN
                    %s as b ON a.%s = b.%s
                INNER JOIN
                    %s as c ON b.%s = c.%s
                ORDER BY %s;
                """,
                // SELECT
                /*a.*/DBHelper.Books.COLUMN_ID,
                DBHelper.Books.COLUMN_TITLE,
                DBHelper.Books.COLUMN_AUTHOR,
                /*b.*/DBHelper.Branches.COLUMN_ID,
                DBHelper.Branches.COLUMN_NAME,
                DBHelper.Branches.COLUMN_ADDR,
                // FROM
                DBHelper.Books.NAME, /* as a */
                // INNER JOIN
                DBHelper.BooksToBranches.NAME, /* as b ON a.*/ DBHelper.Books.COLUMN_ID, /* = b.*/ DBHelper.Books.COLUMN_ID,
                // INNER JOIN
                DBHelper.Branches.NAME, /* as c ON b.*/ DBHelper.Branches.COLUMN_ID, /* = c.*/ DBHelper.Branches.COLUMN_ID,
                // ORDER BY
                DBHelper.Books.COLUMN_TITLE
        );

        try {
            // selecting books
            ResultSet res = DBHelper.executeWithResult(query);
            while (res.next()) {
                int book_id = res.getInt(res.findColumn(DBHelper.Books.COLUMN_ID));
                String title = res.getString(res.findColumn(DBHelper.Books.COLUMN_TITLE));
                String author = res.getString(res.findColumn(DBHelper.Books.COLUMN_AUTHOR));

                Book book = new Book();
                book.id = book_id;
                book.title = title;
                book.author = ((author != null)? author : "");

                do {
                    if (Objects.equals(res.getString(res.findColumn(DBHelper.Books.COLUMN_TITLE)), book.title)) {
                        int branch_id = res.getInt(res.findColumn(DBHelper.Branches.COLUMN_ID));
                        String name = res.getString(res.findColumn(DBHelper.Branches.COLUMN_NAME));
                        String addr = res.getString(res.findColumn(DBHelper.Branches.COLUMN_ADDR));

                        Branch branch = new Branch();
                        branch.id = branch_id;
                        branch.name = name;
                        branch.addr = addr;

                        book.branches.add(branch);
                    }
                    else {
                        res.previous();
                        break;
                    }
                } while (res.next());

                books.add(book);
            }

            for (Book book: books) {
                lvBooks.getItems().add(book.author + " \"" + book.title + "\"");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
//            lMessage.setText("Unexpected error");
            lMessage.setText(e.getMessage());
        }
    }

    private void setButtonsActions() {
        btnBack.setOnAction(actionEvent -> {
            Utils.setStage(btnBack, Urls.USERMAINMENU);
        });

        btnBookIt.setOnAction(actionEvent -> {
            // TODO
        });
    }

}
