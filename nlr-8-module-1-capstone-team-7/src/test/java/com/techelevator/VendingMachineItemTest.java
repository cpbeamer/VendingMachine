package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendingMachineItemTest {

    @Test
    public void VendingMachineItemGetQuantityTest() {
        VendingMachineItem newItem = new VendingMachineItem("Cola", "A1", new BigDecimal(1)) {
            @Override
            public void purchaseConfirmationMessage() {
                System.out.println("");
            }
        };

        int actualQuantityValue = newItem.getQuantity();
        int expectedQuantityValue = 5;

        Assert.assertEquals(expectedQuantityValue, actualQuantityValue);

    }


    @Test
    public void VendingMachineGetCodeTest() {
        VendingMachineItem newItem = new VendingMachineItem("Cola", "A1", new BigDecimal(1)) {
            @Override
            public void purchaseConfirmationMessage() {
                System.out.println("");
            }
        };

        String actualItemCode = newItem.getCode();
        String expectedItemCode = "A1";

        Assert.assertEquals(expectedItemCode, actualItemCode);

    }


    @Test
    public void VendingMachineGetItemPriceTest() {
        VendingMachineItem newItem = new VendingMachineItem("Cola", "A1", new BigDecimal(1)) {
            @Override
            public void purchaseConfirmationMessage() {
                System.out.println("");
            }
        };

        BigDecimal actualItemPrice = newItem.getItemPrice();
        BigDecimal expectedItemPrice = new BigDecimal(1);

        Assert.assertEquals(expectedItemPrice, actualItemPrice);

    }


    @Test
    public void VendingMachineSetQuantityTest() {
        VendingMachineItem newItem = new VendingMachineItem("Cola", "A1", new BigDecimal(1)) {
            @Override
            public void purchaseConfirmationMessage() {
                System.out.println("");
            }
        };

        newItem.setQuantity(2);

        int actualItemQuantity = newItem.getQuantity();
        int expectedItemQuantity = 2;

        Assert.assertEquals(expectedItemQuantity, actualItemQuantity);

    }





}
