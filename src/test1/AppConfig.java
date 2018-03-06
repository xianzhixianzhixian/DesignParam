package test1;

/**
 * 单例模式
 * @author 钰丰
 */
public class AppConfig {
	
	private static String ParameterA=null;
	
	//私有构造函数
	private AppConfig()
	{
		
	}
	
	//获得ParameterA
	public static String GetParameterA()
	{
		if(ParameterA==null)
		{
			ParameterA=new String("Hello world!");
		}
		
		return ParameterA;
	}
	
	//设置ParameterA
	public static void SetParameterA(String Parameter)
	{
		ParameterA=Parameter;
	}
}
