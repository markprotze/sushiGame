package sushigame.model;

import core.Plate;
import core.Sushi;

public class HistoricalPlateImpl implements HistoricalPlate {
	
	private Customer consumer;
	private Plate plate;
	
	public HistoricalPlateImpl(Plate p, Customer c) {
		plate = p;
		consumer = c;
	}
	
	public Sushi getContents() {
		return plate.getContents();
	}
	
	public double getPrice() {
		return plate.getPrice();
	}
	
	public Color getColor() {
		return plate.getColor();
	}
	
	public double getProfit() {
		return plate.getProfit();
	}
	
	public Chef getChef() {
		return plate.getChef();
	}
	
	public boolean wasSpoiled() {
		return (consumer == null);
	}
	
	public Customer getConsumer() {
		return consumer;
	}
	
	public boolean getHasBeenCreatedOnDisplay() {
		return plate.getHasBeenCreatedOnDisplay();
	}
	
	public void setHasBeenCreatedOnDisplay() {
		plate.setHasBeenCreatedOnDisplay();
	}
	
}