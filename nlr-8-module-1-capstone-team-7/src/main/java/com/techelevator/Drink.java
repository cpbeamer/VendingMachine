package com.techelevator;

import java.math.BigDecimal;

public class Drink extends VendingMachineItem {

    public Drink(String itemName, String code, BigDecimal price) {
        super(itemName, code, price);
    }

    @Override
    public void purchaseConfirmationMessage() {

        System.out.println("Cheers Glug, Glug!");

    }
}
