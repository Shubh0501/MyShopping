package com.myshopping.myshopping.UserInterface.Shop.Model;

public class EmployeeListItem {
    String emp_id;
    String emp_name;
    String phone_number;
    String position;

    public EmployeeListItem() {
    }

    public EmployeeListItem(String emp_id, String emp_name, String phone_number, String position) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.phone_number = phone_number;
        this.position = position;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
