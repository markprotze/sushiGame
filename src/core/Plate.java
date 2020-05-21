package core;

import sushigame.model.Chef;

public interface Plate {
	 public enum Color {RED, GREEN, BLUE, GOLD}
	 
     Sushi getContents();
     double getPrice();
     Plate.Color getColor();
     double getProfit();
     Chef getChef();
     public boolean getHasBeenCreatedOnDisplay();
     public void setHasBeenCreatedOnDisplay();
     
}