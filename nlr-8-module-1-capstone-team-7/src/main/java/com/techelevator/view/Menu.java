package com.techelevator.view;

import com.techelevator.VendingMachineItem;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	public File vendingMachineLog = new File("log.txt");

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public BigDecimal getUserItemSelection(Map<String, VendingMachineItem> inventoryHashMap, BigDecimal currentMoney) {

		System.out.print(System.lineSeparator() + "Please enter an item code >>> ");

		String userItemSelection = in.nextLine().trim().toUpperCase();

		if (!inventoryHashMap.containsKey(userItemSelection)) {
			System.out.println("This product does not exist");
			return currentMoney;
		}

		if (inventoryHashMap.get(userItemSelection).getQuantity() <= 0) {
			System.out.println("ITEM SOLD OUT");
			return currentMoney;
		}

		if (inventoryHashMap.get(userItemSelection).getItemPrice().compareTo(currentMoney) <= 0) {
			VendingMachineItem localObject = inventoryHashMap.get(userItemSelection);

			currentMoney = currentMoney.subtract(localObject.getItemPrice());
			localObject.setQuantity(localObject.getQuantity() - 1);

			String messageForActivityLog = localObject.getItemName() + " " + localObject.getCode() + " $" + localObject.getItemPrice().toString() + " $" + currentMoney.toString();
			printTransactionToLogFile(vendingMachineLog, messageForActivityLog);

			System.out.println("\nYou purchased: " + localObject.getItemName() + " for $" + localObject.getItemPrice() + " | Balance Remaining: $" + currentMoney);
			localObject.purchaseConfirmationMessage();
			System.out.println("");

			return currentMoney;
		} else {
			System.out.println("Insufficient Funds!");
		}

		return currentMoney;

	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;

		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptionsPurchaseMenu(Object[] options, BigDecimal currentMoney) {
		Object choice = null;
		System.out.println("Current Money Provided: $" + currentMoney);

		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public void displayCurrentMoney(BigDecimal currentMoney) {
		System.out.println("Current Money Provided: $" + currentMoney);

	}



	public void printTransactionToLogFile(File logFile, String stringToPrint) {

		boolean append = logFile.exists() ? true : false;
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String myDate = DateFor.format(date);

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, append))) {
			writer.append(myDate + " " + stringToPrint + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
