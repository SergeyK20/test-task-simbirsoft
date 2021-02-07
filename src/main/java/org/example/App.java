package org.example;

import org.example.controllers.StatisticController;
import org.example.db.DAO;
import org.example.ui.UserInterface;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserInterface userInterface = new UserInterface();
        StatisticController statisticController = new StatisticController(userInterface);
        DAO dao = new DAO(statisticController);
        statisticController.setDao(dao);
        userInterface.setController(statisticController);
        userInterface.start();
    }
}
