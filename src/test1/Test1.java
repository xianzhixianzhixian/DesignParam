package test1;

import javax.swing.*;
import java.awt.event.*;

/**
 * ���������ʵ��
 * @author �ڷ�
 */
public class Test1 extends JFrame implements ActionListener {
	
	//����ؼ�
	private static String text = "";
	JPanel jp=null;
	JButton jb=null;
	JTextField jtf=null;
	JTextArea jta=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args)
	{
		Test1 test = new Test1();
		byte source[] = { (byte) 0x80, 0x00, (byte) 0xfe, (byte) 0x85, 0x3a,
				0x5f }; // Դ��ַ ������ַ
		byte dest[] = { (byte) 0x80, 0x00, (byte) 0xff, 0x60, 0x2c, (byte) 0xdc }; // Ŀ�ĵ�ַ
	}
	
	public Test1()
	{
		jp=new JPanel();
		
		jb=new JButton("Encap");
		jb.addActionListener(this);
		jb.setActionCommand("Encap");
		
		jtf=new JTextField(150);
		jta=new JTextArea(15,30);
		jsp=new JScrollPane(jta);
		
		jp.setLayout(null);
		jtf.setBounds(10,10,300,30);
		jb.setBounds(330,10,100,30);
		jsp.setBounds(10, 50, 450, 300);
		
		jp.add(jb);
		jp.add(jtf);
		jp.add(jsp);
		
		this.add(jp);
		this.setSize(500,400);
		this.setTitle("test1");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static byte[] intToByte4(int sum)
	{
		byte[] arr = new byte[4];
		arr[0] = (byte) (sum >> 24);
		arr[1] = (byte) (sum >> 16);
		arr[2] = (byte) (sum >> 8);
		arr[3] = (byte) (sum & 0xff);
		return arr;
	}
	/**
	 * <pre>
	 * ������Ϊ4��8λbyte����ת��Ϊ32λint.
	 * </pre>
	 * 
	 * @param arr
	 * @return
	 */
	public static int byte4ToInt(byte[] arr)
	{
		if (arr == null || arr.length != 4)
		{
			throw new IllegalArgumentException("byte������벻Ϊ��,������4λ!");
		}
		return (int) (((arr[0] & 0xff) << 24) | ((arr[1] & 0xff) << 16)
				| ((arr[2] & 0xff) << 8) | ((arr[3] & 0xff)));
	}

	/**
	 * @param first
	 *            ����Դ��ַ��Ŀ�ĵ�ַ�������ݵĳ��� ����Ϊ14
	 * @param str
	 *            �����ݲ��֣�������1500���ֽڣ�
	 */
	private static byte[] getFrame(byte first[], String str)
	{
		byte frame[];
		byte cc[] = intToByte4(0);
		byte dat1[] = str.getBytes();
		frame = new byte[dat1.length + 18]; // ��ҪУ���byte����
		for (int i = 0; i < first.length; i++)
		{ // ����ײ�
			frame[i] = first[i];
		}
		for (int i = 14; i < dat1.length + 14; i++)
		{
			frame[i] = dat1[i - 14]; // ������ݲ���
		}
		// ���FCS ��װ��Ϊ0��У����Ϊ��Ӧ��FCS
		frame[dat1.length + 14 + 0] = cc[0];
		frame[dat1.length + 14 + 1] = cc[1];
		frame[dat1.length + 14 + 2] = cc[2];
		frame[dat1.length + 14 + 3] = cc[3];
		return frame;
	}

	public static byte [] GetFrame(byte source[],byte dest[],String str)
	{
		byte frame[] = null;
		boolean hasAnother = true;
		text += "ԭʼ����Ϊ��" + str;
		while (hasAnother)
		{
			text += "\n��ʼ��װ��֡\n";
			hasAnother = false;
			byte first[] = new byte[14];
			for (int i = 0; i < 6; i++)
			{
				first[i] = dest[i];
			}
			for (int i = 6; i < 12; i++)
			{
				first[i] = source[i - 6];
			}
			int size = str.length();

			String temp = str;
			if (str.length() < 46) // ����46���ֽڣ���ȫΪ46���ֽ�
			{
				hasAnother = false;
				for (int i = 0; i < (46 - size); i++)
				{
					str += '\u0000'; // �˴�����ֱ�Ӽ�����0 �����ַ���0 ���򶼻ὲ0 ǿ��ת��Ϊ�ַ�0, ��
										// ��ӵ���ACSII��48
				}
				temp = str;
			} else
			{
				if (size > 1500)
				{
					hasAnother = true;
					temp = str.substring(0, 1500);
					str = str.substring(1500, size);
				}
			}
			// ��䳤��
			first[12] = (byte) ((str.length() + 18) >> 8);
			first[13] = (byte) (str.length() + 18);
			text += "Դ  ��  ַ��" + GetHex(source);
			text += "\nĿ�ĵ�ַ��" + GetHex(dest);

			// int FCS = GetFCS(first, temp, 0);
			frame = getFrame(first, temp);
			int FCS = getFCS(frame);
			return frame;
		}
		return frame;
		 
	}

	/**
	 * 
	 * @param frame
	 *            ����֡���ݣ�����Դ��ַ��Ŀ�ĵ�ַ��֡���ȣ����� ��FCS ��Ϊ��װ FCSΪ0 ��ִ�����FSCΪ��Ӧ��FCS ������FCS
	 *            ��ΪУ�飬��FCSΪ��Ӧ��FCS ִ�������FCSΪ0 ������Ϊ�޲��
	 * @return
	 */
	public static int getFCS(byte frame[])
	{

		int size = frame.length;
		if(size<64)
			return -1;
		int CHECK = 0x04C11DB7; // ���ɶ���ʽ
		byte temp = 0;
		int CRC = 0;
		// frame[size - 4] = (byte) (FCS >> 24);
		// frame[size - 3] = (byte) (FCS >> 16);
		// frame[size - 2] = (byte) (FCS >> 8);
		// frame[size - 1] = (byte) (FCS);

		for (int i = 0; i < size; i++)
		{
			temp = frame[i];
			for (int j = 0x80; j != 0; j >>>= 1)
			{
				// System.out.println("temp  is "+toFullBinaryString(temp)+Integer.toHexString(temp));//����������
				// System.out.println("j     is "+toFullBinaryString(j)+Integer.toHexString(j));//����������
				// System.err.println("CRC   is "+toFullBinaryString(CRC)+Integer.toHexString(CRC));//����������
				if ((CRC & 0x80000000) == 0x80000000)// ��λΪ1
				{
					CRC <<= 1;
					if ((temp & j) == j) // ��������ݵ���λΪ1 ��CRC��ĩλ��1
					{
						CRC |= 0x00000001;
					}
					CRC = CRC ^ CHECK;
				} else
				{
					CRC <<= 1;
					if ((temp & j) == j) // ��������ݵ���λΪ1 ��CRC��ĩλ��1
					{
						CRC |= 0x00000001;
					}
				}
			}
		}
		frame[size - 4] = (byte) (CRC >> 24);
		frame[size - 3] = (byte) (CRC >> 16);
		frame[size - 2] = (byte) (CRC >> 8);
		frame[size - 1] = (byte) (CRC);

		// System.out.println(CRC);
		// System.err.println("CRC   is "+toFullBinaryString(CRC)+Integer.toHexString(CRC));//����������
		return CRC;

	}
	/**
	 * 
	 * @param str ����װ������
	 */
	public static void execute(String str)
	{

		byte pre[] = { (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
				(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xab };// ǰ�����֡ǰ�����
		byte source[] = { (byte) 0x80, 0x00, (byte) 0xfe, (byte) 0x85, 0x3a,
				0x5f }; // Դ��ַ
		byte dest[] = { (byte) 0x80, 0x00, (byte) 0xff, 0x60, 0x2c, (byte) 0xdc }; // Ŀ�ĵ�ַ
		boolean hasAnother = true;
		text += "ԭʼ����Ϊ��" + str;
		while (hasAnother)
		{
			text += "\r\n��ʼ��װ��֡\r\n";
			hasAnother = false;
			byte first[] = new byte[14];
			for (int i = 0; i < 6; i++)
			{
				first[i] = dest[i];
			}
			for (int i = 6; i < 12; i++)
			{
				first[i] = source[i - 6];
			}
			int size = str.length();

			String temp = str;
			if (str.length() < 46) // ����46���ֽڣ���ȫΪ46���ֽ�
			{
				hasAnother = false;
				for (int i = 0; i < (46 - size); i++)
				{
					str += '\u0000'; // �˴�����ֱ�Ӽ�����0 �����ַ���0 ���򶼻Ὣ0 ǿ��ת��Ϊ�ַ�0, ����ӵ���ACSII��48
				}
				temp = str;
			}else
			{
				if (size > 1500)
				{
					hasAnother = true;
					temp = str.substring(0, 1500);
					str = str.substring(1500, size);
				}
			}
			// ��䳤��
			first[12] = (byte) ((str.length() + 18) >> 8);
			first[13] = (byte) (str.length() + 18);
			text += "Դ  ��  ַ��" + GetHex(source);
			text += "\r\nĿ�ĵ�ַ��" + GetHex(dest);

//			int FCS = GetFCS(first, temp, 0);
			byte frame[] = getFrame(first, temp);
			int FCS = getFCS(frame);
			text += "\r\n��ǰ֡����Ϊ��" + temp;

			text += "\r\n֡��ԭʼ����Ϊ��" + getDataFromFrame(frame);
			text += "\r\nFCSΪ��" + Integer.toHexString(FCS);
			text += "\r\nFCSУ��֡�Ľ��Ϊ��" + getFCS(frame);
		}
	}

	public static String getDataFromFrame(byte frame[])
	{
		byte sr[] = new byte[frame.length - 18];// ֡�����ݲ���

		for (int i = 0; i < sr.length; i++)
		{
			sr[i] = frame[i + 14];
		}
		int index = 0;
		while (index < sr.length && sr[index] != 0)
		{
			index++;
		}
		String re = new String(sr);
		re = re.substring(0, index);
		return re;
	}

	/**
	 * ��6λ�ĵ�ַ�ֶ�ת��Ϊ�ַ��� ���������ʾ
	 * 
	 * @param s
	 * @return
	 */
	public static String GetHex(byte[] s)
	{
		String re = "";
		String temp = "";
		for (int i = 0; i < 6; i++)
		{
			if (i != 0)
			{
				re += "-";
			}
			temp = Integer.toHexString(s[i]);
			if (temp.length() > 2)
			{
				re += temp.substring(6, 8);
			} else if (temp.length() == 2)
			{
				re += temp;
			} else
			{
				re += "0" + temp;
			}
		}
		return re;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Encap")){
			String str=jtf.getText();
			execute(str);
			jta.append(text);
		}
	}
}
