package test10;

public class CashRebate extends Strategy {

	//打折率
	private double moneyRebate=0.8;

	public CashRebate(double rebate){
		
		this.moneyRebate=rebate;
	}
	
	//打折函数
	@Override
	public double acceptCash(double money) {
		// TODO Auto-generated method stub
		return money*moneyRebate;
	}

}
