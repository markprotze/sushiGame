package core;

public class Sashimi implements Sushi {
	
	public enum SashimiType {TUNA, SALMON, EEL, CRAB, SHRIMP}
	
	private static double SASHIMI_PORTION_AMOUNT = 0.75;
	private SashimiType typeOfSashimi;
	private IngredientPortion seafood;
	
	public Sashimi(SashimiType type) {
		switch(type) {
		case TUNA:
			seafood = new TunaPortion(SASHIMI_PORTION_AMOUNT);
			typeOfSashimi = SashimiType.TUNA;
			break;
		case SALMON:
			seafood = new SalmonPortion(SASHIMI_PORTION_AMOUNT);
			typeOfSashimi = SashimiType.SALMON;
			break;
		case EEL:
			seafood = new EelPortion(SASHIMI_PORTION_AMOUNT);
			typeOfSashimi = SashimiType.EEL;
			break;
		case CRAB:
			seafood = new CrabPortion(SASHIMI_PORTION_AMOUNT);
			typeOfSashimi = SashimiType.CRAB;
			break;
		case SHRIMP:
			seafood = new ShrimpPortion(SASHIMI_PORTION_AMOUNT);
			typeOfSashimi = SashimiType.SHRIMP;
			break;			
		}
	}
	
	public String getName() {
		return seafood.getName() + " sashimi";
	}
	
	public SashimiType getSashimiType() {
		return typeOfSashimi;
	}
	
	public IngredientPortion[] getIngredients() {
		return new IngredientPortion[] {seafood};
	}
	
	public int getCalories() {
		return (int) (seafood.getCalories() + 0.5);
	}
	
	public double getCost() {
		return ((int) (seafood.getCost() * 100.0 + 0.5)) / 100.0;
	}
	
	public boolean getHasRice() {
		return false;
	}
	
	public boolean getHasShellfish() {
		return seafood.getIsShellfish();
	}
	
	public boolean getIsVegetarian() {
		return false;
	}
	
}