package test9;

public class Member extends Observer {

	private String name;
	private String observerState;
	private OnlineStore store;
	
	public Member(OnlineStore store,String name){
		
		this.store=store;
		this.name=name;
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
		observerState=store.getStoreState();
		System.out.println("观察者的状态是	"+name+" "+observerState);
	}

	public OnlineStore getStore() {
		return store;
	}

	public void setStore(OnlineStore store) {
		this.store = store;
	}

}
