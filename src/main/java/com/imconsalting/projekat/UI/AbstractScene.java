package com.imconsalting.projekat.UI;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class AbstractScene {

    public static Scene scene=null;

    public AbstractScene() {

    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Parent parent) {
        if(scene==null){
            scene=new Scene(parent);
        }else {
            scene.setRoot(parent);
        }
    }
}
