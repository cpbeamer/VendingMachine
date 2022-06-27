package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;



public class GumTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void GumConfirmationMessageTest() {

        Gum gum = new Gum("Good Chip", "B1", new BigDecimal(5.25));
        gum.purchaseConfirmationMessage();

        Assert.assertEquals("Chew Chew, Pop!", outputStreamCaptor.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

}