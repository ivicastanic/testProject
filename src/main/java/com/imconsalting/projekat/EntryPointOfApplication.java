package com.imconsalting.projekat;

import com.imconsalting.projekat.UI.Controller;
import com.imconsalting.projekat.UI.paneli.LoginPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPointOfApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Controller.instance().setMainStage(stage);
        LoginPanel loginPanel=new LoginPanel();
        Scene scene=new Scene(loginPanel);
        stage.setScene(scene);
        stage.setTitle("Projekat");
        stage.show();
    }
}