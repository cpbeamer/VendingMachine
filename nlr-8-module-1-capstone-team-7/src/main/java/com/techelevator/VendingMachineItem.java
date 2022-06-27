package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItem {

    private String itemName;
    private String code;
    private BigDecimal itemPrice;
    private int quantity;

    // Constructor

    public VendingMachineItem(String itemName, String code, BigDecimal price) {

        this.itemName = itemName;
        this.code = code;
        this.itemPrice = price;
        this.quantity = 5;
    }

    // Methods

    public abstract void purchaseConfirmationMessage();


    // Getters

    public String getItemName() {
        return itemName;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
