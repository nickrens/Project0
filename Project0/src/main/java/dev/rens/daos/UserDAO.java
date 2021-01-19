package dev.rens.daos;

import java.util.Set;

import dev.rens.entities.User;

public interface UserDAO {

	User createUser(User user);
	
	User getUserById(String id);
	
	User getUserByUsername(String username);
	
	User getUserByUsernameAndPassword(String username, String passord);
	
	Set<User> getAllUsers();
	
	User updateUser(User user);
	
	boolean deleteUser(String id);
}
