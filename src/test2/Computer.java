package test2;

public class Computer {
	
	public static OEM selectCreate(String computer)
	{
		OEM oem=null;
		switch(computer)
		{
		case "HPE":
			oem=new HPE();
			break;
		case "Acer":
			oem=new Acer();
			break;
		case "Lenovo":
			oem=new Lenovo();
			break;
		case "Dell":
			oem=new Dell();
			break;
		}
		return oem;
	}
}
