/**
 * �۲���ģʽ
 */

package test9;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OnlineStore os=new OnlineStore();
		
		os.Attach(new Member(os,"����"));
		os.Attach(new Member(os,"С��"));
		os.Attach(new Member(os,"����"));
		
		os.setStoreState("������Ʒ���");
		os.Notify();
	}

}
