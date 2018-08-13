package com.revature.screens;

public class StartupScreen implements Screen {

	public Screen start() {
		System.out.println("Welcome to Bank ");
		return new LoginScreen();
	}
}
