package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class MenuScreen implements Screen {

	private User loggedIn;
	private String holder;
	private int holdInt;
	private UserDao ud = UserDao.currentUserDao;
	private Scanner scan = new Scanner(System.in);
	public MenuScreen(User loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	@Override
	public Screen start() {
		System.out.println("Please select from the following options:");
		System.out.println("1. Deposit funds");
		System.out.println("2. Withdraw funds");
		System.out.println("3. View balance");
		System.out.println("4. Transaction History");
		System.out.println("5. Logout");
	 	System.out.println("6. Exit");
		if (loggedIn.isAdmin()) {
			System.out.println("7. ADMIN FUNCTIONS");
		}
		try {
		String selection = scan.nextLine();
		selection = selection.substring(0, 1);
		switch (selection) {
		case "1":
			System.out.println("How much would you like to deposit?(Whole Dollars)");
			holder = scan.nextLine();
			holdInt = Integer.parseInt(holder);
			loggedIn.deposit(holdInt);
			ud.makeDeposit(loggedIn, holdInt);
			break;
		case "2":
			System.out.println("How much would you like to withdraw?(Whole Dollars)");
			holder = scan.nextLine();
			holdInt = Integer.parseInt(holder);
			if(loggedIn.withdraw(holdInt))
				ud.makeWithdrawl(loggedIn,holdInt);
			break;
		case "3":
			System.out.println("User balance: $"+loggedIn.getBalance()+".00");
			break;
		case "4":
			System.out.println(loggedIn.historyToString());
			break;
		case "5":
			return new LoginScreen();
		case "6":
			System.out.println("Thank you for using Bank Bank");
			ud.updateUser(loggedIn);
			return null;
		case "7":
			if(loggedIn.isAdmin())
				return new AdminMenuScreen(loggedIn);
		default:
			System.out.println("Invalid choice, ensure you entered a listed number with no punctuation");
			return this;
		}
		return this;
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid user input! Please try again.");
			return this;
		}
	}

	
}
