package com.imconsalting.projekat.employee;


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

public class EmployeePanel extends VBox {

    private final Button backButton = new Button("Back");
    private TableView<Employee> employeeTableView = new TableView<>();
    private EmployeeController employeeController = new EmployeeController();
    private final TextField nameTextField = new TextField();
    private final Label nameLabel = new Label("Name: ");
    private final TextField surnameTextField = new TextField();
    private final Label surnameLabel = new Label("Surname: ");
    private final Button buttonAddEmployee = new Button("Add Employee");
    private final Button buttonDeleteEmployee = new Button("Delete Employee");

    public EmployeePanel() {
        setSpacing(10);
        setPadding(new Insets(10));

        //TABELA
        ObservableList<Employee> employeeObservableList = employeeController.loadEmployee();
        employeeTableView.setItems(employeeObservableList);

        TableColumn<Employee, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setMinWidth(200);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        employeeTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn);


        //UNOS TEXT FIELDA
        nameTextField.setPromptText("Name...");
        nameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Surname...");
        surnameTextField.setMaxWidth(200);

        //BUTTONs
        backButton.setOnAction(this::onClickBackButton);
        buttonAddEmployee.setOnAction(this::onClickAddEmployeeButton);
        buttonDeleteEmployee.setOnAction(this::onClickDeleteEmployeeButton);

        getChildren().addAll(backButton, employeeTableView, nameLabel, nameTextField, surnameLabel, surnameTextField, buttonAddEmployee, buttonDeleteEmployee);

    }

    private void onClickDeleteEmployeeButton(ActionEvent actionEvent) {
        if (employeeTableView.getSelectionModel().getSelectedItem() == null) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Selektujte zaposlenika kojeg želite izbrisati");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
            ObservableList<Employee> employeeObservableList = employeeTableView.getItems();
            employeeObservableList.remove(selectedEmployee);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, selectedEmployee.getId());
            entityManager.remove(employee);
            entityManager.getTransaction().commit();
        }
    }


    private void onClickBackButton(ActionEvent actionEvent) {
        StartPanel startPanel = new StartPanel();
        AbstractScene.setScene(startPanel);
    }

    private void onClickAddEmployeeButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty()) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Neispravan unos!");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Employee employee = new Employee();
            employee.setName(nameTextField.getText());
            employee.setSurname(surnameTextField.getText());

            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();

            ObservableList<Employee> employeeObservableList = employeeTableView.getItems();
            employeeObservableList.add(employee);
        }
        nameTextField.setText("");
        surnameTextField.setText("");
    }

}

