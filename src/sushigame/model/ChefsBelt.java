package sushigame.model;

import core.Plate;

interface ChefsBelt extends Belt {
	
	int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException;
	
}