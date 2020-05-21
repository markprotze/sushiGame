package sushigame.view;

import sushigame.model.Chef;
import java.util.Comparator;

public class LowToHighFoodSpoiledComparator implements Comparator<Chef> {

	public int compare(Chef chefA, Chef chefB) {
		return (int) (chefA.getTotalFoodSpoiled()) - Math.round(chefB.getTotalFoodSpoiled());
	}
	
}