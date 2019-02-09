
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

class CreateAccount extends JFrame implements ActionListener{
	
	
	private final JLabel lblAccountsInformation = new JLabel("Account Information");
	private final JLabel lblNewLabel = new JLabel("First name");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Customers[] Customers;
	private Database dbase = null;
	private Customers c= null;
	private Subscription Subscription;
	private JButton btnCancel;

	public CreateAccount(String s, Database d, Customers cust)
	{
		
		super(s);
		dbase = d;
		c  = cust;
		
		Container content=getContentPane();
		getContentPane().setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(72, 99, 106, 26);
		
		getContentPane().add(lblNewLabel);
		lblAccountsInformation.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
		lblAccountsInformation.setBounds(165, 41, 187, 26);
		
		getContentPane().add(lblAccountsInformation);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(72, 146, 106, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(72, 201, 74, 26);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone Number");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(72, 252, 117, 26);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField.setBounds(274, 99, 139, 33);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField_1.setBounds(274, 145, 139, 33);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField_2.setBounds(274, 196, 139, 33);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField_3.setBounds(274, 247, 139, 33);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//final Database dbase=new Database();

			
		JButton btnNewButton = new JButton("Create account");
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				String PhoneNumber = textField_3.getText();
				String FirstName=textField.getText();
				String LastName=textField_1.getText();
				String Address=textField_2.getText();
				String SUBS = PhoneNumber.substring(0,2);
				
				if ( FirstName.matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(FirstName);
					
				}
				if ( Address.matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your address should consist of input letters only.");
					System.out.println(Address);
					
				}
				if ( LastName.matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(LastName);
					
				}
				
				if (!SUBS.equals("08")|| PhoneNumber.length()!=10)
				{  
					JOptionPane.showMessageDialog(null, "Invaild. You should input the phone numbers beginning with '08' followed by 8 more digits");
					System.out.println(PhoneNumber);
					System.out.println(SUBS);
				}
				else
				{
					c =new Customers(FirstName,LastName,Address,PhoneNumber,dbase);
					boolean res = false;
					try 
					{
						res = c.insertCustomer();
					}
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(res == true)
					{
						JOptionPane.showMessageDialog(null, "Insert Successful");
					    SubscribeService sub=new SubscribeService("Create Subscribe Service",Subscription, dbase);
						setVisible(false); 
						dispose(); 

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert Failed");
					}
				}
				
				
			
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(55, 364, 178, 40);
		getContentPane().add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Code to open the main menu			
				MainMenu main = new MainMenu();
				main.frame.setVisible(true);
				setVisible(false); 
				dispose(); 
			}
		});
		btnCancel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancel.setBounds(288, 364, 178, 40);
		getContentPane().add(btnCancel);
		Font f=new Font("TimesRoman", Font.BOLD,20);
		refresh();
		setSize(561,485);    
		setVisible(true);
		}
	
	
	public void refresh(){
	}
	
	public void actionPerformed(ActionEvent e){
        
	  	Object target=e.getSource();
	 }
}