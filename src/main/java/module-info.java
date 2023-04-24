module com.limoges.imageedit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.limoges.imageedit to javafx.fxml;
    exports com.limoges.imageedit;
    exports com.limoges.imageedit.models;
    opens com.limoges.imageedit.models to javafx.fxml;
}