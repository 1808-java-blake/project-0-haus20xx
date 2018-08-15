package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.launcher.BankingLauncher;

public class AdminMenuScreen implements Screen{
	
	private Scanner scan = BankingLauncher.scan;
	private UserDao ud = UserDao.currentUserDao;
	private User loggedIn;
	private User selectedUser;
	private String in;
	public AdminMenuScreen(User u) {
		this.loggedIn=u;
	}
	@Override
	public Screen start() {
		
		System.out.println("Please select fromt the following options");
		System.out.println("1. List all user IDs");
		System.out.println("2. Select a user");
		System.out.println("3. Promote user to admin");
		System.out.println("4. Return to standard menu");
		
		switch (scan.next()) {
		case "1":
			ud.displayAllUserID();
			break;
		case "2":
			
			break;
		case "3":
			System.out.println("Type in a validUserID");
			in = scan.nextLine();
			selectedUser = ud.findByUsername(in);
			if (selectedUser == null)
				System.out.println("Invalid UserID");
			else
			{
				System.out.println("Enter the universal admin code: ");
				in = scan.nextLine();
				selectedUser.makeAdmin(in);
			}
			break;
		case "4":
			return new MenuScreen(loggedIn);
		default:
			System.out.println("Invalid choice, please make sure you entered a listed number with no punctuation");
			break;
		}
		
		return this;
	}
	

}