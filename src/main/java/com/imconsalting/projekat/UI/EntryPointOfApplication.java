package com.imconsalting.projekat.UI;

import com.imconsalting.projekat.UI.paneli.LoginPanel;
import javafx.application.Application;
import javafx.stage.Stage;


public class EntryPointOfApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginPanel loginPanel=new LoginPanel();
        AbstractScene.setScene(loginPanel);
        stage.setScene(AbstractScene.getScene());
        stage.setTitle("Projekat");

        stage.show();
    }
}