package test1;

/**
 * ����ģʽ
 * @author �ڷ�
 */
public class AppConfig {
	
	private static String ParameterA=null;
	
	//˽�й��캯��
	private AppConfig()
	{
		
	}
	
	//���ParameterA
	public static String GetParameterA()
	{
		if(ParameterA==null)
		{
			ParameterA=new String("Hello world!");
		}
		
		return ParameterA;
	}
	
	//����ParameterA
	public static void SetParameterA(String Parameter)
	{
		ParameterA=Parameter;
	}
}
