package core;

public class Nigiri implements Sushi {
	
	public enum NigiriType {TUNA, SALMON, EEL, CRAB, SHRIMP}
	
	private static double NIGIRI_PORTION_AMOUNT = 0.75;
	private static double RICE_PORTION_AMOUNT = 0.5;
	
	private IngredientPortion seafood;
	private IngredientPortion rice;
	private NigiriType typeOfNigiri;
	
	public Nigiri(NigiriType type) {
		rice = new RicePortion(RICE_PORTION_AMOUNT);
		
		switch(type) {
		case TUNA:
			seafood = new TunaPortion(NIGIRI_PORTION_AMOUNT);
			typeOfNigiri = NigiriType.TUNA;
			break;
		case SALMON:
			seafood = new SalmonPortion(NIGIRI_PORTION_AMOUNT);
			typeOfNigiri = NigiriType.SALMON;
			break;
		case EEL:
			seafood = new EelPortion(NIGIRI_PORTION_AMOUNT);
			typeOfNigiri = NigiriType.EEL;
			break;
		case CRAB:
			seafood = new CrabPortion(NIGIRI_PORTION_AMOUNT);
			typeOfNigiri = NigiriType.CRAB;
			break;
		case SHRIMP:
			seafood = new ShrimpPortion(NIGIRI_PORTION_AMOUNT);
			typeOfNigiri = NigiriType.SHRIMP;
			break;			
		}
	}
	
	public String getName() {
		return seafood.getName() + " nigiri";
	}
	
	public NigiriType getNigiriType() {
		return typeOfNigiri;
	}
	
	public IngredientPortion[] getIngredients() {
		return new IngredientPortion[] {seafood, rice};
	}
	
	public int getCalories() {
		return (int) (seafood.getCalories() + rice.getCalories() + 0.5);
	}
	
	public double getCost() {
		return ((int) ((seafood.getCost() + rice.getCost()) * 100.0 + 0.5)) / 100.0;
	}
	
	public boolean getHasRice() {
		return true;
	}
	
	public boolean getHasShellfish() {
		return seafood.getIsShellfish();
	}
	
	public boolean getIsVegetarian() {
		return false;
	}
	
}