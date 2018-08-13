package com.revature.daos;

import com.revature.beans.User;


public interface UserDao {
	 public static final UserDao currentUserDao = UserSerializer.us;
	//This strategy of implementation allows for us to update the backend of data access, for example to
	//change the backend to SQL you would only need to replace the UserSerializer class
	//with a new class utilizing that backend and changing the one line here
	
	void createUser(User u);
	
	User findByUsernameAndPassword(String username, String password);
	
	void updateUser(User u);
	
	void deleteUser(User u);
	

}
