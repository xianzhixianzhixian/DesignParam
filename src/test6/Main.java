/**
 * 桥接的模式  
 */

package test6;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cup cup=new JorumCoffee();
		cup.setIngredient(new Milk());
		cup.ApplyCup();
	}

}
