import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class MainMenu {

	JFrame frame;
	private Database db = new Database();
	private Customers cust;
	private Docket dock;
	private Subscription Subscription;
	private DeliveryPerson del;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 858, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewspaperDeliverySystem = new JLabel("Newspaper Delivery System");
		lblNewspaperDeliverySystem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewspaperDeliverySystem.setBounds(312, 29, 269, 52);
		frame.getContentPane().add(lblNewspaperDeliverySystem);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				LOGIN log = new LOGIN("Login");
				//Code to close current window
				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBounds(237, 463, 196, 45);
		frame.getContentPane().add(btnLogout);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBounds(77, 97, 226, 137);
		frame.getContentPane().add(panel);
		
		JLabel lblCustomer = new JLabel("Customer");
		panel.add(lblCustomer);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnCreateNewCustomer = new JButton("Create New Account");
		panel.add(btnCreateNewCustomer);
		btnCreateNewCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCreateNewCustomer.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JButton btnLookupCustomerAccount = new JButton("Lookup Customer Account");
		btnLookupCustomerAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s=new Search("Search Information",db,cust);
				s.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 
				
			}
		});
		btnLookupCustomerAccount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLookupCustomerAccount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.add(btnLookupCustomerAccount);
		
		JButton btnModifyCustomerAccount = new JButton("Modify Customer Account");
		btnModifyCustomerAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modify m=new Modify("Modify Information",db,cust);
				m.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose();
			}
		});
		btnModifyCustomerAccount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnModifyCustomerAccount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.add(btnModifyCustomerAccount);
		
		JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.setBounds(77, 247, 226, 153);
		frame.getContentPane().add(panel_1);
		
		JLabel lblDeliveryDocket = new JLabel("Delivery Docket");
		panel_1.add(lblDeliveryDocket);
		lblDeliveryDocket.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnCreateNewDocket = new JButton("Create New Docket");
		panel_1.add(btnCreateNewDocket);
		btnCreateNewDocket.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCreateNewDocket.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_2.setBounds(349, 94, 211, 142);
		frame.getContentPane().add(panel_2);
		
		JLabel lblDeliveryPerson = new JLabel("Delivery Person");
		lblDeliveryPerson.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_2.add(lblDeliveryPerson);
		
		JButton btnAddNewDelivery = new JButton("Add New Delivery Person");
		btnAddNewDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				CreateDeliveryPerson dp = new CreateDeliveryPerson("Create Delivery Person", db, del);
				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
		btnAddNewDelivery.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAddNewDelivery.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.add(btnAddNewDelivery);
	    
		JPanel panel_3 =  new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_3.setBounds(349, 247, 211, 153);
		frame.getContentPane().add(panel_3);
		
		JLabel lblSubscription_1 = new JLabel("Subscription");
		lblSubscription_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_3.add(lblSubscription_1);
		
		JButton btnS = new JButton("Modify Subscription");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				SubscriptionModify sm=new SubscriptionModify("SubscriptionSearch",db,Subscription);
				sm.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
		btnS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.add(btnS);
		
		JButton btnSearchSubscription = new JButton("Search Subscription");
		btnSearchSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubscriptionSearch ss=new SubscriptionSearch("SubscriptionSearch",db,Subscription);
				ss.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 

				
			
			}
		});
		btnSearchSubscription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSearchSubscription.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.add(btnSearchSubscription);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainMenu.class.getResource("/image/backgr2.jpg")));
		label.setBounds(0, 0, 840, 553);
		frame.getContentPane().add(label);
		btnCreateNewDocket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Delivery_Docket dd = new Delivery_Docket(db, dock);		
				dd.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 		
			}
		});
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				CreateAccount ca = new CreateAccount("Create Customer Account", db, cust);

				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
	}
}
