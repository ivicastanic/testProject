package com.imconsalting.projekat.UI;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NekiPanel extends VBox {

    private final Button nextButton;
    private final Button cancelButton;
    private final Button deleteButton;

    public NekiPanel() {
        setSpacing(10);
        nextButton=new Button("NEXT");
        nextButton.setOnAction(this::onClickNextButton);
        cancelButton=new Button("CANCEL");
        deleteButton=new Button("DELETE");
        getChildren().addAll(nextButton,cancelButton,deleteButton);
    }

    private void onClickNextButton(ActionEvent actionEvent){
        Button backButton=new Button("BACK");
        backButton.setOnAction(this::onClickBackButton);
        Button button2=new Button("22222");
        Button button3=new Button("33333");
        HBox hBox=new HBox(10);
        hBox.getChildren().addAll(backButton,button2,button3);
        AbstractScene.setScene(hBox);

    }

    private void onClickBackButton(ActionEvent actionEvent) {
        AbstractScene.setScene(this);
    }
}
