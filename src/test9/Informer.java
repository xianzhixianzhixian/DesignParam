/**
 * ֪ͨ��
 */

package test9;

import java.util.List;
import java.util.ArrayList;

public abstract class Informer {
	
	private List<Observer> members=new ArrayList<Observer>();
	
	//���ӹ۲���
	public void Attach(Observer member){
		
		members.add(member);
	}
	
	//�Ƴ��۲���
	public void Detach(Observer member){
		
		members.remove(member);
	}
	
	//֪ͨ
	public void Notify(){
		
		Observer temp;
		for(int i=0;i<members.size();i++){
			
			temp=members.get(i);
			temp.Update();
		}
	}
}
