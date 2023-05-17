package com.imconsalting.projekat.customer;

import com.imconsalting.projekat.employee.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    public ObservableList<Customer> loadCustomers(){
        List<Customer> customers=new ArrayList<>();
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query=entityManager.createQuery("SELECT c FROM Customer c");
        customers=query.getResultList();
        entityManager.getTransaction().commit();

        ObservableList<Customer> customerObservableList= FXCollections.observableList(customers);
        return customerObservableList;

    }
}
