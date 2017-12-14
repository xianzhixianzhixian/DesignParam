package test10;

public class CashFactory {

	public static Strategy cashAccept(String type) {
		
		Strategy st=null;
		
		switch(type)
		{
		case "¼ÆËã»ú":
			st=new CashRebate(0.7);
			break;
		case "Ó¢Óï":
			st=new CashRebate(0.6);
			break;
		default:
			break;
		}
		
		return st;
	}
}
