package org.example.controllers;

import org.example.db.DAO;
import org.example.db.Statistic;
import org.example.logging.Logging;
import org.example.service.ConnectionWebPageService;
import org.example.service.UnloadingWebPageService;
import org.example.service.WordPerPageStatisticService;
import org.example.ui.UserInterface;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;


public class StatisticController {

    private static final String ERROR_MESSAGE_URL = "Not existing url: ";

    private UserInterface userInterface;
    private ConnectionWebPageService connection;
    private UnloadingWebPageService unload;
    private WordPerPageStatisticService statistic;
    private DAO dao;


    public StatisticController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void getStatistic(String url) {
        try {
            connection = new ConnectionWebPageService(url);
            unload = new UnloadingWebPageService(connection.getConnection());
            statistic = new WordPerPageStatisticService(unload.getWebPage());
            Map<String, Integer> words = statistic.getMapWords();
            saveDB(url, words);
            userInterface.getStatistic(words);
        } catch (IOException e) {
            Logging.writeFile(ERROR_MESSAGE_URL + e.getMessage());
            getMessageErrorOnInterface(ERROR_MESSAGE_URL + e.getMessage());
        } catch (Exception e) {
            Logging.writeFile(e.getMessage());
            getMessageErrorOnInterface(e.getMessage());
        }
    }

    public void getMessageErrorOnInterface(String messageError) {
        userInterface.getMessageError(messageError);
    }

    public void saveDB(String url, Map<String, Integer> words) {
        Statistic entity = new Statistic();
        entity.setNameSite(url);
        entity.setDateViewed(new Date(new java.util.Date().getTime()));
        entity.setWords(words);
        dao.save(entity);
    }

}
