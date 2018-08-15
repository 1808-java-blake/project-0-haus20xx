package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5324650928845129578L;
	private String id;
	private String pass;
	private static String adminPasscode;
	private double balance;
	private List<Integer> tHistory;
	private boolean admin = false;
	
	public User() {
		super();
		this.tHistory = new ArrayList<Integer>();	
	}

	public User(String id, String pass, int balance) {
		super();
		this.id = id;
		this.pass = pass;
		this.balance = balance;
		this.tHistory = new ArrayList<Integer>();
		this.tHistory.add(balance);
	}
	public User(String id, String pass, int balance, boolean admin) {
		super();
		this.id = id;
		this.pass = pass;
		this.balance = balance;
		this.tHistory = new ArrayList<Integer>();
		this.tHistory.add(balance);
		this.admin = admin;
	}
	
	public void withdraw() {
		
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

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
		this.tHistory.add(balance);
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	public boolean isAdmin() {
		return admin;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", balance=" + balance + ", tHistory=" + tHistory + ", admin="
				+ admin + "]";
	}
	
	
}
