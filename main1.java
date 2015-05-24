import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import p1.mywin;
import p1.mydialog;

class add extends JPanel implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton b1,b2;
	String s1,s2,s3;
	int no,mem;
	mydialog md;
	JFrame f;
	public add(JFrame f1)
	{
		f=f1;
		l1=new JLabel("Flat No.");
		l2=new JLabel("Owner");
		l3=new JLabel("Members");
		t1=new JTextField(2);
		t2=new JTextField(30);
		t3=new JTextField(2);
		b1=new JButton("Add");
		b2=new JButton("Reset");
		b1.addActionListener(this);
		b2.addActionListener(this);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(b1))
		{
			s1=t1.getText();
			s2=t2.getText();
			s3=t3.getText();
			if(s1.equals("") || s2.equals("") || s3.equals(""))
			{
				md=new mydialog(f,"Fill all the fields.");
				t1.setText("");
				t2.setText("");
				t3.setText("");
			}
			else
			{
				try
				{
					no=Integer.parseInt(s1);
					mem=Integer.parseInt(s3);
					if((no>20 || no<1) || mem<1)
					{
						md=new mydialog(f,"Enter proper data.");
						t1.setText("");
						t2.setText("");
						t3.setText("");
					}
					else
					{
						try
						{
							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
							Statement st=c.createStatement();
							ResultSet rs=st.executeQuery("select data2.status from data2 where (data2.[no]=" + no + ")");
							Statement st1=c.createStatement();
							Statement st2=c.createStatement();
							String s4="";
							rs.next();
							s4=rs.getString("status");
							if(s4.equals("not empty"))
							{
								md=new mydialog(f,"Flat already occupied.");
							}
							else
							{
								int x=st1.executeUpdate("update data1 set data1.owner='" + s2 + "',data1.members=" + mem + " where (data1.[no]=" + no + ")");
								int y=st2.executeUpdate("update data2 set data2.status='not empty' where (data2.[no]=" + no + ")");
								md=new mydialog(f,"Data inserted.");
							}
							t1.setText("");
							t2.setText("");
							t3.setText("");
							c.close();
						}
						catch(Exception e1)
						{
							md=new mydialog(f,"Connection Error.");
						}
					}
				}
				catch(Exception e1)
				{
					md=new mydialog(f,"Enter Proper Data.");
					t1.setText("");
					t2.setText("");
					t3.setText("");
				}
			}
		}
		if(e.getSource().equals(b2))
		{
				t1.setText("");
				t2.setText("");
		}		t3.setText("");
	}
}

class remove extends JPanel implements ActionListener
{
	JTextField t1,t2;
	JButton b1,b2;
	JLabel l1,l2;
	String s1,s2,s3;
	int no;
	mydialog md;
	JFrame f;
	public remove(JFrame f1)
	{
		f=f1;
		l1=new JLabel("Flat No.");
		l2=new JLabel("Owner");
		b1=new JButton("Remove");
		b2=new JButton("Reset");
		t1=new JTextField(2);
		t2=new JTextField(30);
		b1.addActionListener(this);
		b2.addActionListener(this);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(b2))
		{
			t1.setText("");
			t2.setText("");
		}
		if(e.getSource().equals(b1))
		{
			s1=t1.getText();
			s2=t2.getText();
			if(s1.equals("") || s2.equals(""))
			{
				md=new mydialog(f,"Fill all the fields.");
				t1.setText("");
				t2.setText("");
			}
			else
			{
				try
				{
					no=Integer.parseInt(s1);
					if(no>20 || no<1)
					{
						md=new mydialog(f,"Enter proper data.");
					}
					else
					{
						try
						{
							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
							Statement st=c.createStatement();
							ResultSet rs=st.executeQuery("select data2.status from data2 where (data2.[no]=" + no + ")");
							rs.next();
							s3=rs.getString("status");
							if(s3.equals("empty"))
							{
								md=new mydialog(f,"It is already empty.");
							}
							else
							{
								int x=st.executeUpdate("update data1 set data1.owner='null',data1.[members]=0 where (data1.[no]=" + no + ") and owner='" + s2 + "'");
								if(x!=0)
								{
									int y=st.executeUpdate("update data2 set data2.status='empty' where (data2.[no]=" + no + ")");
									md=new mydialog(f,"Data removed.");
								}
								else
									md=new mydialog(f,"Data not removed.");

							}
							t1.setText("");
							t2.setText("");
							c.close();
						}
						catch(Exception e1)
						{
							md=new mydialog(f,"Connction error.");
						}
					}
				}
				catch(Exception e1)
				{
					md=new mydialog(f,"Enter proper data.");
					t1.setText("");
					t2.setText("");
				}
			}
		}
	}
}

class edit extends JPanel implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton b1,b2,b3;
	String s1,s2,s3;
	int no,mem;
	mydialog md;
	JFrame f;
	public edit(JFrame f1)
	{
		f=f1;
		l1=new JLabel("Flat No.");
		l2=new JLabel("Owner");
		l3=new JLabel("Members");
		t1=new JTextField(2);
		t2=new JTextField(30);
		t3=new JTextField(2);
		b1=new JButton("Edit");
		b2=new JButton("Reset");
		b3=new JButton("Find");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		add(l1);
		add(t1);
		add(b3);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(b3))
		{
			s1=t1.getText();
			if(s1.equals(""))
			{
				md=new mydialog(f,"Enter Number.");
			}
			else
			{
				try
				{
					no=Integer.parseInt(s1);
				}
				catch(Exception e1)
				{
					md=new mydialog(f,"Enter proper number.");
				}
				if(no>0 || no<21)
				{
					try
					{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
						Statement st=c.createStatement();
						ResultSet rs=st.executeQuery("select data2.status from data2 where (data2.[no]=" + no + ")");
						rs.next();
						String s7=rs.getString("status");
						if(s7.equals("empty"))
						{
							md=new mydialog(f,"Can't modify.Flat is empty.");
						}
						else
						{
							rs=st.executeQuery("select data1.owner,data1.[members] from data1 where (data1.[no]=" + no + ")");
							rs.next();
							s2=rs.getString("owner");
							mem=rs.getInt("members");
							t2.setText(s2);
							t3.setText("" + mem);
						}
						c.close();
					}
					catch(Exception e1)
					{
						md=new mydialog(f,"Connection error.");
						t1.setText("");
					}
				}
				else
				{
					md=new mydialog(f,"Enter proper number");
					t1.setText("");
				}
			}
		}
		if(e.getSource().equals(b2))
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
		}
		if(e.getSource().equals(b1))
		{
			s1=t1.getText();
			if(s1.equals(""))
			{
				md=new mydialog(f,"Enter Number.");
			}
			else
			{
				try
				{
					no=Integer.parseInt(s1);
				}
				catch(Exception e1)
				{
					md=new mydialog(f,"Enter proper number.");
				}
				if(no<1 || no>20)
				{
					md=new mydialog(f,"Enter proper number.");
					t1.setText("");
					t2.setText("");
					t3.setText("");
				}
				else
				{
					s2=t2.getText();
					s3=t3.getText();
					try
					{
						mem=Integer.parseInt(s3);
					}
					catch(Exception e1)
					{
						md=new mydialog(f,"Enter proper data.");
						t1.setText("");
						t2.setText("");
						t3.setText("");
					}
					if((s2.equals("") || s2.equals("null")) || mem<1)
					{
						md=new mydialog(f,"Enter proper data.");
						t1.setText("");
						t2.setText("");
						t3.setText("");
					}
					else
					{
						try
						{
							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
							Statement st=c.createStatement();
							ResultSet rs=st.executeQuery("select data2.status from data2 where (data2.[no]=" + no + ")");
							rs.next();
							String s7=rs.getString("status");
							if(s7.equals("empty"))
							{
								md=new mydialog(f,"Can't modify.Flat is empty.");
								c.close();
							}
							else
							{
								Statement st1=c.createStatement();
								Statement st2=c.createStatement();
								int x=st1.executeUpdate("update data1 set data1.owner='" + s2 + "',data1.members=" + mem + " where (data1.[no]=" + no + ")");
								int y=st2.executeUpdate("update data2 set data2.status='not empty' where (data2.[no]=" + no + ")");
								md=new mydialog(f,"Data updated.");
								t1.setText("");
								t2.setText("");
								t3.setText("");
								c.close();
							}
						}
						catch(Exception e1)
						{
							md=new mydialog(f,"Connetion error.");
							t1.setText("");
							t2.setText("");
							t3.setText("");
						}
					}
				}
			}
		}
	}
}

class pay extends JPanel implements ActionListener
{
	JTextField t1,t2,t3,t4;
	JLabel l1,l2,l3,l4;
	JButton b1,b2;
	String s1,s2,s3,s4;
	JFrame f;
	mydialog md;
	int no,date,month,year;
	public pay(JFrame f1)
	{
		f=f1;
		t1=new JTextField(2);
		t2=new JTextField(2);
		t3=new JTextField(2);
		t4=new JTextField(4);
		l1=new JLabel("Flat No.");
		l2=new JLabel("Date(dd)");
		l3=new JLabel("Month(mm)");
		l4=new JLabel("Year(yyyy)");
		b1=new JButton("Pay");
		b2=new JButton("Reset");
		b1.addActionListener(this);
		b2.addActionListener(this);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e)
	{
		s1=t1.getText();
		s2=t2.getText();
		s3=t3.getText();
		s4=t4.getText();
		if(e.getSource().equals(b2))
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
		}
		if(e.getSource().equals(b1))
		{
			if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals(""))
			{
				md=new mydialog(f,"Fill all the fields.");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			else
			{
				try
				{
					no=Integer.parseInt(s1);
					date=Integer.parseInt(s2);
					month=Integer.parseInt(s3);
					year=Integer.parseInt(s4);
				}
				catch(Exception e1)
				{
					md=new mydialog(f,"Enter proper data.");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
				}
				if((no<0 || no>20))
				{
					md=new mydialog(f,"Flat number doesn't exist.");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
				}
				else
				{
					if(year <2000 || date<1 ||date >31)
					{
						md=new mydialog(f,"Enter date properly.");
						t1.setText("");
						t2.setText("");
						t3.setText("");
						t4.setText("");
					}
					else
					{
						int m[]=new int[12];
						if((year%4)==0)
						{
							m[0]=31;
							m[1]=29;
							m[2]=31;
							m[3]=30;
							m[4]=31;
							m[5]=30;
							m[6]=31;
							m[7]=31;
							m[8]=30;
							m[9]=31;
							m[10]=30;
							m[11]=31;
						}
						else
						{
							m[0]=31;
							m[1]=28;
							m[2]=31;
							m[3]=30;
							m[4]=31;
							m[5]=30;
							m[6]=31;
							m[7]=31;
							m[8]=30;
							m[9]=31;
							m[10]=30;
							m[11]=31;
						}
						if(date>0 && date<m[month-1])
						{
							try
							{
								Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
								Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
								Statement st=c.createStatement();
								ResultSet rs=st.executeQuery("select data2.status from data2 where (data2.[no]=" + no + ")");
								rs.next();
								String s7=rs.getString("status");
								if(s7.equals("empty"))
								{
									md=new mydialog(f,"Flat is empty.");
									t1.setText("");
									t2.setText("");
									t3.setText("");
									t4.setText("");
								}
								else
								{
									Statement st1=c.createStatement();
									ResultSet rs1=st1.executeQuery("SELECT data3.[no], data3.date, data3.month, data3.year FROM data3 WHERE (((data3.[no])=" + no + ") AND ((data3.month)=" + month + ") AND ((data3.year)=" + year + "))");
									int x=0;
									while(rs1.next())
									{
										x++;
									}
									if(x>0)
									{
										md=new mydialog(f,"You have already paid for this month.");
										t1.setText("");
										t2.setText("");
										t3.setText("");
										t4.setText("");
									}
									else
									{
										PreparedStatement ps=c.prepareStatement("insert into data3 values(?,?,?,?)");
										ps.setInt(1,no);
										ps.setInt(2,date);
										ps.setInt(3,month);
										ps.setInt(4,year);
										int y=ps.executeUpdate();
										md=new mydialog(f,"Maintenance paid.");
									}
									c.close();
								}
							}
							catch(Exception e1)
							{
								md=new mydialog(f,"Connetion error.");
								t1.setText("");
								t2.setText("");
								t3.setText("");
								t4.setText("");
							}
						}
					}
				}
			}
		}
	}
}

class summ extends JPanel implements ActionListener
{
	JButton b1;
	JTextArea t1;
	JFrame f;
	mydialog md;
	public summ(JFrame f1)
	{
		f=f1;
		b1=new JButton("Display");
		t1=new JTextArea(50,50);
		add(t1);
		add(b1);
		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(b1))
		{
			String s1;
			t1.setText("");
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection c=DriverManager.getConnection("Jdbc:Odbc:mydata","","");
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery("select * from data1,data2 where data1.[no]=data2.[no]");
				String s[]=new String[20];
				int i=0;
				while(rs.next())
				{
					s[i]="" + rs.getInt("no") + "\t" + rs.getString("owner") + "\t" + rs.getInt("members") + "\t" +rs.getString("status")+"\t";
					i++;
				}
				s1="Flat No.\tOwner \tMembers\tStatus\n";
				for(i=0;i<20;i++)
				{
					s1=s1 + "\n" + s[i];
				}
				t1.setText(s1);
				c.close();
			}
			catch(Exception e1)
			{
			}
		}
	}
}

public class main1 extends JFrame
{
	JTabbedPane t;
	add ad;
	remove r;
	edit e;
	pay p;
	summ s;
	public main1()
	{
		t=new JTabbedPane();
		ad=new add(this);
		r=new remove(this);
		e=new edit(this);
		p=new pay(this);
		s=new summ(this);
		t.addTab("Add Member",null,ad);
		t.addTab("Remove Member",null,r);
		t.addTab("Edit data",null,e);
		t.addTab("Pay Maintenance",null,p);
		t.addTab("Summary",null,s);
		getContentPane().add(t);
		addWindowListener(new mywin());
	}
	public static void main(String args[])
	{
		JFrame f=new main1();
		f.setSize(800,700);
		f.setVisible(true);
	}
}