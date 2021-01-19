package dev.rens.daos;

import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;

public interface AccountDAO {

	Account createAccount(Account account, User user);
	
	Account getAccount(String id);
	
	Set<Account> getAllAccountsFromUser(String userID);
	
	Account updateAccount(Account account);
	
	boolean deleteAccount(String id);
}
