package dev.rens.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import dev.rens.daos.AccountDAO;
import dev.rens.daos.AccountDAOImpl;
import dev.rens.entities.Account;
import dev.rens.exceptions.AmountMustBePositiveException;
import dev.rens.exceptions.InsufficientFundsException;

public class AccountServiceImpl implements AccountService{
	
	private static AccountDAO adao = new AccountDAOImpl();

	public Account depositToAccount(Account account, double amount) throws AmountMustBePositiveException {
		if(amount < 0) {
			throw new AmountMustBePositiveException();
		}
		account.setBalance(account.getBalance() + roundAmount(amount));
		adao.updateAccount(account);
		return account;
	}

	public Account withdrawFromAccount(Account account, double amount) throws InsufficientFundsException, AmountMustBePositiveException {
		if(amount < 0) {
			throw new AmountMustBePositiveException();
		}
		if(account.getBalance() < amount) {
			throw new InsufficientFundsException();
		} else {
			account.setBalance(account.getBalance() - roundAmount(amount));
			adao.updateAccount(account);
			return account;
		}
	}

	public String viewBalance(Account account) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return "$" + df.format(account.getBalance());
	}
	
	private double roundAmount(double amount) {
		double newAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.DOWN).doubleValue();
		return newAmount;
	}

}
