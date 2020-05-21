package sushigame.model;

import core.Plate;

public interface Chef {
	
	String getName();
	void setName(String name);
	
	void makeAndPlacePlate(Plate plate, int position) throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException;
	
	HistoricalPlate[] getPlateHistory(int max_history_length);
	HistoricalPlate[] getPlateHistory();
	
	double getBalance();
	int getTotalFoodConsumed();
	int getTotalFoodSpoiled();
	
	boolean alreadyPlacedThisRotation();
	
}