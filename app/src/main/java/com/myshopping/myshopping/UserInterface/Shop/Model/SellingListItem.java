package com.myshopping.myshopping.UserInterface.Shop.Model;

public class SellingListItem {

    private String transaction_id;
    private String customer_phone_number;
    private String date;

    public SellingListItem() {
    }

    public SellingListItem(String transaction_id, String customer_phone_number, String date) {
        this.transaction_id = transaction_id;
        this.customer_phone_number = customer_phone_number;
        this.date = date;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCustomer_phone_number() {
        return customer_phone_number;
    }

    public void setCustomer_phone_number(String customer_phone_number) {
        this.customer_phone_number = customer_phone_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
