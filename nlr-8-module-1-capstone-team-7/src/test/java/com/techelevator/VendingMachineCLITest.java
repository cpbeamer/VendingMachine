package com.techelevator;

import com.techelevator.view.Menu;

import org.junit.*;
import org.junit.runners.MethodSorters;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.util.Map;

public class VendingMachineCLITest {


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Menu menu;

    VendingMachineCLI vendingMachineCLI = new VendingMachineCLI(menu);

    public VendingMachineCLITest() throws FileNotFoundException {};

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void VendingMachineCLIChangeTest() {
        vendingMachineCLI.returnChange(new BigDecimal(1.00));

        Assert.assertEquals("Quarters: 4", outputStreamCaptor.toString().trim());
    }

    @Test
    public void VendingMachineCLIChangeTest2() {
        vendingMachineCLI.returnChange(new BigDecimal(3.10));

        Assert.assertEquals("Quarters: 12 Dimes: 1" , outputStreamCaptor.toString().trim());
    }

    @Test
    public void VendingMachineCLIChangeTest3() {
        vendingMachineCLI.returnChange(new BigDecimal(3.40));

        Assert.assertEquals("Quarters: 13 Dimes: 1 Nickels: 1" , outputStreamCaptor.toString().trim());
    }

    @Test
    public void VendingMachineCLIChangeTest4() {
        vendingMachineCLI.returnChange(new BigDecimal(3.43));

        Assert.assertEquals("Quarters: 13 Dimes: 1 Nickels: 1 Pennies: 3" , outputStreamCaptor.toString().trim());
    }

    @Test
    public void createInventoryHashMapTest() {
        File newFile = new File("vendingmachine.csv");

        Map<String, VendingMachineItem> actualMap = VendingMachineCLI.createInventoryHashMap(newFile);
        actualMap.get("A1").purchaseConfirmationMessage();

        Assert.assertEquals("Potato Crisps", actualMap.get("A1").getItemName());
        Assert.assertEquals("Crunch Crunch, Crunch!", outputStreamCaptor.toString().trim());

    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
}
