/**
 * װ��ģʽ
 */

package test7;

public class Main {

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
