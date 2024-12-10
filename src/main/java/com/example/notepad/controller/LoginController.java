package com.example.notepad.controller;

import com.example.notepad.NotepadApp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Component
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Value("${app.oidc.token.url}")
    private String accessTokenUrl;

    private final NotepadApp notepadApp;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public LoginController(NotepadApp notepadApp, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.notepadApp = notepadApp;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        System.out.println("LoginController initialized with NotepadApp: " + notepadApp);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            // Handle successful login
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            try {
                Node source = (Node) event.getSource();
                notepadApp.showNotepad((Stage) source.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle failed login
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {

//        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token",
//                "http://localhost:8080/", "external");

        String requestBody = String.format("username=%s&password=%s&client_id=%s&grant_type=password",
                username, password, "external-client");

        String token = exchangeForToken(accessTokenUrl, requestBody);

        log.info("Access Token: " + token);

        return false;
    }

    private String exchangeForToken(String url, String requestBody) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(requestBody, createFormUrlEncodedHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            log.debug("Response {}", response);
            return parseAccessToken(response.getBody());
        } catch (Exception e) {
            log.error("Error exchanging for token", e);
        }
        return null;
    }

    private HttpHeaders createFormUrlEncodedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private String parseAccessToken(String jsonResponse) {
        try {
            Map<String, String> tokenMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, String>>() {
            });

            tokenMap.forEach((key, value) -> log.debug("Key: {}, Value: {}", key, value));

            return tokenMap.get("access_token");
        } catch (Exception e) {
            log.error("Error parsing access token", e);
            return null;
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}