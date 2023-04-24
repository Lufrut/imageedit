package com.limoges.imageedit;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoadImage extends Application {
    private ComboBox<String> pictureComboBox;
    private ImageView pictureView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create the UI controls
        pictureComboBox = new ComboBox<>();
        Button loadButton = new Button("Load");
        pictureView = new ImageView();

        // set the button action
        loadButton.setOnAction(event -> {
            String selectedPicture = pictureComboBox.getValue();
            if (selectedPicture != null) {
                Path picturePath = Paths.get("pictures", "username", selectedPicture);
                Image image = new Image(picturePath.toUri().toString());
                pictureView.setImage(image);
            }
        });

        // populate the combo box with the pictures in the directory
        List<String> pictureNames = getPictureNames();
        pictureComboBox.setItems(FXCollections.observableList(pictureNames));

        // layout the controls
        HBox root = new HBox(10, pictureComboBox, loadButton, pictureView);
        root.setAlignment(Pos.CENTER);

        // create the scene and show the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String> getPictureNames() throws Exception {
        List<String> pictureNames = new ArrayList<>();
        Path picturesDir = Paths.get("pictures", "username");
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
}
