package test10;

/**
 * 策略模式
 * 不同种类使用不同策略，有多种策略针对不同事物
 * @author 钰丰
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double totalPrice=0;
		String computer="计算机";
		String english="英语";
		
		//计算机收费
		Strategy stc=CashFactory.cashAccept(computer);
		totalPrice+=stc.acceptCash(100);
		System.out.println("计算机书籍 总费用"+totalPrice);
		
		totalPrice=0;
		
		//英语收费
		Strategy ste=CashFactory.cashAccept(english);
		totalPrice+=ste.acceptCash(100);
		System.out.println("英语书籍 总费用"+totalPrice);
	}

}
