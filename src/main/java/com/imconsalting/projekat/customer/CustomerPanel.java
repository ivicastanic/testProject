package com.imconsalting.projekat.customer;

import com.imconsalting.projekat.UI.AbstractScene;
import com.imconsalting.projekat.UI.paneli.StartPanel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;


public class CustomerPanel extends VBox {

    private final Button backButton = new Button("Back");
    private TableView<Customer> customerTableView = new TableView<>();
    private CustomerController customerController = new CustomerController();
    private final TextField nameTextField = new TextField();
    private final Label nameLabel = new Label("Name: ");
    private final TextField surnameTextField = new TextField();
    private final Label surnameLabel = new Label("Surname: ");
    private final Button buttonAddCustomer = new Button("Add Customer");
    private final Button buttonDeleteCustomer = new Button("Delete Customer");

    public CustomerPanel() {
        setSpacing(10);
        setPadding(new Insets(10));

        //TABELA
        ObservableList<Customer> customerObservableList = customerController.loadCustomers();
        customerTableView.setItems(customerObservableList);

        TableColumn<Customer, Integer> idColumn = new TableColumn<>("Id");
        //idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Ime");
        //nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> surnameColumn = new TableColumn<>("Prezime");
        //surnameColumn.setMinWidth(200);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Customer, LocalDate> birthdayColumn = new TableColumn<>("Datum rođenja");
        //birthdayColumn.setMinWidth(200);
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        TableColumn<Customer, String> addressColumn = new TableColumn<>("Adresa");
        //addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Customer, String> mobileColumn = new TableColumn<>("Mobitel");
        //mobileColumn.setMinWidth(200);
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        //emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Customer, String> empStatusColumn = new TableColumn<>("Status");
        //empStatusColumn.setMinWidth(200);
        empStatusColumn.setCellValueFactory(new PropertyValueFactory<>("empStatus"));


        customerTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn,birthdayColumn,addressColumn,mobileColumn,emailColumn,empStatusColumn);


        //UNOS TEXT FIELDA
        nameTextField.setPromptText("Name...");
        nameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Surname...");
        surnameTextField.setMaxWidth(200);

        //BUTTONs
        backButton.setOnAction(this::onClickBackButton);
        buttonAddCustomer.setOnAction(this::onClickAddCustomerButton);
        buttonDeleteCustomer.setOnAction(this::onClickDeleteCustomerButton);

        getChildren().addAll(backButton, customerTableView, nameLabel, nameTextField, surnameLabel, surnameTextField, buttonAddCustomer, buttonDeleteCustomer);

    }

    private void onClickDeleteCustomerButton(ActionEvent actionEvent) {
        if (customerTableView.getSelectionModel().getSelectedItem() == null) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Selektujte kupca kojeg želite izbrisati");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            ObservableList<Customer> employeeObservableList = customerTableView.getItems();
            employeeObservableList.remove(selectedCustomer);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, selectedCustomer.getId());
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        }
    }


    private void onClickBackButton(ActionEvent actionEvent) {
        StartPanel startPanel = new StartPanel();
        AbstractScene.setScene(startPanel);
    }

    private void onClickAddCustomerButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Neispravan unos!");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Customer customer = new Customer();
            customer.setName(nameTextField.getText());
            customer.setSurname(surnameTextField.getText());

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();

            ObservableList<Customer> customerObservableList = customerTableView.getItems();
            customerObservableList.add(customer);
        }
        nameTextField.setText("");
        surnameTextField.setText("");
    }

}