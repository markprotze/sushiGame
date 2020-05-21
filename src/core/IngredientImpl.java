package core;

abstract public class IngredientImpl implements Ingredient {
	
	private String name;
	private double price;
	private int calories;
	private boolean is_vegetarian;
	private boolean is_rice;
	private boolean is_shellfish;
	
	protected IngredientImpl(String name, double price, int calories, boolean is_vegetarian, boolean is_rice, boolean is_shellfish) {
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.is_vegetarian = is_vegetarian;
		this.is_rice = is_rice;
		this.is_shellfish = is_shellfish;		
	}
	
	public String getName() {
		return name;
	}
	
	public double getCaloriesPerDollar() {
		return calories/price;
	}
	
	public int getCaloriesPerOunce() {
		return calories;
	}
	
	public double getPricePerOunce() {
		return price;
	}
	
	public boolean equals(Ingredient other) {
		if (other == null) {
			return false;
		}
		return ((other == this) ||
				(other.getName().equals(getName()) &&
				 (other.getCaloriesPerOunce() == getCaloriesPerOunce()) &&
				 (Math.abs(other.getPricePerOunce()-getPricePerOunce()) < 0.01) &&
				 (other.getIsVegetarian() == getIsVegetarian()) &&
				 (other.getIsRice() == getIsRice()) &&
				 (other.getIsShellfish() == getIsShellfish())));
	}
	
	public boolean getIsVegetarian() {
		return is_vegetarian;
	}
	
	public boolean getIsRice() {
		return is_rice;
	}
	
	public boolean getIsShellfish() {
		return is_shellfish;
	}
	
}