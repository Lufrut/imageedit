package com.limoges.imageedit;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    @FXML
    private Label welcomeText;
    private static ComboBox<String> pictureComboBox;
    private static ImageView pictureView;

    public static void fileReader(Stage stage, Button button, String username) throws Exception {


        pictureComboBox = new ComboBox<>();
        Button loadButton = new Button("Load");
        pictureView = new ImageView();

        // set the button action
        loadButton.setOnAction(event -> {
            String selectedPicture = pictureComboBox.getValue();
            if (selectedPicture != null) {
                Path picturePath = Paths.get("pictures", username, selectedPicture);
                Image image = new Image(picturePath.toUri().toString());
                double scale = Math.min(1, Math.min(900 / image.getWidth(), 430 / image.getHeight()));
                pictureView.setImage(image);
                pictureView.setFitWidth(scale * image.getWidth());
                pictureView.setFitHeight(scale * image.getHeight());
                pictureView.setImage(image);
            }
        });

        // populate the combo box with the pictures in the directory
        List<String> pictureNames = getPictureNames(username);
        pictureComboBox.setItems(FXCollections.observableList(pictureNames));

        // layout the controls
        HBox root = new HBox(10, pictureComboBox, loadButton,button);
        root.setAlignment(Pos.TOP_LEFT);
        // create the scene and show the stage
        StackPane root2 = new StackPane(pictureView);
        root2.setAlignment(Pos.CENTER);
        root2.getChildren().add(root);
        Scene scene = new Scene(root2,900, 500);

        stage.setScene(scene);
        stage.show();
        button.setOnAction(e -> {

            try {
                FileReaderController.saveImageToUserFolder(username);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });


    }
    private static List<String> getPictureNames(String username) {
        List<String> pictureNames = new ArrayList<>();
        Path picturesDir = Paths.get("pictures", username);
        if (Files.isDirectory(picturesDir)) {
            File[] pictureFiles = picturesDir.toFile().listFiles();
            if (pictureFiles != null) {
                for (File pictureFile : pictureFiles) {
                    if (pictureFile.isFile()) {
                        pictureNames.add(pictureFile.getName());
                    }
                }
            }
        }
        return pictureNames;
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}