package com.myshopping.myshopping.UserInterface.Customer.Model;

public class ListItem {

    private String transaction_id;
    private String shop_name;
    private String date;

    public ListItem(){
    }

    public ListItem(String transaction_id, String shop_name, String date) {
        this.transaction_id = transaction_id;
        this.shop_name = shop_name;
        this.date = date;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
