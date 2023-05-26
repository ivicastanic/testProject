package com.imconsalting.projekat.UI;

import com.imconsalting.projekat.customer.Customer;
import com.imconsalting.projekat.employee.Employee;
import javafx.stage.Stage;

public class Controller {
    public static String PU_NAME="projectPU";
    private static Controller INSTANCE=null; //da bi bio singlton

    private Stage mainStage; //stage iz poctne klase, da bi bio vidljiv u svim klasama

    private static Employee currentEmployee; //trenutno logovani employee
    private static Employee editEmployee;
    private static Customer editCustomer;
    private static Customer selctedCustomer;


    private Controller(){

    }

    public static Customer getSelctedCustomer() {
        return selctedCustomer;
    }

    public static void setSelctedCustomer(Customer selctedCustomer) {
        Controller.selctedCustomer = selctedCustomer;
    }

    public static Customer getEditCustomer() {
        return editCustomer;
    }

    public static void setEditCustomer(Customer editCustomer) {
        Controller.editCustomer = editCustomer;
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
