package com.imconsalting.projekat.UI;

import com.imconsalting.projekat.employee.Employee;
import javafx.stage.Stage;

public class Controller {
    public static String PU_NAME="projectPU";
    private static Controller INSTANCE=null;

    private Stage mainStage;

    private static Employee currentEmployee;


    private Controller(){

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
    }

    public static Controller instance(){
        if(INSTANCE==null){
            INSTANCE=new Controller();
        }
        return INSTANCE;
    }
}
