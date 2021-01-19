package dev.rens.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.rens.entities.User;

public class UserDAOlocalImpl implements UserDAO{
	
	private static Map<String, User> user_table = new HashMap<String, User>();
	private static Integer idMaker = 0;

	public User createUser(User user) {
		user.setUserID((++idMaker).toString());
		user_table.put(idMaker.toString(), user);
		return user;
	}

	public User getUserById(String id) {
		User u = user_table.get(id);
		return u;
	}

	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<User>(user_table.values());
		return users;
	}

	public User updateUser(User user) {
		user_table.put(user.getUserID(), user);
		return user;
	}

	public boolean deleteUser(String id) {
		if(user_table.remove(id) == null) {
			return false;
		}else {
			return true;
		}
	}

	public User getUserByUsernameAndPassword(String username, String passord) {
		Set<User> users = new HashSet<User>(user_table.values());
		for(User user: users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(passord)) {
				return user;
			}
		}
		return null;
	}

	public User getUserByUsername(String username) {
		Set<User> users = new HashSet<User>(user_table.values());
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
