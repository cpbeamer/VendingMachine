package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem {

    public Gum(String itemName, String code, BigDecimal price) {
        super(itemName, code, price);
    }

    @Override
    public void purchaseConfirmationMessage() {

        System.out.println("Chew Chew, Pop!");

    }
}
