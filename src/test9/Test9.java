package test9;

/**
 * �۲���ģʽ
 * �۲챻�۲�Ķ����Ƿ�仯����û�б仯��֪ͨʹ����
 * @author �ڷ�
 */
public class Test9 {

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
