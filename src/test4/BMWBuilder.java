package test4;

public class BMWBuilder extends CarBuilder{

	@Override
	void createWheel() {
		// TODO Auto-generated method stub
		System.out.println("BMW wheel");
	}

	@Override
	void createOilBox() {
		// TODO Auto-generated method stub
		System.out.println("BMW oilbox");
	}

	@Override
	void createBody() {
		// TODO Auto-generated method stub
		System.out.println("BMW body");
	}

}
