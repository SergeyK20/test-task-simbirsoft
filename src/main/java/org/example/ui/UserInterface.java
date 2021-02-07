package org.example.ui;

import org.example.controllers.StatisticController;

import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    private StatisticController controller;

    public UserInterface() {

    }

    public void start() {
        System.out.println("Введите URL web-страницы (пример:https://www.simbirsoft.com/): ");
        Scanner scanner = new Scanner(System.in);
        controller.getStatistic(scanner.next());
    }

    public void getStatistic(Map<String, Integer> words) {
        for (Map.Entry<String, Integer> element : words.entrySet()) {
            System.out.println(element.getKey() + " " + element.getValue());
        }
    }

    public void getMessageError(String messageError) {
        System.out.println(messageError);
    }

    public void setController(StatisticController controller) {
        this.controller = controller;
    }
}
