package dev.rens.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;

public class AccountDAOlocalImpl implements AccountDAO{
	
	private static Map<String, Account> account_table = new HashMap<String, Account>();
	private static Integer idMaker = 0;

	public Account createAccount(Account account, User user) {
		account.setAccountID((++idMaker).toString());
		account.setUserID(user.getUserID());
		account_table.put(idMaker.toString(), account);
		return account;
	}

	public Account getAccount(String id) {
		Account a = account_table.get(id);
		return a;
	}

	public Set<Account> getAllAccountsFromUser(String userID) {
		Set<Account> allAccounts = new HashSet<Account>(account_table.values());
		Set<Account> myAccounts = new HashSet<Account>();
		
		for(Account a: allAccounts) {
			if(a.getUserID().equals(userID)) {
				myAccounts.add(a);
			}
		}
		return myAccounts;
	}

	public Account updateAccount(Account account) {
		account_table.put(account.getAccountID(), account);
		return account;
	}

	public boolean deleteAccount(String id) {
		if(account_table.remove(id) == null) {
			return false;
		}else {
			return true;
		}
	}

}
