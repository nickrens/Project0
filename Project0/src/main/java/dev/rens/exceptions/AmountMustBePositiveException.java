package dev.rens.exceptions;

public class AmountMustBePositiveException extends Exception{

	public AmountMustBePositiveException() {
		super("The amount entered must be positive. \nNegative numbers will not be accepted");
	}
}
