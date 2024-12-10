package com.example.notepad;

import com.example.notepad.config.ApplicationContextProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotepadApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ApplicationContextProvider.getApplicationContext();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showLoginPage(primaryStage);
    }

    private void showLoginPage(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showNotepad(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx.fxml"));
        loader.setControllerFactory(ApplicationContextProvider.getApplicationContext()::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Notepad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ApplicationContextProvider.getApplicationContext().close();
    }
}