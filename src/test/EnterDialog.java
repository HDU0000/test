package test;

import java.awt.*;
import javax.swing.*;
public class EnterDialog extends JDialog{
	public EnterDialog(JDialog owner,String s)
	{
		super(owner, "ȷ��", true);
		JPanel panel = new JPanel();
		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		JLabel jl = new JLabel(s);
		ok.addActionListener(event ->
		{
			setVisible(false);
		});
		cancel.addActionListener(event ->
		{
			setVisible(false);
		});
		add(jl, BorderLayout.NORTH);
		panel.add(ok,BorderLayout.SOUTH);
		add(panel,BorderLayout.SOUTH);
		setSize(200, 100);
		setLocationByPlatform(true);
	}
	public EnterDialog(JFrame owner,String s)
	{
		super(owner, "ȷ��", true);
		JPanel panel = new JPanel();
		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		JLabel jl = new JLabel(s);
		ok.addActionListener(event ->
		{
			setVisible(false);
		});
		cancel.addActionListener(event ->
		{
			setVisible(false);
		});
		add(jl, BorderLayout.NORTH);
		panel.add(ok,BorderLayout.SOUTH);
		add(panel,BorderLayout.SOUTH);
		setSize(200, 100);
		setLocationByPlatform(true);
	}
}