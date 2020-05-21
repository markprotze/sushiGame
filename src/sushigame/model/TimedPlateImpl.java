package sushigame.model;

import core.Plate;
import core.Sushi;

public class TimedPlateImpl implements TimedPlate {
	
	private Plate original;
	private int incept_date;
	
	public TimedPlateImpl(Plate plate, int rotation_count) {
		original = plate;
		incept_date = rotation_count;
	}
	
	public Sushi getContents() {
		return original.getContents();
	}
	
	public double getPrice() {
		return original.getPrice();
	}
	
	public Color getColor() {
		return original.getColor();
	}
	
	public double getProfit() {
		return original.getProfit();
	}
	
	public int getInceptDate() {
		return incept_date;
	}
	
	public Plate getOriginal() {
		return original;
	}
	
	public Chef getChef() {
		return original.getChef();
	}
	
	public boolean getHasBeenCreatedOnDisplay() {
		return original.getHasBeenCreatedOnDisplay();
	}
	
	public void setHasBeenCreatedOnDisplay() {
		original.setHasBeenCreatedOnDisplay();
	}
	
}