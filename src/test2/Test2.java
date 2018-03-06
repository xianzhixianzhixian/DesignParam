package test2;

/**
 * 工厂模式
 * @author 钰丰
 */
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OEM oem= Computer.selectCreate("HPE");
		oem.CreateComputer();
	}

}
