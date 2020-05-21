package sushigame.model;

@SuppressWarnings("serial")
public class BeltFullException extends Exception {
	
	private Belt belt;
	
	public BeltFullException(Belt belt) {
		this.belt = belt;
	}
	
	public Belt getBelt() {
		return belt;
	}
	
}