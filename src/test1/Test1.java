package test1;

import javax.swing.*;
import java.awt.event.*;

/**
 * 计算机网络实验
 * @author 钰丰
 */
public class Test1 extends JFrame implements ActionListener {
	
	//定义控件
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
				0x5f }; // 源地址 本机地址
		byte dest[] = { (byte) 0x80, 0x00, (byte) 0xff, 0x60, 0x2c, (byte) 0xdc }; // 目的地址
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
	 * 将长度为4的8位byte数组转换为32位int.
	 * </pre>
	 * 
	 * @param arr
	 * @return
	 */
	public static int byte4ToInt(byte[] arr)
	{
		if (arr == null || arr.length != 4)
		{
			throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
		}
		return (int) (((arr[0] & 0xff) << 24) | ((arr[1] & 0xff) << 16)
				| ((arr[2] & 0xff) << 8) | ((arr[3] & 0xff)));
	}

	/**
	 * @param first
	 *            包含源地址，目的地址，和数据的长度 长度为14
	 * @param str
	 *            纯数据部分（不超过1500个字节）
	 */
	private static byte[] getFrame(byte first[], String str)
	{
		byte frame[];
		byte cc[] = intToByte4(0);
		byte dat1[] = str.getBytes();
		frame = new byte[dat1.length + 18]; // 需要校验的byte数组
		for (int i = 0; i < first.length; i++)
		{ // 填充首部
			frame[i] = first[i];
		}
		for (int i = 14; i < dat1.length + 14; i++)
		{
			frame[i] = dat1[i - 14]; // 填充数据部分
		}
		// 填充FCS 封装则为0，校验则为对应的FCS
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
		text += "原始数据为：" + str;
		while (hasAnother)
		{
			text += "\n开始封装成帧\n";
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
			if (str.length() < 46) // 不足46个字节，补全为46个字节
			{
				hasAnother = false;
				for (int i = 0; i < (46 - size); i++)
				{
					str += '\u0000'; // 此处不可直接加数字0 或者字符串0 否则都会讲0 强制转换为字符0, 即
										// 添加的是ACSII—48
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
			// 填充长度
			first[12] = (byte) ((str.length() + 18) >> 8);
			first[13] = (byte) (str.length() + 18);
			text += "源  地  址：" + GetHex(source);
			text += "\n目的地址：" + GetHex(dest);

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
	 *            整个帧数据，包括源地址，目的地址，帧长度，数据 和FCS 若为封装 FCS为0 ，执行完后FSC为对应的FCS 并返回FCS
	 *            若为校验，则FCS为对应的FCS 执行完后，若FCS为0 ，则认为无差错
	 * @return
	 */
	public static int getFCS(byte frame[])
	{

		int size = frame.length;
		if(size<64)
			return -1;
		int CHECK = 0x04C11DB7; // 生成多项式
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
				// System.out.println("temp  is "+toFullBinaryString(temp)+Integer.toHexString(temp));//调试输出语句
				// System.out.println("j     is "+toFullBinaryString(j)+Integer.toHexString(j));//调试输出语句
				// System.err.println("CRC   is "+toFullBinaryString(CRC)+Integer.toHexString(CRC));//调试输出语句
				if ((CRC & 0x80000000) == 0x80000000)// 首位为1
				{
					CRC <<= 1;
					if ((temp & j) == j) // 读入的数据的首位为1 则将CRC的末位置1
					{
						CRC |= 0x00000001;
					}
					CRC = CRC ^ CHECK;
				} else
				{
					CRC <<= 1;
					if ((temp & j) == j) // 读入的数据的首位为1 则将CRC的末位置1
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
		// System.err.println("CRC   is "+toFullBinaryString(CRC)+Integer.toHexString(CRC));//调试输出语句
		return CRC;

	}
	/**
	 * 
	 * @param str 待封装的数据
	 */
	public static void execute(String str)
	{

		byte pre[] = { (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
				(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xab };// 前导码和帧前定界符
		byte source[] = { (byte) 0x80, 0x00, (byte) 0xfe, (byte) 0x85, 0x3a,
				0x5f }; // 源地址
		byte dest[] = { (byte) 0x80, 0x00, (byte) 0xff, 0x60, 0x2c, (byte) 0xdc }; // 目的地址
		boolean hasAnother = true;
		text += "原始数据为：" + str;
		while (hasAnother)
		{
			text += "\r\n开始封装成帧\r\n";
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
			if (str.length() < 46) // 不足46个字节，补全为46个字节
			{
				hasAnother = false;
				for (int i = 0; i < (46 - size); i++)
				{
					str += '\u0000'; // 此处不可直接加数字0 或者字符串0 否则都会将0 强制转换为字符0, 即添加的是ACSII—48
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
			// 填充长度
			first[12] = (byte) ((str.length() + 18) >> 8);
			first[13] = (byte) (str.length() + 18);
			text += "源  地  址：" + GetHex(source);
			text += "\r\n目的地址：" + GetHex(dest);

//			int FCS = GetFCS(first, temp, 0);
			byte frame[] = getFrame(first, temp);
			int FCS = getFCS(frame);
			text += "\r\n当前帧数据为：" + temp;

			text += "\r\n帧内原始数据为：" + getDataFromFrame(frame);
			text += "\r\nFCS为：" + Integer.toHexString(FCS);
			text += "\r\nFCS校验帧的结果为：" + getFCS(frame);
		}
	}

	public static String getDataFromFrame(byte frame[])
	{
		byte sr[] = new byte[frame.length - 18];// 帧的数据部分

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
	 * 将6位的地址字段转化为字符串 便于输出显示
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
