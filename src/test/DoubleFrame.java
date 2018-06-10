package test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
public class DoubleFrame extends JFrame {
	double sum=0;
	ArrayList<Dish> adish = new ArrayList<Dish>();
	private JScrollPane jsp1;
	private JScrollPane jsp2;
	private JScrollPane jsp3;
	private OkDialog dialog;
	JPanel jp1;
	JPanel jp2;
	JPanel jp3;
	JPanel jp4;
	String caidan;
	JLabel jlpic;
	int totalDish = 0;
	int renshu=1,zhuohao;
	JTextArea jt = new JTextArea(40,8);
	String[] image = new String[100];
	int number[] = new int[100];
	double cost[] = new double[100];
	public DoubleFrame()
	{
		image[0] = "wenjian//0.jpg";
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jlpic = new JLabel();
		jlpic = new JLabel(new ImageIcon(image[0]));
		jt.setEditable(false);
		jt.setLineWrap(true);
		jp2.add(jlpic);
		jp3.setLayout(new FlowLayout());
		jsp1 = new JScrollPane(jp1);
		jsp2 = new JScrollPane(jp2);
		jsp3 = new JScrollPane(jt);
		add(jsp1, new GBC(0,0).setFill(GBC.BOTH).setWeight(100,  100));
		add(jsp2, new GBC(1,0).setFill(GBC.BOTH).setWeight(100,  100));
		add(jsp3, new GBC(2,0).setFill(GBC.BOTH).setWeight(100, 100));
		add(jp3, new GBC(0,1).setFill(GBC.BOTH).setWeight(30, 4));
		jp1.setLayout(layout);
		JLabel jb1 = new JLabel("菜单");
		JLabel jb2 = new JLabel("数量");
		JLabel jb3 = new JLabel("价格");
		jp1.add(jb1, new GBC(0,0,4,1).setFill(GBC.CENTER).setWeight(0, 0));
		jp1.add(jb2, new GBC(5,0,1,1).setFill(GBC.CENTER).setWeight(0, 0));
		jp1.add(jb3, new GBC(7,0,1,1).setFill(GBC.CENTER).setWeight(0, 0));
		int count = 1;
		totalDish = readImage();
		count = readOrder(count);
		jp1.add(jp4, new GBC(0,count,0,0).setFill(GBC.BOTH).setWeight(0, 100));
		count++;
		JButton heji = new JButton("合计");
		jp3.add(heji);
		heji.addActionListener(event ->
		{
			show(jt);
		});
		JComboBox<Integer> jcombo = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,});
		JLabel re = new JLabel("用餐人数: ");
		jp3.add(re);
		jcombo.setSelectedIndex(0);
		jcombo.addActionListener(event ->
		{
			renshu = jcombo.getSelectedIndex()+1;
		});
		jp3.add(jcombo);
		JButton zhifu = new JButton("确认完成");
		JTextArea hao = new JTextArea(1,4);
		zhifu.addActionListener(event ->
		{
			if(checkZhuohao(hao))
			{
				if(dialog == null)
					dialog = new OkDialog(DoubleFrame.this);
				JTextArea jtx;
				jtx = dialog.getJTextArea();
				show(jtx);
				jtx.append("桌号" + zhuohao + "\n");
				jtx.append("备注: \n");
				try(Scanner in = new Scanner(new FileInputStream("wenjian\\beizhu.txt"), "UTF-8"))
				{
					while(in.hasNext())
					{
						jtx.append(in.nextLine());
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				dialog.setVisible(true);
			}
		});
		JLabel zhuo = new JLabel("桌号");
		jp3.add(zhuo);
		jp3.add(hao);
		jp3.add(heji);
		jp3.add(zhifu);
		JButton beizhu = new JButton("备注");
		beizhu.addActionListener(event ->
		{
			BeizhuDialog bz = new BeizhuDialog(DoubleFrame.this);
			bz.setVisible(true);
		});
		jp3.add(beizhu);
		pack();
	}
	public void show(JTextArea gtr)
	{
		sum = 0;
		gtr.setText("");
		gtr.append("合计: \n");
		boolean flag = false;
		for(int i = 0; i < totalDish;i++)
		{
			if(number[i] > 0)
			{
				gtr.append(adish.get(i).getName() + "  " + Integer.toString(number[i]) + "\n");
				sum = sum + adish.get(i).getCost() * number[i];
				flag = true;
			}
		}
		if(!flag)
		{
			gtr.setText("您还未点菜\n");
			return;
		}
		gtr.append("合计金额: " + sum + "\n");
		gtr.append("用餐人数: " + renshu + "\n");
	}
	public boolean checkZhuohao(JTextArea jt)
	{
		try 
		{
			zhuohao = Integer.parseInt(jt.getText());
			return true;
		}
		catch(Exception e)
		{
			EnterDialog enter = new EnterDialog(DoubleFrame.this,"请输入一个整数桌号");
			enter.setVisible(true);
			return false;
		}
	}

	public int writeOrder(int weizhi, Dish[] a, String s)
	{
		JLabel j3 = new JLabel(s);
		jp1.add(j3, new GBC(0,weizhi,1,1).setFill(GBC.HORIZONTAL).setWeight(0, 0));
		weizhi++;
		for(int i = 0; i < a.length; i++)
		{
			final int index = i;
			JButton jb1 = new JButton(a[i].getName());
			jp1.add(jb1, new GBC(0,weizhi,4,1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
			JButton jb2 = new JButton("+");
			JButton jb3 = new JButton("-");
			jb3.setEnabled(false);
			JLabel jl1 = new JLabel(Integer.toString(number[a[i].getNmuber()]));
			JLabel jl2 = new JLabel(Double.toString(a[i].getCost()));
			jb2.addActionListener(event ->
			{
				number[a[index].getNmuber()]++;
				jb3.setEnabled(true);
				jl1.setText(Integer.toString(number[a[index].getNmuber()]));
			});
			jb3.addActionListener(event ->
			{
				if(number[a[index].getNmuber()] > 0)
				{
					number[a[index].getNmuber()]--;
					jl1.setText(Integer.toString(number[a[index].getNmuber()]));
					if(number[a[index].getNmuber()] == 0)
						jb3.setEnabled(false);
				}
			});
			jb1.addActionListener(event ->
			{
				jt.setText(a[index].toString());
				jlpic.setIcon(new ImageIcon(image[a[index].getNmuber()+1]));
			});
			jp1.add(jb2, new GBC(4,weizhi,1,1).setFill(GBC.NONE).setAnchor(GBC.EAST).setWeight(10, 0));
			jp1.add(jb3, new GBC(6,weizhi,1,1).setFill(GBC.NONE).setWeight(0, 0));
			jp1.add(jl1, new GBC(5,weizhi,1,1).setFill(GBC.NONE).setWeight(0, 0));
			jp1.add(jl2, new GBC(7,weizhi,1,1).setFill(GBC.NONE).setWeight(0, 0));
			weizhi++;
		}
		return weizhi;
	}
	public int readOrder(int n)
	{
		try(Scanner in = new Scanner(new FileInputStream("wenjian\\Dish.txt"), "UTF-8"))
		{
			while(in.hasNext())
			{
				String kind = in.nextLine();
				int len = Integer.parseInt(in.nextLine());
				Dish a[] = new Dish[len];
				for(int i = 0; i < len; i++)
				{
					a[i] = new Dish();
					a[i].setName(in.nextLine());
					a[i].setDetail(in.nextLine());
					a[i].setCost(Double.parseDouble(in.nextLine()));
					a[i].setIndex(Integer.parseInt(in.nextLine()));
					adish.add(a[i]);
				}
				n = writeOrder(n, a, kind);
			}
			return n;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return n;
	}
	public int readImage()
	{
		int n = 0;
		try(Scanner in = new Scanner(new FileInputStream(""
				+ "wenjian\\Image.txt"), "UTF-8"))
		{
			in.nextLine();
			n = Integer.parseInt(in.nextLine());
			for(int i = 0; i < n; i++)
				image[i+1] = in.nextLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return n;
	}
}
