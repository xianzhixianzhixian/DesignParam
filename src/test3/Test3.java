package test3;

/**
 * 抽象工厂模式
 * @author 钰丰
 */
public class Test3 {

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
