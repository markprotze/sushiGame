package sushigame.model;

import core.Plate;

public class PlatePlacedEvent extends PlateEvent {
	
	public PlatePlacedEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_PLACED, p, position);
	}
	
}