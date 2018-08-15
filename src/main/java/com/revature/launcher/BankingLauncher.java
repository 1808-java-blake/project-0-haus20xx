package com.revature.launcher;

import com.revature.screens.Screen;
import com.revature.screens.StartupScreen;

public class BankingLauncher {

	public static void main(String[] args) throws Exception {
		Screen currentScreen = new StartupScreen();
//		User adm = new User("kh14c","supersecret",1000000,true);
//		UserDao dao = UserDao.currentUserDao;
//		dao.createUser(adm);
		while(true) {
			if (currentScreen == null)
				break;
			currentScreen = currentScreen.start();
		}
	}
}
