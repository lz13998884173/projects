import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class SubscriptionSearch {

	JFrame frame;
	private JTextField textField;

	private Database dbase = null;
	private Subscription sc=null;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SubscriptionSearch(String s,Database d,Subscription sub) {
		super();
		dbase=d;
		initialize();
		sc=sub;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 782, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(BorderFactory.createTitledBorder( "Search"));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(39, 28, 574, 445);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(55, 49, 419, 168);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSearchBySubscription = new JLabel("Search By Subscription ID:                 ");
		lblSearchBySubscription.setBounds(37, 5, 344, 18);
		panel_1.add(lblSearchBySubscription);
		
		textField = new JTextField(10);
		textField.setBounds(37, 44, 251, 24);
		panel_1.add(textField);
		
		JButton button = new JButton("Search");
		button.setBounds(69, 94, 167, 43);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(55, 230, 419, 146);
		panel.add(panel_2);
		
		JLabel lblSearchByCustomer = new JLabel("Search By Customer ID:                 ");
		lblSearchByCustomer.setBounds(37, 5, 344, 18);
		panel_2.add(lblSearchByCustomer);
		
		textField_1 = new JTextField(10);
		textField_1.setBounds(37, 52, 251, 24);
		panel_2.add(textField_1);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscription s=new Subscription();
				try {
					ResultSet rs;
					rs=s.searchSubscription_CustID(Integer.parseInt(textField_1.getText()));
					String[] sub_details = new String[7];
					sub_details[0]="Holiday Start: "+rs.getString("Holiday_Start");
					sub_details[1]="Holiday End: "+rs.getString("Holiday_End");
					sub_details[2]="Subscribed to Independent: "+rs.getString("Independent");
					sub_details[3]="Subscribed to Mirror: "+rs.getString("MIrror");
					sub_details[4]="Subscribed to Leader: "+rs.getString("Leader");
					sub_details[5]="Subscribed to Topic: "+rs.getString("Topic");
					sub_details[6]="textField_1: "+rs.getString("Sub_ID");
					String str="";
					for(int i=0;i<7;i++) {
						System.out.println(sub_details[i]);
						str+=sub_details[i]+"\n";
					
					}JOptionPane.showMessageDialog(null, str);
					
					
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(69, 86, 167, 37);
		panel_2.add(button_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				Subscription s=new Subscription();
			try {
				ResultSet rs;
				rs=s.searchSubscription_SubID(Integer.parseInt(textField.getText()));
				String[] sub_details = new String[7];
				sub_details[0]="Holiday Start: "+rs.getString("Holiday_Start");
				sub_details[1]="Holiday End: "+rs.getString("Holiday_End");
				sub_details[2]="Subscribed to Independent: "+rs.getString("Independent");
				sub_details[3]="Subscribed to Mirror: "+rs.getString("MIrror");
				sub_details[4]="Subscribed to Leader: "+rs.getString("Leader");
				sub_details[5]="Subscribed to Topic: "+rs.getString("Topic");
				sub_details[6]="Customer ID: "+rs.getString("Cust_ID");
				String str="";
				for(int i=0;i<7;i++) {
					System.out.println(sub_details[i]);
					str+=sub_details[i]+"\n";
				
				}JOptionPane.showMessageDialog(null, str);
				
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	}
}
