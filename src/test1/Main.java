/**
 * 单例模式
 */

package test1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String parameter1=AppConfig.GetParameterA();
		String parameter2=AppConfig.GetParameterA();
		
		if(parameter1==parameter2)
		{
			System.out.println("两个对象是相同的实例");
		}
		
		System.out.println(parameter1);
	}

}
