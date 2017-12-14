/**
 * 外观类
 */

package test8;

public class Facade {
	
	private Camera camera;
	private Alarm alarm;
	private Light light;
	private Sensor sensor;
	
	public Facade(){
		
		camera=new Camera();
		alarm=new Alarm();
		light=new Light();
		sensor=new Sensor();
	}
	
	public void MethonA(){
		
		System.out.println("方法一");
		camera.showCamera();
		light.showLight();
	}
	
	public void MethodB(){
		
		System.out.println();
		System.out.println("方法二");
		camera.showCamera();
		sensor.showSensor();
		light.showLight();
		alarm.showAlarm();
	}
}
