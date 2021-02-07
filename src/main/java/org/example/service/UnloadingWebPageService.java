package org.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;

public class UnloadingWebPageService {

    private HttpURLConnection connection;

    public UnloadingWebPageService(HttpURLConnection connection) {
        this.connection = connection;
    }

    public String getWebPage() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
