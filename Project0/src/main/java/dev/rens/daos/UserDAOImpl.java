package dev.rens.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dev.rens.entities.User;
import dev.rens.util.JDBCConnection;

public class UserDAOImpl implements UserDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	public User createUser(User user) {

		try {
			
			String sql = "Call add_user(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			if(user.isSuperUser()) {
				cs.setString(3, "1");
			}else {
				cs.setString(3, "0");
			}
			
			cs.execute();
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserById(String id) {

		try {
			
			String sql = "Select * From users Where userID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserID(rs.getString("USERID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setSuperUser(rs.getBoolean("SUPERUSER"));
				
				return u;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public User getUserByUsername(String username) {
		
		try {
			
			String sql = "Select * From users Where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserID(rs.getString("USERID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setSuperUser(rs.getBoolean("SUPERUSER"));
				
				return u;
			}else {
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public User getUserByUsernameAndPassword(String username, String passord) {
		
		try {
			
			String sql = "Select * From users Where username = ? And password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, passord);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserID(rs.getString("USERID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setSuperUser(rs.getBoolean("SUPERUSER"));
				
				return u;
			} else {
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Set<User> getAllUsers() {
		
		try {
			
			String sql = "Select * From users";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<User> users = new HashSet<User>();
			
			while(rs.next()) {
				User u = new User();
				u.setUserID(rs.getString("USERID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setSuperUser(rs.getBoolean("SUPERUSER"));
				
				users.add(u);
			}
			
			return users;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public User updateUser(User user) {
		
		try {
			
			String sql = "Update users Set username = ?, password = ?, superUser = ? Where userID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setBoolean(3, user.isSuperUser());
			ps.setString(4, user.getUserID());
			
			ps.executeQuery();
			return user;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean deleteUser(String id) {

		try {
			
			String sql = "Delete users Where userid = ?";
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
