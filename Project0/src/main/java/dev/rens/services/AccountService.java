package dev.rens.services;

import dev.rens.entities.Account;
import dev.rens.exceptions.AmountMustBePositiveException;
import dev.rens.exceptions.InsufficientFundsException;

public interface AccountService {
	
	public String viewBalance(Account account);
	
	public Account depositToAccount(Account account, double amount) throws AmountMustBePositiveException;
	
	public Account withdrawFromAccount(Account account, double amount) throws InsufficientFundsException, AmountMustBePositiveException;
	
	
}
