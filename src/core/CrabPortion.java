package core;

public class CrabPortion extends IngredientPortionImpl {
	
	private static final Ingredient CRAB = new Crab();
	
	public CrabPortion(double amount) {
		super(amount, CRAB);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(CRAB)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new CrabPortion(other.getAmount() + this.getAmount());
	}
	
}