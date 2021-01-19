package dev.rens.exceptions;

public class InsufficientFundsException extends Exception{

	public InsufficientFundsException() {
		super("You do not have enough funds to make a withdrawl of that size");
	}
}
