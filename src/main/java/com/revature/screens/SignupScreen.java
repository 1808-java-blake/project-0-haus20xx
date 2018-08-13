package com.revature.screens;

import java.util.Scanner;

public class SignupScreen implements Screen{

	public SignupScreen() {
		
	}
	@Override
	public Screen start() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Username you would like to register:");
		
		
		
		
		return new LoginScreen();
	}

}
