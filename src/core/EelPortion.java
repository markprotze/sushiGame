package core;

public class EelPortion extends IngredientPortionImpl {
	
	private static final Ingredient EEL = new Eel();
	
	public EelPortion(double amount) {
		super(amount, EEL);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(EEL)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new EelPortion(other.getAmount() + this.getAmount());
	}
	
}