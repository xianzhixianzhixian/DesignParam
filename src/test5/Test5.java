package test5;

/**
 * 适配器模式
 * @author 钰丰
 */
public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//成功适配接口
		Drawer angle=new AngleAdapter();
		angle.Draw();
	}

}
