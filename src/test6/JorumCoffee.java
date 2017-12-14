package test6;

public class JorumCoffee extends Cup{

	@Override
	public void ApplyCup() {
		// TODO Auto-generated method stub
		i.add();
		System.out.println("JorumCoffee");
	}

	@Override
	public void setIngredient(Ingredient i) {
		// TODO Auto-generated method stub
		this.i=i;
	}

}
