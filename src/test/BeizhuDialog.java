package test;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class BeizhuDialog extends JDialog {
	public BeizhuDialog(JFrame owner) {
		super(owner, "��ע", true);
		JPanel panel = new JPanel();
		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		JTextArea jt = new JTextArea(8,20);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setLineWrap(true);
		add(scrollPane,BorderLayout.CENTER);
		panel.add(ok);
		panel.add(cancel);
		add(panel, BorderLayout.SOUTH);
		setSize(300, 400);
		setLocationByPlatform(true);
		ok.addActionListener(event -> {
			try(PrintWriter out = new PrintWriter("wenjian\\beizhu.txt","UTF-8"))
			{
				String[] lineString = jt.getText().split("\n");
				for(int i = 0; i < lineString.length; i++)
					out.print(lineString[i]);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			EnterDialog e = new EnterDialog(BeizhuDialog.this, "����ɹ���");
			e.setVisible(true);
			setVisible(false);
		});
		cancel.addActionListener(event -> {
			setVisible(false);
		});
	}
}
