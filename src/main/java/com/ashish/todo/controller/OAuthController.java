package com.ashish.todo.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/oauth")
public class OAuthController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @PostMapping("/exchange")
    public ResponseEntity<Map<String, String>> exchangeCodeForToken(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("code");
        String redirectUri = requestBody.get("redirectUri");

        if (code == null || redirectUri == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing required parameters"));
        }


        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("redirect_uri", redirectUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange("https://github.com/login/oauth/access_token", HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to get access token", "details", e.getMessage()));
        }


        String responseBody = response.getBody();
        if (responseBody != null) {
            Map<String, String> responseMap = new HashMap<>();
            for (String param : responseBody.split("&")) {
                String[] keyValue = param.split("=");
                responseMap.put(keyValue[0], keyValue[1]);
            }
            return ResponseEntity.ok(responseMap);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to get access token"));
    }
}
