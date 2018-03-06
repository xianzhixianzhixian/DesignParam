package test7;

/**
 * 装饰模式
 * @author 钰丰
 */
public class Test7 {

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
