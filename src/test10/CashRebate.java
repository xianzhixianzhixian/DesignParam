package test10;

public class CashRebate extends Strategy {

	//������
	private double moneyRebate=0.8;

	public CashRebate(double rebate){
		
		this.moneyRebate=rebate;
	}
	
	//���ۺ���
	@Override
	public double acceptCash(double money) {
		// TODO Auto-generated method stub
		return money*moneyRebate;
	}

}
