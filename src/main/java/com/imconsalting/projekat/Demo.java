package com.imconsalting.projekat;

import com.imconsalting.projekat.action.Action;
import com.imconsalting.projekat.customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;


public class Demo {
    public static void main(String[] args) {

        /*Customer customer = new Customer();
        customer.setAddress("Zmaja pdbosne");
        customer.setBirthday(LocalDate.of(1998,5,1));
        customer.setId(1);
        customer.setEmail("ivica@gmail.com");
        customer.setName("Ivica");
        customer.setSurname("Stanic");
        customer.setMobile("061 123456");
        customer.setDateRegistry(LocalDate.of(2023,1,1));
        Employee employee =new Employee();
        employee.setId(1);
        employee.setName("mirsad");
        employee.setSurname("Skandro");
        customer.setEmployee(employee);
        System.out.println(customer.getEmployee().getName());*/

        EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("projectPU");
        EntityManager manager=entityManagerFactory.createEntityManager();/*

        manager.getTransaction().begin();
        Query companyListQuery = manager.createQuery("SELECT c FROM Company c");
        List<Company> companyList = companyListQuery.getResultList();
        manager.getTransaction().commit();
        companyList.forEach(System.out::println);

        manager.getTransaction().begin();
        Query professionListQuery = manager.createQuery("SELECT p FROM Profession p");
        List<Profession> professionList = professionListQuery.getResultList();
        manager.getTransaction().commit();
        professionList.forEach(System.out::println);

        manager.getTransaction().begin();
        Query employeeListQuery = manager.createQuery("SELECT e FROM Employee e");
        List<Employee> employeeList = employeeListQuery.getResultList();
        manager.getTransaction().commit();
        employeeList.forEach(System.out::println);*/

        manager.getTransaction().begin();
        Query query=manager.createQuery("SELECT c FROM Action c");
        List<Action> customerList=query.getResultList();
        manager.getTransaction().commit();
        customerList.forEach(System.out::println);


        /*manager.getTransaction().begin();
        Query customerListQuery = manager.createQuery("SELECT c FROM Customer c");
        List<Customer> customerList = customerListQuery.getResultList();
        manager.getTransaction().commit();
        customerList.forEach(System.out::println);*/


    }

}