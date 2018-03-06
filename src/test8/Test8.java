package test8;

/**
 * 外观模式
 * 把组成外观的所有部件集合在一个函数里展示出来
 * @author 钰丰
 */
public class Test8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade facade=new Facade();
		
		facade.MethonA();
		facade.MethodB();
	}

}
