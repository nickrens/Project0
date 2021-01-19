package dev.rens.exceptions;

public class InvalidUsernameOrPasswordException extends Exception{

	public InvalidUsernameOrPasswordException() {
		super("The username or password you entered is not valid");
	}
}
