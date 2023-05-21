package com.imconsalting.projekat.UI;

import com.imconsalting.projekat.employee.Employee;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    public static String PU_NAME="projectPU";
    private static Controller INSTANCE=null; //da bi bio singlton

    private Stage mainStage; //stage iz poctne klase, da bi bio vidljiv u svim klasama

    private static Employee currentEmployee; //trenutno logovani employee
    private static Employee editEmployee;


    private Controller(){

    }


    public static Employee getEditEmployee() {
        return editEmployee;
    }

    public static void setEditEmployee(Employee editEmployee) {
        Controller.editEmployee = editEmployee;
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public static void setCurrentEmployee(Employee currentEmployee) {
        Controller.currentEmployee = currentEmployee;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    } //setuje stage

    public static Controller instance(){
        if(INSTANCE==null){
            INSTANCE=new Controller(); //samo se prvi put konstruisee
        }
        return INSTANCE;
    }
}
