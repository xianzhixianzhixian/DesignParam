package test9;

/**
 * 观察者模式
 * 观察被观察的对象是否变化，若没有变化则通知使用者
 * @author 钰丰
 */
public class Test9 {

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
