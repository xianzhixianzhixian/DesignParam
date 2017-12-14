/**
 * 观察者模式
 */

package test9;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OnlineStore os=new OnlineStore();
		
		os.Attach(new Member(os,"大王"));
		os.Attach(new Member(os,"小王"));
		os.Attach(new Member(os,"对王"));
		
		os.setStoreState("海鲜商品添加");
		os.Notify();
	}

}
