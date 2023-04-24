package com.limoges.imageedit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;


public class FileReaderController{

    public static void saveImageToUserFolder(String username) throws IOException {
        // create the pictures directory if it doesn't exist
        Path picturesDir = Paths.get("pictures");
        if (!Files.exists(picturesDir)) {
            Files.createDirectory(picturesDir);
        }

        // show the file chooser dialog to select an image file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // create the user directory if it doesn't exist
            Path userDir = picturesDir.resolve(username);
            if (!Files.exists(userDir)) {
                Files.createDirectory(userDir);
            }
            // copy the selected file to the user directory
            Path targetPath = userDir.resolve(selectedFile.getName());
            Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    }
