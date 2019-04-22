package com.myshopping.myshopping.UserInterface.Customer.Model;

public class ShoppingCardList {
    private String card_number;
    private String bank_name;
    private String credits;

    public ShoppingCardList() {
    }

    public ShoppingCardList(String card_number, String bank_name, String credits) {
        this.card_number = card_number;
        this.bank_name = bank_name;
        this.credits = credits;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
