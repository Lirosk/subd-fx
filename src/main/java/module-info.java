module com.example.subd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.subd to javafx.fxml;
    exports com.example.subd;
    exports com.example.subd.Controllers;
    opens com.example.subd.Controllers to javafx.fxml;
    exports com.example.subd.Helpers;
    opens com.example.subd.Helpers to javafx.fxml;
}