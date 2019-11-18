package com.example.stock.models;

public class Items {


    private String itemName;
    private int itemCount;
    private String itemPrice;


    public Items(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }
    public String getItemName() {
        return itemName;
    }
    public int getItemCount() {
        return itemCount;
    }
    public String getItemPrice() {
        return itemPrice;
    }

}
