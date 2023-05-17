package com.imconsalting.projekat.employee;
/*
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.scene.Scene;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

 */

import com.imconsalting.projekat.UI.AbstractScene;
import com.imconsalting.projekat.UI.StartPanel;
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

    private final Button backButton=new Button("Back");
    private TableView<Employee> employeeTableView=new TableView<>();
    private EmployeeController employeeController=new EmployeeController();
    private final TextField nameTextField=new TextField();
    private final Label nameLabel=new Label("Name: ");
    private final TextField surnameTextField = new TextField();
    private final Label surnameLabel = new Label("Surname: ");
    private final Button buttonAddEmployee = new Button("Add Employee");
    private final Button buttonDeleteEmployee=new Button("Delete Employee");

    public EmployeePanel(){
        setSpacing(10);
        setPadding(new Insets(10));

        //TABELA
        ObservableList<Employee> employeeObservableList=employeeController.loadEmployee();
        employeeTableView.setItems(employeeObservableList);

        TableColumn<Employee,Integer> idColumn=new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee,Integer> nameColumn=new TableColumn<>("Ime");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee,Integer> surnameColumn=new TableColumn<>("Prezime");
        surnameColumn.setMinWidth(200);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        employeeTableView.getColumns().addAll(idColumn,nameColumn,surnameColumn);


        //UNOS TEXT FIELDA
        nameTextField.setPromptText("Name...");
        nameTextField.setMaxWidth(200);
        surnameTextField.setPromptText("Surname...");
        surnameTextField.setMaxWidth(200);

        //BUTTONs
        backButton.setOnAction(this::onClickBackButton);
        buttonAddEmployee.setOnAction(this::onClickAddEmployeeButton);

        getChildren().addAll(backButton,employeeTableView,nameLabel,nameTextField,surnameLabel,surnameTextField,buttonAddEmployee,buttonDeleteEmployee);

    }


    private void onClickBackButton(ActionEvent actionEvent) {
        StartPanel startPanel=new StartPanel();
        AbstractScene.setScene(startPanel);
    }

    private void onClickAddEmployeeButton(ActionEvent actionEvent) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Employee employee=new Employee();
        employee.setName(nameTextField.getText());
        employee.setSurname(surnameTextField.getText());

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        nameTextField.setText(null);
        surnameTextField.setText(null);

        ObservableList<Employee> employeeObservableList=employeeTableView.getItems();
        employeeObservableList.add(employee);
    }


}


/*


        EmployeeTableModel employeeTableModel = new EmployeeTableModel();
        employeeTable = new JTable(employeeTableModel);
        JScrollPane employeeTableScrollPane = new JScrollPane(employeeTable);
        add(employeeTableScrollPane);

        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        nameTextField.setEditable(true);
        nameTextField.setColumns(10);
        namePanel.add(nameTextField);

        JPanel surnamePanel = new JPanel();
        surnamePanel.add(surnameLabel);
        surnameTextField.setEditable(true);
        surnameTextField.setColumns(10);
        surnamePanel.add(surnameTextField);

        JPanel buttonPanel = new JPanel();
        buttonAddEmployee.addActionListener(this::ButtonAddListener);
        buttonPanel.add(buttonAddEmployee);

        buttonDeleteEmployee.addActionListener(this::ButtonDeleteListener);

        add(namePanel);
        add(surnamePanel);
        add(buttonPanel);
        add(buttonDeleteEmployee);



    }

    private void ButtonDeleteListener(ActionEvent event){
        Employee employee=employeeList.get(employeeTable.getSelectedRow());
        EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }

    private void ButtonAddListener(ActionEvent event){
        EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Employee employee=new Employee();
        employee.setName(nameTextField.getText());
        employee.setSurname(surnameTextField.getText());
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        nameTextField.setText("");
        surnameTextField.setText("");
    }

    private class EmployeeTableModel extends AbstractTableModel {

        private List<String> columnNames = new ArrayList<>();

        public EmployeeTableModel() {

            columnNames.add("id");
            columnNames.add("name");
            columnNames.add("surname");
            //this.columnNames = playerDao.getColumnNames();
        }

        @Override
        public int getRowCount() {
            return employeeList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Employee employee = employeeList.get(rowIndex);
            //EmployeeWrapper employeeWrapper = new EmployeeWrapper(employee);
            //Object employeeFieldOnIndex = employeeWrapper.getColumValue(columnIndex);
            EmployeeWrapper employeeWrapper=new EmployeeWrapper(employee);
            return employeeWrapper.getColumValue(columnIndex);
        }

        /**
         * @param aValue      value to assign to cell
         * @param rowIndex    row of cell
         * @param columnIndex column of cell
         *

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class<?> clazz = getValueAt(0, columnIndex).getClass();
            return clazz;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }


    }
}*/