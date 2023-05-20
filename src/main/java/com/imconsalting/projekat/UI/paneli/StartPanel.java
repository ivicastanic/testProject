package com.imconsalting.projekat.UI.paneli;


import com.imconsalting.projekat.UI.Controller;
import com.imconsalting.projekat.customer.CustomerPanel;
import com.imconsalting.projekat.employee.EmployeePanel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class StartPanel extends VBox {
    private final Label currentEmployeeLabel=new Label();
    private final Label welcomeLabel = new Label("WELCOME");
    private final Button customersButton = new Button("Customers");
    private final Button employeesButton = new Button("Employees");
    private final Button logoutButton = new Button("Odjava");
    private final Label dateLabel = new Label();
    private final Image image=new Image("firma.png");

    public StartPanel(){
        setSpacing(10);
        setPadding(new Insets(10));

        dateLabel.setText(returnDate());
        currentEmployeeLabel.setText(Controller.getCurrentEmployee().getName()+", "+Controller.getCurrentEmployee().getSurname());
        BorderPane borderPane1=new BorderPane(null,null,currentEmployeeLabel,null,dateLabel);

        BorderPane borderPane=new BorderPane(welcomeLabel);
        welcomeLabel.setFont(new Font("Arial",20));
        borderPane.setPadding(new Insets(20));

        HBox hBox=new HBox(10);
        hBox.getChildren().addAll(customersButton, employeesButton, logoutButton);
        BorderPane borderPane2=new BorderPane(hBox);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        BorderPane borderPane3=new BorderPane(imageView);


        getChildren().addAll(borderPane1,borderPane,borderPane2,borderPane3);



        logoutButton.setOnAction(this::onClickLogoutButton);
        customersButton.setOnAction(this::onClickCustomerButton);
        employeesButton.setOnAction(this::onClickEmployeeButton);

        //Image middleImage=new Image("middle.gif");
        //ImageView imageView1 = new ImageView(middleImage);
        //customersButton.setGraphic(imageView1);
    }

    private void onClickCustomerButton(ActionEvent actionEvent) {
        CustomerPanel customerPanel=new CustomerPanel();
        Scene scene=new Scene(customerPanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Customers");
    }

    private void onClickEmployeeButton(ActionEvent actionEvent) {
        EmployeePanel employeePanel=new EmployeePanel();
        Scene scene=new Scene(employeePanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Employees");
    }

    private void onClickLogoutButton(ActionEvent actionEvent) {
        LoginPanel loginPanel=new LoginPanel();
        Scene scene=new Scene(loginPanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Projekat");
    }

    private String returnDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

}



