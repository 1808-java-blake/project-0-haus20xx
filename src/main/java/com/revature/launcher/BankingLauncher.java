package com.revature.launcher;

import com.revature.screens.Screen;
import com.revature.screens.StartupScreen;

public class BankingLauncher {

	public static void main(String[] args) {
		Screen currentScreen = new StartupScreen();
		while(true) {
			currentScreen = currentScreen.start();
		}
	}
}
