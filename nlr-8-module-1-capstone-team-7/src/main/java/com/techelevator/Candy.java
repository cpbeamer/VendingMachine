package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem {


    public Candy(String itemName, String code, BigDecimal price) {
        super(itemName, code, price);
    }

    @Override
    public void purchaseConfirmationMessage() {

        System.out.println("Munch Munch Mmm-Good!");

    }
}
