package com.limoges.imageedit;

import com.limoges.imageedit.models.DataBase;
import com.limoges.imageedit.models.SetUpDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
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
        /*
        Button button = new Button("Select File");
        FileReader.fileReader(stage,button);

        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setTitle("Hello!");


        stage.setScene(scene);
        */

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}