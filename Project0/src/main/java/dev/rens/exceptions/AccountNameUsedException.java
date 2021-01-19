package dev.rens.exceptions;

public class AccountNameUsedException extends Exception{

	public AccountNameUsedException() {
		super("You allready have an account with name. \nPlease try again with a name you have not used");
	}
}
