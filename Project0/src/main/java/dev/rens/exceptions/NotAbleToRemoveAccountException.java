package dev.rens.exceptions;

public class NotAbleToRemoveAccountException extends Exception{

	public NotAbleToRemoveAccountException() {
		super("The balance of an account must be zero to delete");
	}
}
