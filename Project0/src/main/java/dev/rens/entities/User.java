package dev.rens.entities;


public class User {

	private String userID;
	private String username;
	private String password;
	private boolean superUser;
	
	public User() {
		super();
	}

	public User(String username, String password, boolean superUser) {
		super();
		this.username = username;
		this.password = password;
		this.superUser = superUser;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", superUser=" + superUser + "]";
	}

	
	
	
	
}
