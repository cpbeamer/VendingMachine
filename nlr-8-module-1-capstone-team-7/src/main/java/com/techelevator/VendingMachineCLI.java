package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	BigDecimal currentMoney = BigDecimal.valueOf(0.00);


	private Menu menu;

	public VendingMachineCLI(Menu menu) throws FileNotFoundException {
		this.menu = menu;
	}



	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		Map<String, VendingMachineItem> inventoryHashMap = new HashMap<>();

		File inventoryFile = new File("vendingmachine.csv");

		inventoryHashMap = createInventoryHashMap(inventoryFile);

		cli.run(inventoryHashMap);
	}

	public void run(Map<String, VendingMachineItem> inventoryFile) {


		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayMenuItems(inventoryFile);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				choice = (String) menu.getChoiceFromOptionsPurchaseMenu(PURCHASE_MENU_OPTIONS, currentMoney);

				while (true) {
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						currentMoney = currentMoney.add(new BigDecimal(5));
						String feedMoneyLogString = "FEED MONEY: $" + 5.00 + " $" + currentMoney.toString();
						menu.printTransactionToLogFile(menu.vendingMachineLog , feedMoneyLogString);
						choice = (String) menu.getChoiceFromOptionsPurchaseMenu(PURCHASE_MENU_OPTIONS, currentMoney);
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						displayMenuItems(inventoryFile);
						currentMoney = menu.getUserItemSelection(inventoryFile, currentMoney);
						choice = (String) menu.getChoiceFromOptionsPurchaseMenu(PURCHASE_MENU_OPTIONS, currentMoney);
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						BigDecimal changeDue = currentMoney;
						currentMoney = BigDecimal.valueOf(0.00);

						String closeProgramTransactionLogString = "GIVE CHANGE: $" + changeDue.toString() + " $" + currentMoney.toString();
						menu.printTransactionToLogFile(menu.vendingMachineLog, closeProgramTransactionLogString);

						System.out.println("Here is your change!");
						returnChange(changeDue);
						break;

					}
				}


			}
		}
	}


	public void displayMenuItems(Map<String, VendingMachineItem> inventoryHashMap){

		for (Map.Entry<String, VendingMachineItem> item : inventoryHashMap.entrySet()){
			String key = item.getKey();
			VendingMachineItem value = inventoryHashMap.get(key);

			System.out.println(key + " " + value.getItemName() + " $" + value.getItemPrice() + " | Number of items: " + (value.getQuantity() <= 0 ? "SOLD OUT" : value.getQuantity()) );

		}

	}


	public static Map<String, VendingMachineItem> createInventoryHashMap(File inventoryFile) {

		Map<String, VendingMachineItem> inventoryMap = new LinkedHashMap<>();

		try (Scanner scanner = new Scanner(inventoryFile)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] itemInformation = line.split("\\|");

				switch (itemInformation[3]) {
					case "Chip" :
						Chips chip = new Chips(itemInformation[1], itemInformation[0], new BigDecimal(itemInformation[2]));
						inventoryMap.put(chip.getCode(), chip);
						break;
					case "Drink" :
						Drink drink = new Drink(itemInformation[1], itemInformation[0], new BigDecimal(itemInformation[2]));
						inventoryMap.put(drink.getCode(), drink);
						break;
					case "Candy" :
						Candy candy = new Candy(itemInformation[1], itemInformation[0], new BigDecimal(itemInformation[2]));
						inventoryMap.put(candy.getCode(), candy);
						break;
					case "Gum" :
						Gum gum = new Gum(itemInformation[1], itemInformation[0], new BigDecimal(itemInformation[2]));
						inventoryMap.put(gum.getCode(), gum);
						break;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return inventoryMap;

	}

	public void returnChange(BigDecimal totalChangeDue){

		int[] change = new int[4];

		BigDecimal numberOfQuartersBD = totalChangeDue.divide(new BigDecimal(0.25), 2, RoundingMode.HALF_UP);
		int numberOfQuarters = numberOfQuartersBD.intValue();

		totalChangeDue = totalChangeDue.subtract(new BigDecimal(numberOfQuarters * 0.25));
		BigDecimal numberOfDimesBD = totalChangeDue.divide(new BigDecimal(0.10), 2, RoundingMode.HALF_UP);
		int numberOfDimes = numberOfDimesBD.intValue();

		totalChangeDue = totalChangeDue.subtract(new BigDecimal(numberOfDimes * 0.10));
		BigDecimal numberOfNickelsBD = totalChangeDue.divide(new BigDecimal(0.05), 2, RoundingMode.HALF_UP);
		int numberOfNickels = numberOfNickelsBD.intValue();

		totalChangeDue = ((totalChangeDue.subtract(new BigDecimal(numberOfNickels * 0.05))));
		BigDecimal numberOfPenniesBD = totalChangeDue.divide(new BigDecimal(0.01), 2, RoundingMode.HALF_UP);
		int numberOfPennies = numberOfPenniesBD.intValue();

		change[0] = numberOfQuarters;
		change[1] = numberOfDimes;
		change[2] = numberOfNickels;
		change[3] = numberOfPennies;

		System.out.print(numberOfQuarters > 0 ? "Quarters: " + numberOfQuarters + " ":"");
		System.out.print(numberOfDimes > 0 ? "Dimes: " + numberOfDimes + " ":"");
		System.out.print(numberOfNickels > 0 ? "Nickels: " + numberOfNickels + " ":"");
		System.out.print((numberOfPennies > 0 ? "Pennies: " + numberOfPennies:""));
		System.out.println("");

	}




}
