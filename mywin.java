package p1;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mywin extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}