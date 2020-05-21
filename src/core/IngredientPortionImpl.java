package core;

abstract public class IngredientPortionImpl implements IngredientPortion {
	
	private double amount;
	private Ingredient ingredient;
	
	protected IngredientPortionImpl(double amount, Ingredient ingredient) {
		if (amount <= 0.0) {
			throw new RuntimeException("Amount of ingredient portion must be greater than 0.0");
		}
		
		this.amount = amount;
		this.ingredient = ingredient;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public String getName() {
		return ingredient.getName();
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getCalories() {
		return amount * ingredient.getCaloriesPerOunce();
	}
	
	public double getCost() {
		return amount * ingredient.getPricePerOunce();
	}
	
	public boolean getIsVegetarian() {
		return ingredient.getIsVegetarian();
	}
	
	public boolean getIsRice() {
		return ingredient.getIsRice();
	}
	
	public boolean getIsShellfish() {
		return ingredient.getIsShellfish();
	}
	
	abstract public IngredientPortion combine(IngredientPortion other);
	
}