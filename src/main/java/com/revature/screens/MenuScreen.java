package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.utility.UtilityClass;

public class MenuScreen implements Screen {

	private User loggedIn;
	private String holder;
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
		if (loggedIn.isAdmin()) {
			System.out.println("5. View another account (ADMINS ONLY)");
		}
		String selection = scan.nextLine();
		selection = selection.substring(0, 1);
		switch (selection) {
		case "1":
			System.out.println("How much would you like to deposit?");
			holder = scan.nextLine();
			holder = UtilityClass.removePunctuation(holder);
			loggedIn.deposit(holder);
			break;

		default:
			System.out.println("Invalid choice");
			return this;
		}
		
		
		return this;
	}

	
}
