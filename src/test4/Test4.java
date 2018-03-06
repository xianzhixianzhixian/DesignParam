package test4;

/**
 * 建造者模式（生成器模式）
 * 和工厂模式还有抽象工厂模式不一样，建造者模式是把建造过程中的一系列步骤集合在了一个函数中（是步骤的集合），而工厂模式和抽象工厂模式是创建对象的
 * @author 钰丰
 */
public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CarDirector cb=new CarDirector(new BMWBuilder());
		cb.createCar();
		
		CarDirector cb1=new CarDirector(new BenZBuilder());
		cb1.createCar();
	}

}
