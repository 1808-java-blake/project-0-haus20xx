package com.revature.daos;

import com.revature.beans.User;

public class UserSerializer implements UserDao {

	public static final UserSerializer us = new UserSerializer();
	
	private UserSerializer() {
		
	}
	@Override
	public void createUser(User u) {
		if (u == null)
			return;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	public User findByUsernameAdmin(String username) {
		return null;
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

}
