package com.myshopping.myshopping.UserInterface.Mall.Model;

public class MallShopsList {

    private String shop_name;
    private String shop_owner_name;
    private String shop_owner_phone_number;

    public MallShopsList() {
    }

    public MallShopsList(String shop_name, String shop_owner_name, String shop_owner_phone_number) {
        this.shop_name = shop_name;
        this.shop_owner_name = shop_owner_name;
        this.shop_owner_phone_number = shop_owner_phone_number;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public void setShop_owner_name(String shop_owner_name) {
        this.shop_owner_name = shop_owner_name;
    }

    public String getShop_owner_phone_number() {
        return shop_owner_phone_number;
    }

    public void setShop_owner_phone_number(String shop_owner_phone_number) {
        this.shop_owner_phone_number = shop_owner_phone_number;
    }
}
