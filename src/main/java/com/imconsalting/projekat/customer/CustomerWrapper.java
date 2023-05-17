package com.imconsalting.projekat.customer;

import com.imconsalting.projekat.empstatus.EmpStatus;

public class CustomerWrapper {
    private final Customer customer;

    //DI Dependency Injection

    public CustomerWrapper(Customer customer) {
        this.customer = customer;
    }

    public Object getColumValue(int index) {
        switch (index) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getName();
            case 2:
                return customer.getSurname();
            case 3:
                return customer.getBirthday();
            case 4:
                return customer.getAddress();
            case 5:
                return customer.getMobile();
            case 6:
                return customer.getEmail();
            case 7:
                return customer.getEmpStatus().getName();
            case 8:
                return customer.getProfession().getName();
            case 9:
                return customer.getCompany().getName();
            case 10:
                return customer.getEmployee().getName();
            case 11:
                return customer.getDateRegisty();
        }
        return null;
    }

    public void setColumnValue(Object aValue,int index) {
        switch (index) {
            case 1:
                customer.setName((String) aValue);
                break;
            case 2:
                customer.setSurname((String) aValue);
                break;
        }
    }
}