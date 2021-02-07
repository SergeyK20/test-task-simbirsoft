package org.example.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionWebPageService {

    private HttpURLConnection connection;

    public ConnectionWebPageService(String url) throws IOException {
        connection =
                (HttpURLConnection) new URL(url).openConnection();
    }

    public HttpURLConnection getConnection() throws IOException {
        if (connection.getResponseCode() == 200) {
            return connection;
        } else {
            return null;
        }
    }
}
