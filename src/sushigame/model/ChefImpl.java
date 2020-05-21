package sushigame.model;

import java.util.ArrayList;
import java.util.List;

import core.Plate;

public class ChefImpl implements Chef, BeltObserver {
	
	private double balance;
	private int foodConsumed;
	private int foodSpoiled;
	private List<HistoricalPlate> plate_history;
	private String name;
	private ChefsBelt belt;
	private boolean already_placed_this_rotation;
	
	public ChefImpl(String name, double starting_balance, ChefsBelt belt) {
		this.name = name;
		this.balance = starting_balance;
		this.belt = belt;
		belt.registerBeltObserver(this);
		already_placed_this_rotation = false;
		plate_history = new ArrayList<HistoricalPlate>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public HistoricalPlate[] getPlateHistory(int history_length) {
		if (history_length < 1 || (plate_history.size() == 0)) {
			return new HistoricalPlate[0];
		}
		
		if (history_length > plate_history.size()) {
			history_length = plate_history.size();
		}
		return plate_history.subList(plate_history.size()-history_length, plate_history.size()-1).toArray(new HistoricalPlate[history_length]);
	}
	
	public HistoricalPlate[] getPlateHistory() {
		return getPlateHistory(plate_history.size());
	}
	
	public double getBalance() {
		return balance;
	}
	
	public int getTotalFoodConsumed() {
		return foodConsumed;
	}
	
	public int getTotalFoodSpoiled() {
		return foodSpoiled;
	}
	
	public void makeAndPlacePlate(Plate plate, int position) throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException {
		if (already_placed_this_rotation) {
			throw new AlreadyPlacedThisRotationException();
		}
		
		if (plate.getContents().getCost() > balance) {
			throw new InsufficientBalanceException();
		}
		belt.setPlateNearestToPosition(plate, position);
		balance = balance - plate.getContents().getCost();
		already_placed_this_rotation = true;
	}
	
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.PLATE_CONSUMED) {
			Plate plate = ((PlateEvent) e).getPlate();
			if (plate.getChef() == this) {
				balance += plate.getPrice();
				foodConsumed++;
				Customer consumer = belt.getCustomerAtPosition(((PlateEvent) e).getPosition());
				plate_history.add(new HistoricalPlateImpl(plate, consumer));
			}
		} else if (e.getType() == BeltEvent.EventType.PLATE_SPOILED) {
			Plate plate = ((PlateEvent) e).getPlate();
			plate_history.add(new HistoricalPlateImpl(plate, null));
			if (plate.getChef() == this) {
				foodSpoiled++;
			}
		} else if (e.getType() == BeltEvent.EventType.ROTATE) {
			already_placed_this_rotation = false;
		}
	}
	
	public boolean alreadyPlacedThisRotation() {
		return already_placed_this_rotation;
	}
	
}