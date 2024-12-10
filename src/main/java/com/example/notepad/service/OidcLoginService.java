package com.example.notepad.service;


public class OidcLoginService {

//    private static final String CLIENT_ID = "external-client";
//    private static final String CLIENT_SECRET = "ID5sEPIdxc33MgKxBOw3xVoo8s4h3EMM";
//    private static final String REDIRECT_URI = "http://localhost:8080/callback";
//    private static final String TOKEN_URL = "http://localhost:8080/realms/external/protocol/openid-connect/token";
//    private static final String AUTH_URL = "http://localhost:8080/realms/external/protocol/openid-connect/auth";
//
//    public void performLogin() {
//        // Step 1: Open Browser for Login
//        String loginUrl = AUTH_URL +
//                "?response_type=code" +
//                "&client_id=" + CLIENT_ID +
//                "&redirect_uri=" + REDIRECT_URI +
//                "&scope=openid profile email";
//        openBrowser(loginUrl);
//
//        // Step 2: Handle the callback for authorization code
//        String authorizationCode = waitForAuthorizationCode();
//
//        // Step 3: Exchange code for token
//        String token = fetchToken(authorizationCode);
//        System.out.println("Access Token: " + token);
//    }
//
//    private void openBrowser(String url) {
//        try {
//            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String waitForAuthorizationCode() {
//        // Implement logic to start a local HTTP server or listen to custom URI scheme
//        return "received-authorization-code"; // Mocked for simplicity
//    }
//
//    private String fetchToken(String authorizationCode) {
//        RestTemplate restTemplate = new RestTemplate();
//        var request = new org.springframework.util.LinkedMultiValueMap<String, String>();
//        request.add("grant_type", "authorization_code");
//        request.add("code", authorizationCode);
//        request.add("redirect_uri", REDIRECT_URI);
//        request.add("client_id", CLIENT_ID);
//        request.add("client_secret", CLIENT_SECRET);
//
//        var response = restTemplate.postForObject(TOKEN_URL, request, java.util.Map.class);
//        return response != null ? (String) response.get("access_token") : null;
//    }
}
