package core;

import sushigame.model.Chef;

abstract public class PlateImpl implements Plate {
	
	private Sushi contents;
	private double price;
	private Plate.Color color;
	private Chef chef;
	private boolean hasBeenCreatedOnDisplay;
	
	public PlateImpl(Chef chef, Sushi s, double price, Plate.Color color) throws PlatePriceException {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		if (chef == null) {
			throw new IllegalArgumentException();
		}
		
		if (s.getCost() > price) {
			throw new PlatePriceException(this, s);
		}
		
		this.price = price;
		this.color = color;
		this.chef = chef;
		contents = s;
	}
	
	public void setHasBeenCreatedOnDisplay() {
		hasBeenCreatedOnDisplay = true;
	}
	
	public boolean getHasBeenCreatedOnDisplay() {
		return hasBeenCreatedOnDisplay;
	}
	
	public Sushi getContents() {
		return contents;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Color getColor() {
		return color;
	}
	
	public double getProfit() {
		return getPrice() - contents.getCost();
	}
	
	public Chef getChef() {
		return chef;
	}
	
}