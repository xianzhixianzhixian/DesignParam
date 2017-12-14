package test9;

public class OnlineStore extends Informer {
	
	private String storeState;

	//具体被观察者状态
	public String getStoreState() {
		return storeState;
	}

	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

}
