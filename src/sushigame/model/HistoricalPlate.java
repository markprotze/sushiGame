package sushigame.model;

import core.Plate;

public interface HistoricalPlate extends Plate {
	
	boolean wasSpoiled();
	Customer getConsumer();
	
}