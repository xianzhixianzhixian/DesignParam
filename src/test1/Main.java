/**
 * ����ģʽ
 */

package test1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String parameter1=AppConfig.GetParameterA();
		String parameter2=AppConfig.GetParameterA();
		
		if(parameter1==parameter2)
		{
			System.out.println("������������ͬ��ʵ��");
		}
		
		System.out.println(parameter1);
	}

}
