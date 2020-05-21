package core;

public class ShrimpPortion extends IngredientPortionImpl {
	
	private static final Ingredient SHRIMP = new Shrimp();
	
	public ShrimpPortion(double amount) {
		super(amount, SHRIMP);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(SHRIMP)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new ShrimpPortion(other.getAmount() + this.getAmount());
	}
	
}