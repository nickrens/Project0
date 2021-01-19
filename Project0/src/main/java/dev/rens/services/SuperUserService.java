package dev.rens.services;

import java.util.Set;

import dev.rens.entities.User;

public interface SuperUserService {

	Set<User> viewAllUsers();
	
	User viewUser(String username);
	
	User updateUser(User user);
	
	boolean deleteUser(User user);
}
