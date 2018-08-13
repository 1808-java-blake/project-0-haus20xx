package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.UserDao;

public class LoginScreen implements Screen{

	private UserDao ud = UserDao.currentUserDao;
	private Scanner in = new Scanner(System.in);
	@Override
	public Screen start() {
		String userName, userPass;
		System.out.println("Please enter your username or type Register to sign up");
		userName = in.nextLine();
		
		if ("register".equalsIgnoreCase(userName)) {
			return new SignupScreen();
		}
		System.out.println("Please enter your password:");
		userPass = in.nextLine();
		
		if (ud.findByUsernameAndPassword(userName, userPass) == null) {
			System.out.println("Incorrect login!");
			return this;
		}
		else if (ud.findByUsernameAndPassword(userName, userPass)!= null) {
			System.out.println("Logging in...");
			return new MenuScreen(ud.findByUsernameAndPassword(userName, userPass));
		}

		return this;
	}
	

}
