package test7;

/**
 * װ��ģʽ
 * @author �ڷ�
 */
public class Test7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person xyy=new Person("ϲ����");
		
		System.out.println("��ʼװ��ϲ����");
		
		GreenApple ga=new GreenApple();
		RedApple ra=new RedApple();
		GoldApple golda=new GoldApple();
		
		ga.Decorate(xyy);
		ra.Decorate(ga);
		golda.Decorate(ra);
		
		golda.show();
	}

}
