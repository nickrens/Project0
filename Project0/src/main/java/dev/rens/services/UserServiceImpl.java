package dev.rens.services;

import java.util.HashSet;
import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;
import dev.rens.exceptions.AccountNameUsedException;
import dev.rens.exceptions.InvalidUsernameOrPasswordException;
import dev.rens.exceptions.NotAbleToRemoveAccountException;
import dev.rens.exceptions.UsernameHasBeenTakenException;
import dev.rens.daos.AccountDAO;
import dev.rens.daos.AccountDAOImpl;
import dev.rens.daos.AccountDAOlocalImpl;
import dev.rens.daos.UserDAO;
import dev.rens.daos.UserDAOImpl;
import dev.rens.daos.UserDAOlocalImpl;

public class UserServiceImpl implements UserService{
	
	private static UserDAO udao = new UserDAOImpl();
	private static AccountDAO adao = new AccountDAOImpl();

	public Account makeBankAccount(String name, User user) throws AccountNameUsedException {
		Set<Account> myAccounts = ShowBankAccounts(user);
		for(Account account: myAccounts) {
			if(account.getName().equals(name)) {
				throw new AccountNameUsedException();
			}
		}
		Account a = new Account(name);
		adao.createAccount(a, user);
		return a;
	}

	public boolean deleteBankAccount(Account account) throws NotAbleToRemoveAccountException {
		if(account.getBalance() > 0) {
			throw new NotAbleToRemoveAccountException();
		}else {
			adao.deleteAccount(account.getAccountID());
			return true;
		}
	}

	public Set<Account> ShowBankAccounts(User user) {
		Set<Account> myAccounts = new HashSet<Account>();
		myAccounts = adao.getAllAccountsFromUser(user.getUserID());
		return myAccounts;
	}

	public User login(String username, String password) throws InvalidUsernameOrPasswordException {
		User u = udao.getUserByUsernameAndPassword(username, password);
		if(u != null) {
			return u;
		}else {
			throw new InvalidUsernameOrPasswordException();
		}
	}

	public User createNewUser(String username, String password, boolean superUser) throws UsernameHasBeenTakenException {
		User u = new User(username, password, superUser);
		Set<User> all = udao.getAllUsers();
		for(User user: all) {
			if(user.getUsername().equals(username)) {
				throw new UsernameHasBeenTakenException();
			}
		}
		udao.createUser(u);
		return u;
	}

}
