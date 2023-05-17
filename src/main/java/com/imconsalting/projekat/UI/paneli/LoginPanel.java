package com.imconsalting.projekat.UI.paneli;

import com.imconsalting.projekat.UI.AbstractScene;
import com.imconsalting.projekat.employee.Employee;
import jakarta.persistence.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


public class LoginPanel extends GridPane {
    private final Label usernameLabel = new Label("Korisničko ime: ");
    private final Label passwordLabel = new Label("Lozinka: ");
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Prijava");
    private final Button cancelButton = new Button("Odustani");
    private final Label messageLabel = new Label();//ovdje ne piše ništa..sadržaj ćemo možda dinamički popuniti

    public LoginPanel() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        setAlignment(Pos.CENTER);

        //username
        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);
        //password
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        //FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_RIGHT);
        loginButton.setOnAction(this::onLoginButtonClick);
        cancelButton.setOnAction(this::onCancelButtonClick);
        flowPane.getChildren().addAll(loginButton, cancelButton);
        add(flowPane, 1, 2);
        //message
        add(messageLabel, 1, 3);
    }

    private void onCancelButtonClick(ActionEvent event) {
        usernameTextField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }


    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            messageLabel.setText("Nije dozvoljeno prazan unos korisničkog imena ili lozinke");
            return;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.username = :username");
        query.setParameter("username", username);
        try {
            Employee employee = (Employee) query.getSingleResult();
            if (employee != null && password.equals(employee.getPassword())) {
                AbstractScene.setScene(new StartPanel());
            } else {
                messageLabel.setText("Neispravna lozinka.");
            }
        } catch (NoResultException e) {
            messageLabel.setText("Nesipravno korisničko ime.");
            System.out.println(e.getMessage());
        }


    }

}
