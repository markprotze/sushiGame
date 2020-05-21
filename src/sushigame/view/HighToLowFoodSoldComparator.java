package sushigame.view;

import sushigame.model.Chef;
import java.util.Comparator;

public class HighToLowFoodSoldComparator implements Comparator<Chef> {

	public int compare(Chef chefA, Chef chefB) {
		return (chefB.getTotalFoodConsumed() - chefA.getTotalFoodConsumed());
	}
	
}