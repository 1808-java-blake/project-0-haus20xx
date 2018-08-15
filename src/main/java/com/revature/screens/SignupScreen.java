package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.utility.UtilityClass;

public class SignupScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	public SignupScreen() {
	
	}
	@Override
	public Screen start() {
		Scanner scan = new Scanner(System.in);
		User u = new User();
		System.out.println("Enter the Username you would like to register:");
		u.setId(scan.nextLine());
		System.out.println("Enter the Password you would like to use:");
		u.setPass(scan.nextLine());
		System.out.println("Please enter your initial deposit:");
		u.setBalance(Integer.parseInt(UtilityClass.removePunctuation(scan.nextLine())));
		try {
		ud.createUser(u);
		}
		catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			return this;
			
		}
		System.out.println("Successful account creation!");
		return new LoginScreen();
	}

}
