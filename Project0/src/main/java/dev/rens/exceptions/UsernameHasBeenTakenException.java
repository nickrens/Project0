package dev.rens.exceptions;

public class UsernameHasBeenTakenException extends Exception{

	public UsernameHasBeenTakenException() {
		super("The username you entered has already been selected by someone else please try another username");
	}
}
