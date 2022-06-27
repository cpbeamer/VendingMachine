package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingMachineItem {


    public Chips(String itemName, String code, BigDecimal price) {
        super(itemName, code, price);
    }

    @Override
    public void purchaseConfirmationMessage() {

        System.out.println("Crunch Crunch, Crunch!");

    }


}
