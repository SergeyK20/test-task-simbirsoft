package org.example.db;

import org.example.controllers.StatisticController;
import org.example.logging.Logging;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private StatisticController controller;

    public DAO(StatisticController controller) {
        this.controller = controller;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("StatisticDB");
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            Logging.writeFile(e.getMessage());
            controller.getMessageErrorOnInterface(e.getMessage());
        }
    }

    public void save(Statistic statistic) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(statistic);

            entityManager.getTransaction().commit();


            close();
        } catch (Exception e) {
            Logging.writeFile(e.getMessage());
            controller.getMessageErrorOnInterface(e.getMessage());
        }
    }

    private void close() {
        try {
            entityManager.close();
            entityManagerFactory.close();
        } catch (Exception e) {
            Logging.writeFile(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}
