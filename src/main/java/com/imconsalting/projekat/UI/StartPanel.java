package com.imconsalting.projekat.UI;


import com.imconsalting.projekat.employee.EmployeePanel;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class StartPanel extends VBox {
    private final Label welcomeLabel = new Label("WELCOME");
    private final Button buttonCustomers = new Button("Customers");
    private final Button buttonEmployees = new Button("Employees");
    private final Button buttonOthers = new Button("Others");
    private final Label dateLabel = new Label();

    private final Image image=new Image("firma.png");

    public StartPanel(){
        setSpacing(20);
        setPadding(new Insets(10));

        BorderPane borderPane=new BorderPane(welcomeLabel);

        HBox hBox=new HBox(10);
        hBox.getChildren().addAll(buttonCustomers,buttonEmployees,buttonOthers);

        HBox hBox1=new HBox(10);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        hBox1.getChildren().addAll(dateLabel,imageView);


        getChildren().addAll(borderPane,hBox,hBox1);


        dateLabel.setText(returnDate());

        buttonOthers.setOnAction(this::onClickOthersButton);
        //buttonCustomers.setOnAction(this::onClickCustomerButton);
        buttonEmployees.setOnAction(this::onClickEmployeeButton);
    }

    private void onClickEmployeeButton(ActionEvent actionEvent) {
        EmployeePanel employeePanel=new EmployeePanel();
        AbstractScene.setScene(employeePanel);
    }

    private String returnDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

    private void onClickOthersButton(ActionEvent e) {
        NekiPanel nekiPanel=new NekiPanel();
        AbstractScene.setScene(nekiPanel);
    }

}




/*


    private void addComponent() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(welcomeLabel);
        welcomePanel.setSize(100, 10);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        buttonsPanel.add(buttonCustomers);
        buttonsPanel.add(buttonEmployees);
        buttonsPanel.add(buttonOthers);

        JPanel dateAndLogoPanel = new JPanel();
        dateAndLogoPanel.setLayout(new GridLayout(1, 2));
        dateAndLogoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dateAndLogoPanel.add(dateLabel);
        dateAndLogoPanel.add(pictureLabel);
        dateAndLogoPanel.setSize(new Dimension(100, 400));


        add(welcomePanel);
        add(buttonsPanel);
        add(dateAndLogoPanel);

    }



    private void buttonOthersListener(ActionEvent e) {
        JFrame frame = new JFrame("Others");
        NekiPanel nekiPanel = new NekiPanel();
        frame.setContentPane(nekiPanel);
        frame.setSize(new Dimension(300, 300));

        frame.setVisible(true);
    }

    private void buttonCustomersListener(ActionEvent e) {
        JFrame frame = new JFrame("Customers");
        CustomerPanel customerPanel = new CustomerPanel();
        frame.setContentPane(customerPanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void buttonEmployeessListener(ActionEvent e) {
        JFrame frame = new JFrame("Employees");
        EmployeePanel employeePanel = new EmployeePanel();
        frame.setContentPane(employeePanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}*/