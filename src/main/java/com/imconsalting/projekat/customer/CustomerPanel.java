package com.imconsalting.projekat.customer;

import com.imconsalting.projekat.UI.Controller;
import com.imconsalting.projekat.UI.paneli.StartPanel;
import com.imconsalting.projekat.company.Company;
import com.imconsalting.projekat.employee.Employee;
import com.imconsalting.projekat.employee.EmployeeEditPanel;
import com.imconsalting.projekat.employee.privilege.Privilege;
import com.imconsalting.projekat.empstatus.EmpStatus;
import com.imconsalting.projekat.profession.Profession;
import jakarta.persistence.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;


public class CustomerPanel extends VBox {

    private final Label currentEmployeeLabel = new Label();
    private final Button backButton = new Button("Back");
    private TableView<Customer> customerTableView = new TableView<>();
    private CustomerController customerController = new CustomerController();
    private final Button addCustomerButton = new Button("Add Customer");
    private final Button editEmployeeButton = new Button("Edit Customer");
    private final Button deleteCustomerButton = new Button("Delete Customer");
    private final Button actionCustomerButton = new Button("Action Customer");
    private final Button actionEmployeeButton = new Button("Action Employee");
    private final CheckBox deleteCheckBox = new CheckBox("Delete");
    private final Label nameLabel = new Label("Name: ");
    private final TextField nameTextField = new TextField();
    private final Label surnameLabel = new Label("Surname: ");
    private final TextField surnameTextField = new TextField();
    private final Label birthdayLabel = new Label("Birthday: ");
    private final DatePicker birthdayPicker = new DatePicker();
    private final Label addressLabel = new Label("Adress: ");
    private final TextField addressTextField = new TextField();
    private final Label mobileLabel = new Label("Mobile: ");
    private final TextField mobileTextField = new TextField();
    private final Label emailLabel = new Label("Email: ");
    private final TextField emailTextField = new TextField();
    private final Label empStatusLabel = new Label("Status: ");
    private final ComboBox<EmpStatus> empStatusComboBox = new ComboBox<>();
    private final Label professionLabel = new Label("Profession: ");
    private final ComboBox<Profession> professionComboBox = new ComboBox<>();
    private final Label companyLabel = new Label("Company: ");
    private final ComboBox<Company> companyComboBox = new ComboBox<>();


    public CustomerPanel() {
        setSpacing(10);
        setPadding(new Insets(10));

        currentEmployeeLabel.setText(Controller.getCurrentEmployee().getName() + ", " + Controller.getCurrentEmployee().getSurname());
        BorderPane borderPane = new BorderPane(null, null, currentEmployeeLabel, null, backButton);

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

        TableColumn<Customer, String> professionColumn = new TableColumn<>("Profesija");
        //professionColumn.setMinWidth(200);
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));

        TableColumn<Customer, String> companyColumn = new TableColumn<>("Kompanija");
        //companysColumn.setMinWidth(200);
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

        TableColumn<Customer, String> employeeColumn = new TableColumn<>("Zaposlenik");
        //employeesColumn.setMinWidth(200);
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));

        TableColumn<Customer, String> dateColumn = new TableColumn<>("Datum registracije");
        //dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegisty"));

        customerTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn, birthdayColumn, addressColumn, mobileColumn, emailColumn, empStatusColumn, professionColumn, companyColumn, employeeColumn, dateColumn);


        //BUTTONs
        HBox buttonHbox = new HBox(10);
        buttonHbox.getChildren().addAll(addCustomerButton, editEmployeeButton, deleteCustomerButton, deleteCheckBox);
        backButton.setOnAction(this::onClickBackButton);
        addCustomerButton.setOnAction(this::onClickAddCustomerButton);
        editEmployeeButton.setOnAction(this::onCLickEditCustomerButton);
        deleteCustomerButton.setOnAction(this::onClickDeleteCustomerButton);
        deleteCustomerButton.setDisable(true);
        deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
        HBox button1Hbox = new HBox(10);
        button1Hbox.getChildren().addAll(actionCustomerButton, actionEmployeeButton);
        actionCustomerButton.setOnAction(this::onClickActionCustomerButton);
        actionEmployeeButton.setOnAction(this::onClickActionEmployeeButton);
        BorderPane buttonBorderPane = new BorderPane(null, null, button1Hbox, null, buttonHbox);


        //UNOS TEXT FIELDA
        nameTextField.setPromptText("Enter name...");
        nameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Enter surname...");
        surnameTextField.setMaxWidth(200);
        addressTextField.setPromptText("Enter address...");
        addressTextField.setMaxWidth(200);
        mobileTextField.setPromptText("Enter mobile...");
        mobileTextField.setMaxWidth(200);
        emailTextField.setPromptText("Enter email...");
        emailTextField.setMaxWidth(200);
        companyComboBox.setPromptText("Enter company...");
        professionComboBox.setPromptText("Enter profession...");
        empStatusComboBox.setPromptText("Enter status...");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 0, 1);
        gridPane.add(surnameLabel, 1, 0);
        gridPane.add(surnameTextField, 1, 1);
        gridPane.add(addressLabel, 2, 0);
        gridPane.add(addressTextField, 2, 1);
        gridPane.add(mobileLabel, 3, 0);
        gridPane.add(mobileTextField, 3, 1);
        gridPane.add(emailLabel, 4, 0);
        gridPane.add(emailTextField, 4, 1);
        gridPane.add(birthdayLabel, 5, 0);
        gridPane.add(birthdayPicker, 5, 1);
        gridPane.add(empStatusLabel, 0, 2);
        gridPane.add(empStatusComboBox, 0, 3);
        gridPane.add(professionLabel, 1, 2);
        gridPane.add(professionComboBox, 1, 3);
        gridPane.add(companyLabel, 2, 2);
        gridPane.add(companyComboBox, 2, 3);

        //UNOS CHECK BOXA
        ObservableList<EmpStatus> empStatusObservableList = empStatusComboBox.getItems();
        ObservableList<Profession> professionObservableList=professionComboBox.getItems();
        ObservableList<Company> companyObservableList=companyComboBox.getItems();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("EmpStatus.findAll");
        List<EmpStatus> empStatusList = query.getResultList();
        query=entityManager.createNamedQuery("Profession.findAll");
        List<Profession> professionList = query.getResultList();
        query=entityManager.createNamedQuery("Company.findAll");
        List<Company> companyList = query.getResultList();
        entityManager.getTransaction().commit();
        empStatusObservableList.addAll(empStatusList);
        empStatusComboBox.setOnAction(this::onCLickEmpStatusComboBox);
        professionObservableList.addAll(professionList);
        companyObservableList.addAll(companyList);


        getChildren().addAll(borderPane, customerTableView, buttonBorderPane, gridPane);
    }

    private void onClickActionEmployeeButton(ActionEvent actionEvent) {
    }

    private void onClickActionCustomerButton(ActionEvent actionEvent) {
    }

    private void onCLickEmpStatusComboBox(ActionEvent actionEvent) {
        if (!empStatusComboBox.getValue().getName().equals("Zaposlen")) {
            companyComboBox.setDisable(true);
        } else {
            companyComboBox.setDisable(false);
        }
    }

    private void onClickDeleteCheckBox(ActionEvent actionEvent) {
        if (deleteCheckBox.isSelected()) {
            deleteCustomerButton.setDisable(false);
        } else {
            deleteCustomerButton.setDisable(true);
        }
    }

    private void onCLickEditCustomerButton(ActionEvent actionEvent) {
        if (customerTableView.getSelectionModel().getSelectedItem() == null) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Selektujte kupca kojeg želite editovati");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        }else {
            Controller.setEditCustomer(customerTableView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Editovanje kupca");
            CustomerEditPanel customerEditPanel = new CustomerEditPanel();
            Scene scene = new Scene(customerEditPanel);
            stage.setScene(scene);
            stage.show();
        }
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

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, selectedCustomer.getId());
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        }
    }


    private void onClickBackButton(ActionEvent actionEvent) {
        StartPanel startPanel = new StartPanel();
        Scene scene = new Scene(startPanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Pocetna");
    }

    private void onClickAddCustomerButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || birthdayPicker.getValue() == null ||
                addressTextField.getText().isEmpty() || mobileTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                empStatusComboBox.getValue() == null || professionComboBox.getValue() == null ||
                (empStatusComboBox.getValue().getName().equals("Zaposlen") && companyComboBox.getValue()==null)) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Niste popunili sva polja!");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            Customer customer = new Customer();
            customer.setName(nameTextField.getText());
            customer.setSurname(surnameTextField.getText());
            customer.setBirthday(birthdayPicker.getValue());
            customer.setAddress(addressTextField.getText());
            customer.setMobile(mobileTextField.getText());
            customer.setEmail(emailTextField.getText());
            customer.setEmpStatus(empStatusComboBox.getValue());
            customer.setProfession(professionComboBox.getValue());
            if(companyComboBox.getValue()!=null){
                customer.setCompany(companyComboBox.getValue());
            }
            customer.setEmployee(Controller.getCurrentEmployee());
            customer.setDateRegisty(LocalDate.now());


            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();

            ObservableList<Customer> customerObservableList = customerTableView.getItems();
            customerObservableList.add(customer);
        }
        nameTextField.setText("");
        surnameTextField.setText("");
        birthdayPicker.setValue(null);
        addressTextField.setText("");
        mobileTextField.setText("");
        emailTextField.setText("");
    }

}