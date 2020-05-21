package sushigame.view;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.PlateConsumedEvent;
import sushigame.model.PlateSpoiledEvent;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import core.IngredientPortion;
import core.Nigiri;
import core.Plate;
import core.Roll;
import core.Sashimi;
import core.Sushi;
import core.Nigiri.NigiriType;
import core.Plate.Color;
import core.Sashimi.SashimiType;

@SuppressWarnings("serial")
public class PlateDisplayWidget extends JLabel implements BeltObserver {
	
	private Plate encapsulatedSuperPlate;
	private BeltEvent beltEventPasser;
	private String plateColor;
	private String sushiType;
	private String ifSashimi_type = "";
	private String ifNigiri_type = "";
	private String ifRoll_name;
	private String[] ifRoll_Ingredients;
	private double[] ifRoll_IngredientAmounts;
	private String ifRoll_IngredientString;
	private boolean isSashimiOrNigiri;
	private boolean isRoll;
	private String chefName;
	private int plateAge;
	private String plateAgeString;
	private int rotationsSincePlateCreation;
	
	public PlateDisplayWidget(Belt inputBelt) {
		
		inputBelt.registerBeltObserver(this);
		
		this.setPreferredSize(new Dimension(800, 35));
		this.setOpaque(true);
		this.setBackground(java.awt.Color.decode("#AFEEEE"));
	}
	
	public void createActualPlateLabel(Plate inputPlate) throws MalformedURLException {
		
		runSushiAnalysis(inputPlate);
		encapsulatedSuperPlate = inputPlate;
		
        URL imgurLink = new URL("http://i.imgur.com/KzZoHKd.png");
        ImageIcon sushiIcon = new ImageIcon(imgurLink);
		this.setBackground(java.awt.Color.decode("#F5DEB3"));
		this.setIcon(sushiIcon);
		this.setVerticalTextPosition(JLabel.CENTER);
		this.setHorizontalTextPosition(JLabel.TRAILING);
		this.setHorizontalAlignment(CENTER);
		Border plateLabelBorder = BorderFactory.createLineBorder(java.awt.Color.decode("#CD5C5C"), 1);
		this.setBorder(plateLabelBorder);
		
		rotationsSincePlateCreation = 0;
		updatePlateAge();
		
		if (isSashimiOrNigiri) {
			this.setText(ifSashimi_type + ifNigiri_type + " " + sushiType + " on a " + plateColor + " plate made by " + chefName + " " + plateAgeString + ".");
		} else if (isRoll) {
			this.setText("<html>" + ifRoll_name + " on a " + plateColor + " plate made by " + chefName + " " + plateAgeString + ". " + ifRoll_IngredientString + "</html>");
		}
	}
	
	private void runSushiAnalysis(Plate inputDisplayPlate) {
		
		Color plateColorInEnum = inputDisplayPlate.getColor();
		switch (plateColorInEnum) {
			case RED: plateColor = "red";
			break;
			case GREEN: plateColor = "green";
			break;
			case BLUE: plateColor = "blue";
			break;
			case GOLD: plateColor = "gold";
		}
		
		Sushi plateSushiUnknown = inputDisplayPlate.getContents();
		if (plateSushiUnknown instanceof Sashimi) {
			sushiType = "sashimi";
			isSashimiOrNigiri = true;
		}
		if (plateSushiUnknown instanceof Nigiri) {
			sushiType = "nigiri";
			isSashimiOrNigiri = true;
		}
		if (plateSushiUnknown instanceof Roll) {
			sushiType = "roll";
			isRoll = true;
		}
		
		if (plateSushiUnknown instanceof Sashimi) {
			ifNigiri_type = "";
			Sashimi plateSashimiTypeUnknownCasted = (Sashimi) plateSushiUnknown;
			SashimiType plateSashimiTypeEnum = plateSashimiTypeUnknownCasted.getSashimiType();
			switch (plateSashimiTypeEnum) {
				case TUNA: ifSashimi_type = "Tuna";
				break;
				case SALMON: ifSashimi_type = "Salmon";
				break;
				case EEL: ifSashimi_type = "Eel";
				break;
				case CRAB: ifSashimi_type = "Crab";
				break;
				case SHRIMP: ifSashimi_type = "Shrimp";
				break;
			}
		}
		
		if (plateSushiUnknown instanceof Nigiri) {
			ifSashimi_type = "";
			Nigiri plateNigiriTypeUnknownCasted = (Nigiri) plateSushiUnknown;
			NigiriType plateNigiriTypeEnum = plateNigiriTypeUnknownCasted.getNigiriType();
			switch (plateNigiriTypeEnum) {
				case TUNA: ifNigiri_type = "Tuna";
				break;
				case SALMON: ifNigiri_type = "Salmon";
				break;
				case EEL: ifNigiri_type = "Eel";
				break;
				case CRAB: ifNigiri_type = "Crab";
				break;
				case SHRIMP: ifNigiri_type = "Shrimp";
				break;
			}
		}
		
		if (plateSushiUnknown instanceof Roll) {
			Roll plateRollCasted = (Roll) plateSushiUnknown;
			ifRoll_name = plateRollCasted.getName();
			IngredientPortion[] tempIngredientPortionArray = plateRollCasted.getIngredients();
			ifRoll_Ingredients = new String[tempIngredientPortionArray.length];
			ifRoll_IngredientAmounts = new double[tempIngredientPortionArray.length];
			for (int i = 0; i < tempIngredientPortionArray.length; i++) {
				ifRoll_Ingredients[i] = tempIngredientPortionArray[i].getIngredient().getName();
				ifRoll_IngredientAmounts[i] = (((int) (tempIngredientPortionArray[i].getAmount() * 100.0 + 0.5)) / 100.0);
			}
			ifRoll_IngredientString = twoArraysToLongString(ifRoll_IngredientAmounts, ifRoll_Ingredients);
		}
		
		chefName = inputDisplayPlate.getChef().getName();
		plateAge = 0;
		plateAgeString = "just now";
		
	}
	
	public void reRunLabelForAgeUpdate() {
		if (isSashimiOrNigiri) {
			this.setText(ifSashimi_type + ifNigiri_type + " " + sushiType + " on a " + plateColor + " plate made by " + chefName + " " + plateAgeString + ".");
		} else if (isRoll) {
			this.setText("<html>" + ifRoll_name + " on a " + plateColor + " plate made by " + chefName + " " + plateAgeString + ". " + ifRoll_IngredientString + "</html>");
		}
	}
	
	public void handleBeltEvent(BeltEvent beltEventChange) {
		if (beltEventChange.getType() == BeltEvent.EventType.ROTATE) {
			rotationsSincePlateCreation++;
			updatePlateAge();
		}
		if (beltEventChange.getType() == BeltEvent.EventType.PLATE_CONSUMED) {
			beltEventPasser = beltEventChange;
			checkIfPlateNeedsToBeRemovedFromConsumption();
		}
		if (beltEventChange.getType() == BeltEvent.EventType.PLATE_SPOILED) {
			beltEventPasser = beltEventChange;
			checkIfPlateNeedsToBeRemovedFromSpoiling();
		}
	}
	
	private String twoArraysToLongString(double[] ingredientAmountsArray, String[] ingredientNamesArray) {
		String ingredientString = "Comprised of ";
		if (ingredientNamesArray.length == 1) {
			ingredientString = ingredientString + ingredientAmountsArray[0] + "oz of " + ingredientNamesArray[0] + ".";
		} else if (ingredientNamesArray.length == 2) {
			ingredientString = ingredientString + ingredientAmountsArray[0] + "oz of " + ingredientNamesArray[0] + " and " + ingredientAmountsArray[1] + "oz of " + ingredientNamesArray[1] + ".";
		} else {
			for (int i = 0; i < ingredientNamesArray.length - 1; i++) {
				ingredientString = ingredientString + ingredientAmountsArray[i] + "oz of " + ingredientNamesArray[i] + ", ";
			}
			ingredientString = ingredientString + "and " + ingredientAmountsArray[ingredientAmountsArray.length - 1] + "oz of " + ingredientNamesArray[ingredientNamesArray.length - 1] + ".";
		}
		return ingredientString;
	}
	
	private void updatePlateAgeString() {
		if (plateAge == 0) {
			plateAgeString = "just now";
		} else if (plateAge == 1) {
			plateAgeString = "last rotation";
		} else {
			plateAgeString = plateAge + " rotations ago";
		}
	}
	
	private void updatePlateAge() {
		plateAge = rotationsSincePlateCreation;
		updatePlateAgeString();
	}
	
	private void selfDestruct() {
		this.setIcon(null);
		this.setText(null);
		this.setBackground(java.awt.Color.decode("#AFEEEE"));
		this.setBorder(null);
		rotationsSincePlateCreation = 0;
		plateAge = 0;
		plateAgeString = null;
		encapsulatedSuperPlate = null;
		beltEventPasser = null;
		plateColor = null;
		sushiType = null;
		ifSashimi_type = "";
		ifNigiri_type = "";
		ifRoll_name = null;
		ifRoll_Ingredients = null;
		ifRoll_IngredientAmounts = null;
		ifRoll_IngredientString = null;
		isSashimiOrNigiri = false;
		isRoll = false;
		chefName = null;
		
	}
	
	private void checkIfPlateNeedsToBeRemovedFromConsumption() {
		PlateConsumedEvent ConsumptionEvent = (PlateConsumedEvent) beltEventPasser;
		Plate plateToBeCheckedAgainst = ConsumptionEvent.getPlate();
		if (plateToBeCheckedAgainst.equals(encapsulatedSuperPlate)) {
			selfDestruct();
		}
	}
	
	public void checkIfPlateNeedsToBeRemovedFromSpoiling() {
		PlateSpoiledEvent SpoilageEvent = (PlateSpoiledEvent) beltEventPasser;
		Plate plateToBeCheckedAgainst = SpoilageEvent.getPlate();
		if (plateToBeCheckedAgainst.equals(encapsulatedSuperPlate)) {
			selfDestruct();
		}
	}
	
}