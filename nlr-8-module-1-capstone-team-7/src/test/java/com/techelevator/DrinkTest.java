package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;



public class DrinkTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void DrinkConfirmationMessageTest() {

        Drink drink = new Drink("Good Chip", "B1", new BigDecimal(5.25));
        drink.purchaseConfirmationMessage();

        Assert.assertEquals("Cheers Glug, Glug!", outputStreamCaptor.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

}