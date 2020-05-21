package sushigame.model;

import core.Plate;

public class PlateConsumedEvent extends PlateEvent {
	
	public PlateConsumedEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_CONSUMED, p, position);
	}
	
}