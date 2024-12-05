package com.example.notepad.controller;

import com.example.notepad.service.NoteService;
import com.example.notepad.service.TextService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class NotepadController {

    @FXML
    private TextArea textArea;
    @FXML
    private ImageView newIcon;
    @FXML
    private ImageView openIcon;
    @FXML
    private ImageView loadIcon;


    private final TextService textService;
    private final NoteService noteService;

    public NotepadController(TextService textService,NoteService noteService) {
        this.textService = textService;
        this.noteService = noteService;

    }

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    public void initialize() {
        Image newImage = new Image(getClass().getResource("/icons/new.png").toExternalForm());
        newIcon.setImage(newImage);

//        ImageView newImageView = new ImageView(newImage);
//        newImageView.setFitWidth(16);
//        newImageView.setFitHeight(16);
//        newMenuItem.setGraphic(newImageView);

        Image openImage = new Image(getClass().getResource("/icons/book_open.png").toExternalForm());
        openIcon.setImage(openImage);

        Image loadImage = new Image(getClass().getResource("/icons/text_list_bullets.png").toExternalForm());
        loadIcon.setImage(loadImage);


    }


    @FXML
    public void handleNewFile() {
        textArea.clear();
    }

    @FXML
    public void handleToUpperCase() {
        String text = textArea.getText();
        textArea.setText(textService.toUpperCase(text));
    }

    @FXML
    public void handleOpenFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try {
                String content = Files.readString(file.toPath());
                textArea.setText(content);
            } catch (IOException e) {
                showError("Could not open file: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleSaveFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(textArea.getScene().getWindow());
        if (file != null) {
            try {
                Files.writeString(file.toPath(), textArea.getText());
            } catch (IOException e) {
                showError("Could not save file: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleLoadTable() {
        noteService.findAll().forEach(note -> textArea.appendText(note.getTitle() + " - "
                + note.getContent() + " - "
                + note.getCreatedAt()
                +"\n\n"));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
