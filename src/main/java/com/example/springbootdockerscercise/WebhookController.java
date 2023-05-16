package com.example.springbootdockerscercise;

import java.time.LocalDateTime;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    private static final String DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1090549186040184913/_nVKm8TfNkMjjZDz2lgNqbVQBmciLcgii6trO7nXIEz7LAtMHVRBXkSVwoSd-nYRGxzU";
    private static final String DISCORD_USERNAME = "익명1";

    @GetMapping("/")
    public String webhook() {
        LocalDateTime now = LocalDateTime.now();
        String message = "Current date and time: " + now;

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String payload = String.format("{\"content\":\"%s\", \"username\":\"%s\"}", message, DISCORD_USERNAME);
        RequestBody body = RequestBody.create(mediaType, payload);

        Request request = new Request.Builder()
            .url(DISCORD_WEBHOOK_URL)
            .post(body)
            .build();

        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }
}
