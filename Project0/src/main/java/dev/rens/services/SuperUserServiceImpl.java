package dev.rens.services;

import java.util.Set;

import dev.rens.daos.AccountDAO;
import dev.rens.daos.AccountDAOImpl;
import dev.rens.daos.AccountDAOlocalImpl;
import dev.rens.daos.UserDAO;
import dev.rens.daos.UserDAOImpl;
import dev.rens.daos.UserDAOlocalImpl;
import dev.rens.entities.Account;
import dev.rens.entities.User;

public class SuperUserServiceImpl extends UserServiceImpl implements SuperUserService{

	private static UserDAO udao = new UserDAOImpl();
	private static AccountDAO adao = new AccountDAOImpl();
	
	public Set<User> viewAllUsers() {
		return udao.getAllUsers();
	}

	public User viewUser(String username) {
		return udao.getUserByUsername(username);
	}

	public User updateUser(User user) {
		udao.updateUser(user);
		return user;
	}

	public boolean deleteUser(User user) {
		return udao.deleteUser(user.getUserID());
	}

}
