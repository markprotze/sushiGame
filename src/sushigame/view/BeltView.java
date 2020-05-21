package sushigame.view;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import javax.swing.JPanel;

import core.Plate;

@SuppressWarnings("serial")
public class BeltView extends JPanel implements BeltObserver {
	
	private Belt belt;
	private PlateDisplayWidget[] belt_DisplayPlates;
	
	public BeltView(Belt inputBelt) {
		belt = inputBelt;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(inputBelt.getSize(), 1));
		belt_DisplayPlates = new PlateDisplayWidget[inputBelt.getSize()];
		for (int i = 0; i < inputBelt.getSize(); i++) {
			PlateDisplayWidget individualPlateWidget = new PlateDisplayWidget(inputBelt);
			add(individualPlateWidget);
			belt_DisplayPlates[i] = individualPlateWidget;
		}
		refresh();
	}
	
	public void handleBeltEvent(BeltEvent beltEventChange) {	
		if ((beltEventChange.getType() == BeltEvent.EventType.PLATE_CONSUMED) || (beltEventChange.getType() == BeltEvent.EventType.PLATE_PLACED) || (beltEventChange.getType() == BeltEvent.EventType.PLATE_SPOILED)) {
			refresh();
		} else if (beltEventChange.getType() == BeltEvent.EventType.ROTATE) {
			rotatePlateDisplay();
			refresh();
		}
	}
	
	private void refresh() {
		for (int i=0; i < belt.getSize(); i++) {
			Plate possiblePlate = belt.getPlateAtPosition(i);
			PlateDisplayWidget individualPlateWidget = belt_DisplayPlates[i];
			individualPlateWidget.reRunLabelForAgeUpdate();
			
			if (!(possiblePlate == null) && !(possiblePlate.getHasBeenCreatedOnDisplay())) {
				try {
					individualPlateWidget.createActualPlateLabel(possiblePlate);
					possiblePlate.setHasBeenCreatedOnDisplay();
				} catch (MalformedURLException e) {
					System.err.println("Unable to create plate label due to error in sushi icon");
				}
			}
		}
	}
	
	private void rotatePlateDisplay() {
		PlateDisplayWidget[] belt_DisplayPlates_reDraw_Hold;
		belt_DisplayPlates_reDraw_Hold = new PlateDisplayWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			belt_DisplayPlates_reDraw_Hold[i] = belt_DisplayPlates[i];
		}
		for (int i = 0; i < belt.getSize(); i++) {
			remove(belt_DisplayPlates[i]);
			belt_DisplayPlates[i] = null;
		}
		add(belt_DisplayPlates_reDraw_Hold[belt.getSize() - 1]);
		belt_DisplayPlates[0] = belt_DisplayPlates_reDraw_Hold[belt.getSize() - 1];
		for (int i = 0; i < belt.getSize() - 1; i++) {
			add(belt_DisplayPlates_reDraw_Hold[i]);
			belt_DisplayPlates[i + 1] = belt_DisplayPlates_reDraw_Hold[i];
		}
	}
	
}