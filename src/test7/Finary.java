/**
 * Decorator
 */

package test7;

public class Finary extends Person {

	protected Person component;
	
	//×°°ç
	public void Decorate(Person component){
		
		this.component=component;
	}
	
	public void show(){
		
		if(component!=null){
			
			component.show();
		}
	}
}
