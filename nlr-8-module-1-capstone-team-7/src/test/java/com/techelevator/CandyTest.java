package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;



public class CandyTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void CandyConfirmationMessageTest() {

        Candy candy = new Candy("Good Candy", "A1", new BigDecimal(5.25));
        candy.purchaseConfirmationMessage();

        Assert.assertEquals("Munch Munch Mmm-Good!", outputStreamCaptor.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

}
