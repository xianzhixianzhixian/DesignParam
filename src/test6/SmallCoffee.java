package test6;

public class SmallCoffee extends Cup{

	@Override
	public void ApplyCup() {
		// TODO Auto-generated method stub
		i.add();
		System.out.println("SmallCoffee");
	}

	@Override
	public void setIngredient(Ingredient i) {
		// TODO Auto-generated method stub
		this.i=i;
	}
	
}
