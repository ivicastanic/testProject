package com.imconsalting.projekat.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class EntryPointOfApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartPanel startPanel=new StartPanel();
        AbstractScene.setScene(startPanel);
        stage.setScene(AbstractScene.getScene());
        stage.setTitle("Projekat");

        stage.show();
    }
}