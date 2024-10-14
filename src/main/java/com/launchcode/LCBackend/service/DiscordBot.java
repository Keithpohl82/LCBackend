package com.launchcode.LCBackend.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordBot {

    // Replace with your webhook URL
    private static final String DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1295422354758959238/y3VZvAS3PGGLRKFjfaCIZX6vp9HUHQPfXFhUGkeBOGuzYS63vb-_S9i6pZXnY_SvnhLY";

    public static void postMessage(String message) {
        try {
            URL url = new URL(DISCORD_WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Create JSON payload
            String jsonPayload = "{\"content\":\"" + message + "\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Handle the connection response...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
