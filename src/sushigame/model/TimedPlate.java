package sushigame.model;

import core.Plate;

public interface TimedPlate extends Plate {
	
	int getInceptDate();
	Plate getOriginal();
	
}