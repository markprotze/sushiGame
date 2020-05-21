package sushigame.model;

import core.Plate;

public class RandomCustomer implements Customer {
	
	private double pickiness;
	
	public RandomCustomer(double pickiness) {
		this.pickiness = pickiness;
	}
	
	public boolean consumesPlate(Plate p) {
		return (Math.random() < pickiness);
	}
	
}