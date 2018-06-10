package test;

import java.awt.*;
import javax.swing.*;
public class OkDialog extends JDialog{
	JTextArea jt = new JTextArea(40,20);
	public OkDialog(JFrame owner)
	{
		super(owner, "确认支付", true);
		JPanel panel = new JPanel();
		JButton ok = new JButton("确认");
		JButton cancel = new JButton("取消");
		ok.addActionListener(event ->
		{
			EnterDialog e = new EnterDialog(OkDialog.this,"您已成功支付"	);
			e.setVisible(true);
			setVisible(false);
		});
		cancel.addActionListener(event ->
		{
			setVisible(false);
		});
		jt.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setLineWrap(true);
		add(scrollPane,BorderLayout.CENTER);
		panel.add(ok);
		panel.add(cancel);
		add(panel,BorderLayout.SOUTH);
		setSize(300, 400);
		setLocationByPlatform(true);;
	}
	public JTextArea getJTextArea()
	{
		return jt;
	}
}
