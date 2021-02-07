package org.example;


import org.example.controllers.StatisticController;
import org.example.db.DAO;
import org.example.db.Statistic;
import org.example.logging.Logging;
import org.example.ui.UserInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class AppTest {

    @Test
    public void testAddedForDB() {
        Statistic statistic = new Statistic();
        statistic.setNameSite("test");
        statistic.setDateViewed(new Date(new java.util.Date().getTime()));
        Map<String, Integer> words = new HashMap<>();

        words.put("ONE", 4);
        words.put("TWO", 3);
        statistic.setWords(words);

        DAO dao = new DAO(new StatisticController(new UserInterface()));

        dao.save(statistic);
    }

    @Test
    public void loggingCheck() {
        Logging.writeFile("Error 1");
        Logging.writeFile("Error 2");
        Logging.writeFile("Error 3");
        Logging.writeFile("Error 4");
    }

    @Test
    public void testStatistic() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.simbirsoft.com/").openConnection();
        String html;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            html = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        Document document = Jsoup.parse(html);
        String htmlBody = document.select("body").text();
        String bodyText = htmlBody.replaceAll("[\\s,.!?\";:\\[\\]()\\n\\r\\t]", "").toUpperCase();
        Scanner scanBodyText = new Scanner(bodyText);
        Map<String, Integer> words = new HashMap<>();
        while (scanBodyText.hasNext()) {
            String word = scanBodyText.next();
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }
        for (Map.Entry<String, Integer> element : words.entrySet()) {
            System.out.println(element.getKey() + " " + element.getValue());
        }
    }
}
