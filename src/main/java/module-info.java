module com.limoges.imageedit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.limoges.imageedit to javafx.fxml;
    exports com.limoges.imageedit;
}