/**
 * 抽象工厂模式
 */

package test3;

public class Main {

	public static void main(String []args)
	{
		IFactory factory=new KFCFactory();
		IUser iu=factory.createUser();
		iu.showUser();
		
		IDepartment id=factory.createDepartment(); 
		id.createHamburg();
		id.createCola();
	}
}
