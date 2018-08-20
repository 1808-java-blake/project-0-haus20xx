package com.revature.daos;

import com.revature.beans.User;
import com.revature.exceptions.UserAlreadyExistsException;


public interface UserDao {
	 public static final UserDao currentUserDao = UserDaoJdbc.udj;
	//This strategy of implementation allows for us to update the backend of data access, for example to
	//change the backend to SQL you would only need to replace the UserSerializer class
	//with a new class utilizing that backend and changing the one line here
	
	void createUser(User u) throws UserAlreadyExistsException;
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	void updateUser(User u);
	
	void displayAllUserID();
	
	void makeDeposit(User u, int amount);
	
	void makeWithdrawl(User u, int amount);

}
