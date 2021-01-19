package dev.rens.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;
import dev.rens.util.JDBCConnection;

public class AccountDAOImpl implements AccountDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	public Account createAccount(Account account, User user) {
		
		try {
			
			String sql = "Call add_account(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, account.getName());
			cs.setString(2, Double.toString(account.getBalance()));
			cs.setString(3, user.getUserID());
			
			cs.execute();
			return account;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Account getAccount(String id) {
		
		try {
			
			String sql = "Select * From accounts Where accountID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Account a = new Account();
				a.setAccountID(rs.getString("ACCOUNTID"));
				a.setName(rs.getString("NAME"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setUserID(rs.getString("USERID"));
				
				return a;
			} else {
				return null;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Set<Account> getAllAccountsFromUser(String userID) {
		
		try {
			
			String sql = "Select * From accounts Where userID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, userID);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				Account a = new Account();
				a.setAccountID(rs.getString("ACCOUNTID"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setName(rs.getString("NAME"));
				a.setUserID(rs.getString("USERID"));
				
				accounts.add(a);
			}
			
			return accounts;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Account updateAccount(Account account) {
		
		try {
			
			String sql = "Update accounts Set name = ?, balance = ? Where accountID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, account.getName());
			ps.setString(2, Double.toString(account.getBalance()));
			ps.setString(3, account.getAccountID());
			
			ps.execute();
			return account;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean deleteAccount(String id) {

		try {
			
			String sql = "Delete accounts Where accountID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);
			
			ps.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
