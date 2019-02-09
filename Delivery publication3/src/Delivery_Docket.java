
import java.awt.EventQueue;
/*import DATABASE.Database;
import Process.Docket;*/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class Delivery_Docket {

	JFrame frame;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField;
	private JTextField textField1;
	static Database dbase=new Database();
	static Docket dock = null;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Delivery_Docket(Database db, Docket d) 
	{
		dbase = db;
		dock = d;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 572, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delivery Area ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(32, 120, 180, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Delivery Person: First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(32, 173, 242, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCustomerId = new JLabel("Delivery Person: Surname");
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomerId.setBounds(32, 220, 242, 33);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblSubscriptionId = new JLabel("Subscription ID");
		lblSubscriptionId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubscriptionId.setBounds(32, 360, 180, 31);
		frame.getContentPane().add(lblSubscriptionId);
		
		textField2 = new JTextField();
		textField2.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField2.setColumns(10);
		textField2.setBounds(258, 122, 242, 33);
		frame.getContentPane().add(textField2);
		
		textField3 = new JTextField();
		textField3.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField3.setColumns(10);
		textField3.setBounds(258, 174, 242, 33);
		frame.getContentPane().add(textField3);
		
		textField4 = new JTextField();
		textField4.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField4.setColumns(10);
		textField4.setBounds(258, 220, 242, 33);
		frame.getContentPane().add(textField4);
		
		textField5 = new JTextField();
		textField5.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField5.setColumns(10);
		textField5.setBounds(258, 358, 242, 33);
		frame.getContentPane().add(textField5);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( textField.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField2.getText());
				}
				
				if ( textField1.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField1.getText());
				}
				
				if ( textField2.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your delivery area should consist of input letters only.");
					System.out.println(textField2.getText());
				}
				
				if ( textField3.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField3.getText());
				}
				
				if ( textField4.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField4.getText());
				}
				else {
				
				dock = new Docket(textField2.getText(),textField3.getText(),textField4.getText(),textField.getText(),textField1.getText(),Integer.parseInt(textField5.getText()),dbase);
				
				boolean res = dock.insertDocket();
				
				if(res == true)
				{
					JOptionPane.showMessageDialog(null, "Insert Successful");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert Failed");
				}
				
			}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmit.setBounds(96, 420, 178, 40);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblCustomerFirstname = new JLabel("Customer: First Name");
		lblCustomerFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomerFirstname.setBounds(31, 266, 221, 33);
		frame.getContentPane().add(lblCustomerFirstname);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField.setBounds(257, 266, 243, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField1 = new JTextField();
		textField1.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		textField1.setColumns(10);
		textField1.setBounds(258, 312, 242, 33);
		frame.getContentPane().add(textField1);
		
		JLabel lblCustomerSurname = new JLabel("Customer: Surname");
		lblCustomerSurname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomerSurname.setBounds(32, 314, 170, 33);
		frame.getContentPane().add(lblCustomerSurname);
		
		JLabel lblDailyDeliveryDockets = new JLabel("Daily Delivery Dockets");
		lblDailyDeliveryDockets.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
		lblDailyDeliveryDockets.setBounds(185, 57, 212, 26);
		frame.getContentPane().add(lblDailyDeliveryDockets);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//Code to open the main menu			
				MainMenu main = new MainMenu();
				main.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setBounds(316, 420, 178, 40);
		frame.getContentPane().add(button);
		
		
	}
}
