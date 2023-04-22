package com.limoges.imageedit;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileReader {
    @FXML
    private Label welcomeText;


    public static void fileReader(Stage stage, Button button){

        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );

            fileChooser.setTitle("Open Image File");
            File initialDirectory = new File(System.getProperty("user.home"),"Pictures");

            if (initialDirectory.exists()){
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                // Load the selected image into an ImageView object
                Image image = new Image(selectedFile.toURI().toString());
                ImageView imageView = new ImageView(image);

                // Create a StackPane layout and add the ImageView to it
                StackPane root = new StackPane();
                root.getChildren().add(imageView);

                // Create a Scene and set the StackPane layout as the root
                Scene scene = new Scene(root, 960, 600);

                // Set the Scene on the Stage
                stage.setScene(scene);
            }
        });


    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}