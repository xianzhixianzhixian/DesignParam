package test4;

/**
 * ������ģʽ��������ģʽ��
 * �͹���ģʽ���г��󹤳�ģʽ��һ����������ģʽ�ǰѽ�������е�һϵ�в��輯������һ�������У��ǲ���ļ��ϣ���������ģʽ�ͳ��󹤳�ģʽ�Ǵ��������
 * @author �ڷ�
 */
public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CarDirector cb=new CarDirector(new BMWBuilder());
		cb.createCar();
		
		CarDirector cb1=new CarDirector(new BenZBuilder());
		cb1.createCar();
	}

}
