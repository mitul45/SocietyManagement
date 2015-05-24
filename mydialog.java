package p1;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mydialog extends JDialog implements ActionListener
{
	JButton b1;
	public mydialog(JFrame f,String s)
	{
		super(f,"Message!",true);
		setLayout(new GridLayout());
		JLabel l1=new JLabel(s);
		b1=new JButton("OK");
		b1.addActionListener(this);
		JPanel p1,p2;
		p1=new JPanel();
		p2=new JPanel();
		p1.add(l1);
		p2.add(b1);
		add(p1,"North");
		add(p2,"Center");
		setSize(350,200);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(b1))
		{
			dispose();
		}
	}
}