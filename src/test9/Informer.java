/**
 * 通知者
 */

package test9;

import java.util.List;
import java.util.ArrayList;

public abstract class Informer {
	
	private List<Observer> members=new ArrayList<Observer>();
	
	//增加观察者
	public void Attach(Observer member){
		
		members.add(member);
	}
	
	//移除观察者
	public void Detach(Observer member){
		
		members.remove(member);
	}
	
	//通知
	public void Notify(){
		
		Observer temp;
		for(int i=0;i<members.size();i++){
			
			temp=members.get(i);
			temp.Update();
		}
	}
}
