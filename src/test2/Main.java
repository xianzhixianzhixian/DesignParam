/**
 * 工厂模式
 */

package test2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OEM oem= Computer.selectCreate("HPE");
		oem.CreateComputer();
	}

}
