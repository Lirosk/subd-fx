module com.example.subd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.subd to javafx.fxml;
    exports com.example.subd;
}