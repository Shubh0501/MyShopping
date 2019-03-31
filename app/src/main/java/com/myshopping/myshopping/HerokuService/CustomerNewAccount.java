package com.myshopping.myshopping.HerokuService;

import com.google.gson.annotations.SerializedName;

public class CustomerNewAccount {

    @SerializedName("Name")
    String name;

    @SerializedName("PhoneNumber")
    String phonenumber;

    @SerializedName("Password")
    String password;

    public CustomerNewAccount(String name, String phonenumber, String password) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }
    public String getPhonenumber() {
        return this.phonenumber;
    }
}
