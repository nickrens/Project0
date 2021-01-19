package dev.rens.services;

import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;
import dev.rens.exceptions.AccountNameUsedException;
import dev.rens.exceptions.InvalidUsernameOrPasswordException;
import dev.rens.exceptions.NotAbleToRemoveAccountException;
import dev.rens.exceptions.UsernameHasBeenTakenException;

public interface UserService {

	public Set<Account> ShowBankAccounts(User user);
	
	public Account makeBankAccount(String name, User user) throws AccountNameUsedException;
	
	//Check if balance is zero if not return false otherwise remove BankAccount
	public boolean deleteBankAccount(Account account) throws NotAbleToRemoveAccountException;
	
	public User login(String username, String password) throws InvalidUsernameOrPasswordException;
	
	public User createNewUser(String username, String password, boolean superUser) throws UsernameHasBeenTakenException;
	
}
