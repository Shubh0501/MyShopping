package com.myshopping.myshopping.UserInterface.Customer.Model;

public class ListItem {

    private String product_name;
    private String product_quantity;
    private String shop_name;
    private String date;

    public ListItem(){

    }

    public ListItem(String product_name, String product_quantity, String shop_name, String date) {
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.shop_name = shop_name;
        this.date = date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
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
