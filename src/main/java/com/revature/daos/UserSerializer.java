package com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.User;
import com.revature.exceptions.UserAlreadyExistsException;

public class UserSerializer implements UserDao {

	
	private static File f = new File("src/main/resources/users/userObjectList1.txt");
	private static Set<User> userSet;
	public static final UserSerializer us = new UserSerializer();
	
	@SuppressWarnings("unchecked")
	private UserSerializer() {
		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
				Object obj = ois.readObject();
				if (obj instanceof HashSet<?>)
					userSet = (HashSet<User>)obj;
				else
					userSet = new HashSet<>();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			userSet = new HashSet<>();
		
	}
	@Override
	public void createUser(User u) throws UserAlreadyExistsException{
		
		if (u == null)
			return;
		
		User temp = findByUsername(u.getUserId());
		
		if (!(temp == null))
			throw new UserAlreadyExistsException("User already exists");
		
		if(u.getUserId().equalsIgnoreCase("register")) {
			throw new UserAlreadyExistsException("Cannot use the ID 'register'");
		}
			
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
			userSet.add(u);
			oos.writeObject(userSet);
		}
		catch(Exception e) {
			System.out.println("Something went wrong when writing to file.");
		}
		
		
		
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		for (User u: userSet) {
			if (username.equals(u.getId())&&password.equals(u.getPass())){
				return u;
			}
		}
		return null;
		
		
	}
	public User findByUsername(String username) {
		for (User u: userSet) {
			if (username.equals(u.getId())){
				return u;
			}
		}
		return null;
	}

	@Override
	public void updateUser(User u){
		if (u==null) {
			System.out.println("Please use delete functionality instead");
			return;
		}
		String idCheck = u.getUserId();
		User marker = null;
		for(User current: userSet) {
			if(current.getUserId().equals(idCheck))
			{
				marker = current;
			}
		}
		userSet.remove(marker);
		userSet.add(u);
	}
	
	
	@Override
	public void deleteUser(User u) {
		userSet.remove(u);
	}
	
	public void displayAllUserID(){
		for(User cur:userSet) {
			System.out.println(cur.getId());
		}
	}
	@Override
	public void makeDeposit(User u, int amount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void makeWithdrawl(User u, int amount) {
		// TODO Auto-generated method stub
		
	}
	
	
	}

