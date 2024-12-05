package com.example.notepad;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotepadSpringBootApplication {
    public static void main(String[] args) {
        Application.launch(NotepadApp.class, args);
    }
}