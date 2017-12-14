/**
 * 装饰模式
 */

package test7;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person xyy=new Person("喜羊羊");
		
		System.out.println("开始装扮喜羊羊");
		
		GreenApple ga=new GreenApple();
		RedApple ra=new RedApple();
		GoldApple golda=new GoldApple();
		
		ga.Decorate(xyy);
		ra.Decorate(ga);
		golda.Decorate(ra);
		
		golda.show();
	}

}
