/**
 * ÷∏ª”’ﬂ¿‡
 */

package test4;

public class CarDirector {
	
	CarBuilder cb;
	
	public CarDirector(CarBuilder cb)
	{
		this.cb=cb;
	}
	
	public void createCar()
	{
		cb.createWheel();
		cb.createOilBox();
		cb.createBody();
	}
}
