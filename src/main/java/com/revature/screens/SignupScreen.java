package com.revature.screens;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.launcher.BankingLauncher;

public class SignupScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	public SignupScreen() {
	
	}
	@Override
	public Screen start() {
		Scanner scan = BankingLauncher.scan;
		User u = new User();
		try {
		System.out.println("Enter the Username you would like to register:");
		u.setUserId(scan.nextLine());
		System.out.println("Enter the Password you would like to use:");
		u.setPass(scan.nextLine());
		System.out.println("Please enter your initial deposit:");
		u.setBalance(Integer.parseInt(scan.nextLine()));
		
		ud.createUser(u);
		}
		catch(NumberFormatException e ) {
			System.out.println("Invalid input.");
			return this;
		}
		catch(NoSuchElementException e) {
			System.out.println("Invalid input.");
			return this;
		}
		catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			return this;
		}

		return new LoginScreen();
	}

}
