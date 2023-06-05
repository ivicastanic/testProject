package com.imconsalting.projekat.action;

import com.imconsalting.projekat.UI.Controller;
import com.imconsalting.projekat.channel.Channel;
import com.imconsalting.projekat.customer.CustomerPanel;
import com.imconsalting.projekat.employee.Employee;
import com.imconsalting.projekat.response.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class ActionCustomerPanel extends VBox {
    private final Label currentEmployeeLabel = new Label();
    private final Label currentCustomerLabel = new Label();
    private final Button backButton = new Button("Back");
    private TableView<Action> actionTableView = new TableView<>();
    private ActionController actionController = new ActionController();
    private final Button addActionButton = new Button("Add Action");
    private final Button deleteActionButton = new Button("Delete Customer");
    private final CheckBox deleteCheckBox = new CheckBox("Delete");
    private final Label responseLabel = new Label("Odgovor: ");
    private final ComboBox<Response> responseComboBox = new ComboBox<>();
    private final Label channelLabel = new Label("Kanal: ");
    private final ComboBox<Channel> channelComboBox = new ComboBox<>();
    private final Label descriptionLabel = new Label("Opis: ");
    private final TextField descriptionTextField = new TextField();

    public ActionCustomerPanel() {
        setSpacing(10);
        setPadding(new Insets(10));

        currentCustomerLabel.setText("Kupac: "+Controller.getSelctedCustomer().getName()+", "+Controller.getSelctedCustomer().getSurname());
        currentEmployeeLabel.setText(Controller.getCurrentEmployee().getName() + ", " + Controller.getCurrentEmployee().getSurname());
        BorderPane backButtonAndEmployeePanel = new BorderPane(currentCustomerLabel, null, currentEmployeeLabel, null, backButton);


        setupTableView();
        BorderPane buttonBorderPane = setupButtonPanel();
        GridPane textFieldPanel = setupTextFieldPanel();

        getChildren().addAll(backButtonAndEmployeePanel, actionTableView, buttonBorderPane, textFieldPanel);
    }

    private GridPane setupTextFieldPanel() {
        descriptionTextField.setPromptText("Enter description...");
        descriptionLabel.setMaxWidth(200);
        channelComboBox.setPromptText("Enter channel...");
        channelComboBox.setMaxWidth(200);
        responseComboBox.setPromptText("Enter response...");
        responseComboBox.setMaxWidth(200);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(channelLabel, 0, 0);
        gridPane.add(channelComboBox, 0, 1);
        gridPane.add(responseLabel, 1, 0);
        gridPane.add(responseComboBox, 1, 1);
        gridPane.add(descriptionLabel, 2, 0);
        gridPane.add(descriptionTextField, 2, 1);

        //UNOS COMBO BOXA
        ObservableList<Channel> channelObservableList = channelComboBox.getItems();
        ObservableList<Response> responseObservableList = responseComboBox.getItems();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Channel.findAll");
        List<Channel> channelList = query.getResultList();
        query = entityManager.createNamedQuery("Response.findAll");
        List<Response> responseList = query.getResultList();
        entityManager.getTransaction().commit();
        channelObservableList.addAll(channelList);
        responseObservableList.addAll(responseList);

        return gridPane;
    }

    private BorderPane setupButtonPanel() {
        HBox buttonHbox = new HBox(10);
        buttonHbox.getChildren().addAll(addActionButton, deleteActionButton, deleteCheckBox);
        backButton.setOnAction(this::onClickBackButton);
        addActionButton.setOnAction(this::onClickAddActionButton);
        deleteActionButton.setOnAction(this::onClickDelteActionButton);
        deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
        deleteActionButton.setDisable(true);
        BorderPane buttonBorderPane = new BorderPane(null, null, null, null, buttonHbox);
        return buttonBorderPane;
    }

    private void onClickDelteActionButton(ActionEvent actionEvent) {
        if (actionTableView.getSelectionModel().getSelectedItem() == null) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Selektujte akciju koju želite izbrisati");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            Action selectedAction = actionTableView.getSelectionModel().getSelectedItem();
            ObservableList<Action> customerObservableList = actionTableView.getItems();
            customerObservableList.remove(selectedAction);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Action action = entityManager.find(Action.class, selectedAction.getId());
            entityManager.remove(action);
            entityManager.getTransaction().commit();
        }
    }

    private void onClickAddActionButton(ActionEvent actionEvent) {
        if (channelComboBox.getValue() == null || responseComboBox.getValue() == null) {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Greška");
            dialog.setContentText("Niste popunili sva polja!");
            dialog.show();
            dialog.setHeight(150);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        } else {
            Action action = new Action();
            action.setDate(LocalDate.now());
            action.setCustomer(Controller.getSelctedCustomer());
            action.setEmployee(Controller.getCurrentEmployee());
            action.setChannel(channelComboBox.getValue());
            action.setResponse(responseComboBox.getValue());
            if (!descriptionTextField.getText().isBlank()) {
                action.setDescription(descriptionTextField.getText());
            }
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(action);
            entityManager.getTransaction().commit();

            ObservableList<Action> actionObservableList = actionTableView.getItems();
            actionObservableList.add(action);
        }
        descriptionTextField.clear();
        channelComboBox.setValue(null);
        responseComboBox.setValue(null);
    }

    private void onClickDeleteCheckBox(ActionEvent actionEvent) {
        if (deleteCheckBox.isSelected()) {
            deleteActionButton.setDisable(false);
        } else {
            deleteActionButton.setDisable(true);
        }
    }

    private void setupTableView() {
        ObservableList<Action> actionObservableList = actionController.loadActionsByCustomer();
        actionTableView.setItems(actionObservableList);
        actionTableView.setEditable(true);

        TableColumn<Action, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Action, LocalDate> dateColumn = new TableColumn<>("Datum");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Action, Employee> employeeColumn=new TableColumn<>("Zaposlenik");
        employeeColumn.setMinWidth(200);
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));

        TableColumn<Action, Channel> channelColumn = new TableColumn<>("Kanal");
        channelColumn.setMinWidth(200);
        channelColumn.setCellValueFactory(new PropertyValueFactory<>("channel"));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Channel.findAll");
        Channel[] channelsArray = (Channel[]) query.getResultList().toArray(new Channel[0]);
        entityManager.getTransaction().commit();
        channelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(channelsArray));
        channelColumn.setOnEditCommit(event -> onChannelFiledChange(event, r -> r.setChannel(event.getNewValue())));

        TableColumn<Action, Response> responseColumn = new TableColumn<>("Odgovor");
        responseColumn.setMinWidth(200);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        entityManager.getTransaction().begin();
        Query query1 = entityManager.createNamedQuery("Response.findAll");
        Response[] responsesArray = (Response[]) query1.getResultList().toArray(new Response[0]);
        entityManager.getTransaction().commit();
        responseColumn.setCellFactory(ComboBoxTableCell.forTableColumn(responsesArray));
        responseColumn.setOnEditCommit(event -> onResponseFiledChange(event, r -> r.setResponse(event.getNewValue())));

        TableColumn<Action, String> descriptionColumn = new TableColumn<>("Opis");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> onDescriptionFiledChange(event, r -> r.setDescription(event.getNewValue())));

        actionTableView.getColumns().addAll(idColumn, dateColumn,employeeColumn, channelColumn, responseColumn, descriptionColumn);
    }

    private <F> void onChannelFiledChange(TableColumn.CellEditEvent<Action, F> event, Consumer<Action> actionConsumer) {
        Action editAction = event.getRowValue();
        actionConsumer.accept(editAction);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(editAction);
        entityManager.getTransaction().commit();
    }

    private <F> void onResponseFiledChange(TableColumn.CellEditEvent<Action, F> event, Consumer<Action> actionConsumer) {
        Action editAction = event.getRowValue();
        actionConsumer.accept(editAction);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(editAction);
        entityManager.getTransaction().commit();
    }

    private <F> void onDescriptionFiledChange(TableColumn.CellEditEvent<Action, F> event, Consumer<Action> actionConsumer) {
        Action editAction = event.getRowValue();
        actionConsumer.accept(editAction);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Controller.PU_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(editAction);
        entityManager.getTransaction().commit();
    }


    private void onClickBackButton(ActionEvent actionEvent) {
        CustomerPanel customerPanel = new CustomerPanel();
        Scene scene = new Scene(customerPanel);
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Customers");
    }
}
