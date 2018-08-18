package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private String userID;
	private String pass;
	private static final String adminPasscode = "top level secret";
	private int balance;
	private List<Integer> tHistory;
	private boolean admin = false;
	private int id;
	
	
	public User() {
		super();
		this.tHistory = new ArrayList<Integer>();	
	}

	public User(String userID, String pass, int balance) {
		super();
		this.userID =userID;
		this.pass = pass;
		this.balance = balance;
		this.tHistory = new ArrayList<Integer>();
		this.tHistory.add(balance);
	}
	public User(String userID, String pass, int balance, boolean admin) {
		super();
		this.userID = userID;
		this.pass = pass;
		this.balance = balance;
		this.tHistory = new ArrayList<Integer>();
		this.tHistory.add(balance);
		this.admin = admin;
	}
	
	public boolean withdraw(int amount) {
		if (amount < 0)
			amount = amount * -1;
		if (amount > balance) {
			System.out.println("Insufficient funds!");
			return false;
		}
		balance -= amount;
		tHistory.add(-1*amount);
		return true;
	}
	
	public void deposit(int money) {
		balance += money;
		tHistory.add(money);
	}
	
	public void makeAdmin(String adminpass) {
		if(adminpass.equals(adminPasscode))
			this.admin = true;
		else
			System.out.println("Invalid administrator passcode");
	}
	
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserId() {
		return userID;
	}
	public void setUserId(String userID) {
		this.userID = userID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public List<Integer> gettHistory() {
		return tHistory;
	}
	public void settHistory(List<Integer> tHistory) {
		this.tHistory = tHistory;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((tHistory == null) ? 0 : tHistory.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (admin != other.admin)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (tHistory == null) {
			if (other.tHistory != null)
				return false;
		} else if (!tHistory.equals(other.tHistory))
			return false;
		return true;
	}
	public void setAdmin(boolean b) {
		admin = b;
	}
	public boolean isAdmin() {
		return admin;
	}
	@Override
	public String toString() {
		return "User[id=" + userID + ", balance=" + balance + ", admin=" + admin + "]";
	}
	
	public String historyToString() {
		
		int historyLength = tHistory.size();
		String returnMe = "";
		for (int i = 0; i<historyLength;i++) {

			if (tHistory.get(i)>=0)
				returnMe = returnMe +("Deposit: " + tHistory.get(i) + "\n");
			else
				returnMe = returnMe +("Withdrawl: " + tHistory.get(i) + "\n");
		}
		return returnMe;
	}
	
}
