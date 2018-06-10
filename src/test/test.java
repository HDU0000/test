package test;
import javax.swing.*;
import java.awt.*;

public class test{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			JFrame frame = new DoubleFrame();
			frame.setTitle("µã²ËÏµÍ³");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200,500);
			frame.setVisible(true);
		});
	}
}
