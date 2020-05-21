package sushigame.view;

import sushigame.model.Chef;
import java.util.Comparator;

public class HighToLowBalanceComparator implements Comparator<Chef> {
	
	public int compare(Chef chefA, Chef chefB) {
		// We do b - a because we want largest to smallest
		return (int) (Math.round(chefB.getBalance() * 100.0) - Math.round(chefA.getBalance() * 100));
	}
	
}