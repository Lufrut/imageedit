package com.limoges.imageedit;
import com.limoges.imageedit.models.DataBase;
import com.limoges.imageedit.models.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthRegistrationForm extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the authentication form
        GridPane authPane = new GridPane();
        authPane.setPadding(new Insets(10));
        authPane.setHgap(10);
        authPane.setVgap(10);

        Label authLabel = new Label("Authentication");
        authPane.add(authLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        authPane.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        authPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        authPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        authPane.add(passwordField, 1, 2);

        Button authButton = new Button("Authenticate");
        authButton.setOnAction(event -> {
            // Authenticate user with username and password
            Connection conn = DataBase.connectDb();
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (authenticate(username, password,conn)) {
                    // here we go to other scene
                    Button button = new Button("Select File");
                    FileReader.fileReader(primaryStage,button,username);

                } else {
                    // Show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Authentication");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password.");
                    alert.showAndWait();
                }
                assert conn !=null;
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        authPane.add(authButton, 0, 3, 2, 1);

        // Create the registration form
        GridPane regPane = new GridPane();
        regPane.setPadding(new Insets(10));
        regPane.setHgap(10);
        regPane.setVgap(10);

        Label regLabel = new Label("Registration");
        regPane.add(regLabel, 0, 0, 2, 1);

        Label newUsernameLabel = new Label("New username:");
        regPane.add(newUsernameLabel, 0, 1);

        TextField newUsernameField = new TextField();
        regPane.add(newUsernameField, 1, 1);

        Label newPasswordLabel = new Label("New password:");
        regPane.add(newPasswordLabel, 0, 2);

        PasswordField newPasswordField = new PasswordField();
        regPane.add(newPasswordField, 1, 2);

        Button regButton = new Button("Register");
        regButton.setOnAction(event -> {
            // Register new user with username and password
            Connection conn = DataBase.connectDb();
            String newUsername = newUsernameField.getText();
            String newPassword = newPasswordField.getText();
            try {
                if (register(newUsername, newPassword,conn)) {
                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration successful!");
                    alert.showAndWait();
                } else {
                    // Show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("Username already exists.");
                    alert.showAndWait();
                }
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        regPane.add(regButton, 0, 3, 2, 1);

        // Create the main scene with both forms
        TabPane tabPane = new TabPane();

        Tab authTab = new Tab("Authenticate", authPane);
        Tab regTab = new Tab("Register", regPane);
        tabPane.getTabs().addAll(authTab, regTab);

        Scene scene = new Scene(tabPane, 400, 300);

        primaryStage.setTitle("Auth and Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean authenticate(String username, String password, Connection conn) throws SQLException {
        User user = User.getUserByUsername(conn,username);
        if(user != null ) {
            return username.equals(user.getUsername()) && password.equals(user.getPassword());
        } else return false;
    }

    private boolean register(String username, String password, Connection conn) throws SQLException {
        if (User.getUserByUsername(conn,username)== null) {
            User user = new User(username, password);
            user.save(conn);
            return true;
        } else return  false;
    }
}