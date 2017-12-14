/**
 * 适配器模式
 */

package test5;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//成功适配接口
		Drawer angle=new AngleAdapter();
		angle.Draw();
	}

}
