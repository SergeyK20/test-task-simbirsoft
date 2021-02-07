package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordPerPageStatisticService {
    private String htmlPage;

    public WordPerPageStatisticService(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    public Map<String, Integer> getMapWords() {
        Document document = Jsoup.parse(htmlPage);
        String htmlBody = document.select("body").text();
        String bodyText = htmlBody.replaceAll("[\\s,.!?\";:\\[\\]()\\n\\r\\t]", " ").toUpperCase();
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
        return words;
    }
}
