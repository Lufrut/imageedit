package com.limoges.imageedit;

import com.limoges.imageedit.models.DataBase;
import com.limoges.imageedit.models.SetUpDatabase;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // here we setup database, it's always throw exception because it create table each time
        try {
            Connection conn = DataBase.connectDb();
            SetUpDatabase.setUp(conn);
            assert conn != null;
            conn.close();
        } catch ( SQLException e){
            System.out.println(e);
        }
        AuthRegistrationForm form = new AuthRegistrationForm();
        form.start(stage);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}