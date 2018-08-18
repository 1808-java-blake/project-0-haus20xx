package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.utility.ConnectionUtil;

public class UserDaoJdbc implements UserDao{
	
	public static final UserDaoJdbc udj = new UserDaoJdbc();
	
	private Logger log = Logger.getRootLogger();
	private ConnectionUtil cu = ConnectionUtil.cu;

	
	private UserDaoJdbc() {
		
	}
	@Override
	public void createUser(User u) throws UserAlreadyExistsException {
		if ("register".equalsIgnoreCase(u.getUserId()))
			throw new UserAlreadyExistsException("Register is a reserved username");
		if("exit".equalsIgnoreCase(u.getUserId()))
			throw new UserAlreadyExistsException("Exit is a reserved username");
		if (this.findByUsername(u.getUserId()) != null)
			throw new UserAlreadyExistsException("Username already exists");
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO users(user_id,pass,balance,is_admin) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, u.getUserId());
				ps.setString(2, u.getPass());
				ps.setDouble(3, u.getBalance());
				ps.setBoolean(4, u.isAdmin());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					System.out.println("User created! id = " + rs.getInt("user_serial"));
					u.setId(rs.getInt("user_serial"));	
				}
				ps = conn.prepareStatement("INSERT INTO transactions(user_serial,amount) VALUES (?,?)");
				ps.setInt(1, u.getId());
				ps.setDouble(2, u.getBalance());
				ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id=? and pass=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getString("user_id"));
				u.setPass(rs.getString("pass"));
				u.setId(rs.getInt("user_serial"));
				u.setAdmin(rs.getBoolean("is_admin"));
				ps = conn.prepareStatement("SELECT amount FROM transactions WHERE user_serial = ? ORDER BY transaction_serial asc");
				ps.setInt(1, rs.getInt("user_serial"));
				ResultSet rs2 = ps.executeQuery();
				List<Integer> l = new ArrayList<>();
				log.debug("The size of the queried array results is :"+ rs2.getFetchSize());
				while(rs2.next()) {
					l.add((int)rs2.getDouble("amount"));
					log.debug("the amount added is " + rs2.getDouble("amount"));
				}
				u.settHistory(l);
				u.setBalance((int)rs.getDouble("balance"));
				return u;
			}
			else {
				log.trace("failed to find user");
			}
		}
		catch(SQLException e) {
			
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getString("user_id"));
				u.setPass(rs.getString("pass"));
				u.setId(rs.getInt("user_serial"));
				u.setAdmin(rs.getBoolean("is_admin"));
				ps = conn.prepareStatement("SELECT amount FROM transactions WHERE user_serial = ? ORDER BY transaction_serial asc");
				ps.setInt(1, rs.getInt("user_serial"));
				ResultSet rs2 = ps.executeQuery();
				List<Integer> l = new ArrayList<>();
				
				while(rs2.next()) {
					l.add((int)rs2.getDouble("amount"));
				}
				u.settHistory(l);
				u.setBalance((int)rs.getDouble("balance"));
				return u;
			}
			else {
				log.trace("failed to find user with credentials");
			}
		}
		catch(SQLException e) {
			
		}
		return null;
	}

	@Override
	public void updateUser(User u) {

		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE users SET user_id = ?, pass = ?, balance = ?, is_admin = ? WHERE user_serial = ?");
				ps.setString(1, u.getUserId());
				ps.setString(2, u.getPass());
				ps.setDouble(3, u.getBalance());
				ps.setBoolean(4, u.isAdmin());
				ps.setInt(5, u.getId());
				ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User u) {
	}

	@Override
	public void displayAllUserID() {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT user_id FROM users");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				System.out.println(rs.getString("user_id"));
		}
		catch(SQLException e) {
			
		}
		
	}
	@Override
	public void makeDeposit(User u, int amount) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO transactions(user_serial,amount) VALUES (?,?)");
			ps.setInt(1, u.getId());
			ps.setInt(2, amount);
			ps.executeUpdate();
			updateUser(u);
		}
		catch(SQLException e){	
		}
	}
	@Override
	public void makeWithdrawl(User u, int amount) {
		if (amount > 0)
			amount = amount * -1;
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO transactions(user_serial,amount) VALUES (?,?)");
			ps.setInt(1, u.getId());
			ps.setInt(2, amount);
			updateUser(u);
		}
		catch(SQLException e){
			
		}
	}

}
