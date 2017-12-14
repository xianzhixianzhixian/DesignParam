/**
 * 建造者模式（生成器模式）
 */

package test4;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CarDirector cb=new CarDirector(new BMWBuilder());
		cb.createCar();
		
		CarDirector cb1=new CarDirector(new BenZBuilder());
		cb1.createCar();
	}

}
