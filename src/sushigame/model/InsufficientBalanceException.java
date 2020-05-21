package sushigame.model;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {
	
	public InsufficientBalanceException() {
		super("Insufficient balance");
	}
	
}