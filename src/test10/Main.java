package test10;

/**
 * ����ģʽ
 * ��ͬ����ʹ�ò�ͬ���ԣ��ж��ֲ�����Բ�ͬ����
 * @author �ڷ�
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double totalPrice=0;
		String computer="�����";
		String english="Ӣ��";
		
		//������շ�
		Strategy stc=CashFactory.cashAccept(computer);
		totalPrice+=stc.acceptCash(100);
		System.out.println("������鼮 �ܷ���"+totalPrice);
		
		totalPrice=0;
		
		//Ӣ���շ�
		Strategy ste=CashFactory.cashAccept(english);
		totalPrice+=ste.acceptCash(100);
		System.out.println("Ӣ���鼮 �ܷ���"+totalPrice);
	}

}
