package com.revature.launcher;

import java.util.Scanner;

//import com.revature.beans.User;
//import com.revature.daos.UserDao;
import com.revature.screens.Screen;
import com.revature.screens.StartupScreen;

public class BankingLauncher {

	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		Screen currentScreen = new StartupScreen();
		while(true) {
			if (currentScreen == null)
				break;
			currentScreen = currentScreen.start();
		}
		scan.close();
	}
}
