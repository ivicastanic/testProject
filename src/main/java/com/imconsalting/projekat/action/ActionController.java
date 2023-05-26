package com.imconsalting.projekat.action;

import com.imconsalting.projekat.UI.Controller;
import com.imconsalting.projekat.customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ActionController {
    public ObservableList<Action> loadActionsByEmployee(){
        List<Action> actions=new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Action.findByEmployee");
        query.setParameter("employee", Controller.getCurrentEmployee());
        actions=query.getResultList();
        entityManager.getTransaction().commit();


        ObservableList<Action> actionObservableList= FXCollections.observableList(actions);
        return actionObservableList;
    }

    public ObservableList<Action> loadActionsByCustomer(){
        List<Action> actions=new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Action.findByCustomer");
        query.setParameter("customer", Controller.getSelctedCustomer());
        actions=query.getResultList();
        entityManager.getTransaction().commit();


        ObservableList<Action> actionObservableList= FXCollections.observableList(actions);
        return actionObservableList;
    }

}
