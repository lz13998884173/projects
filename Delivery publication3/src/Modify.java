import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class Modify {

	JFrame frame;
	private JTextField textField;
	private JTextField IDtextField;
	private JTextField FirstNametextField;
	private JTextField LastNametextField;
	private JTextField AddresstextField;
	private JTextField PhoneNumbertextField;
	private Database dbase = null;
	private Customers c= null;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Modify(String s,Database d,Customers cust) {
		super();
		dbase=d;
		c=cust;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(BorderFactory.createTitledBorder( "Search"));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(33, 13, 435, 99);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("SearchByID:                 ");
		label.setBounds(6, 23, 233, 26);
		panel.add(label);
		
		textField = new JTextField(10);
		textField.setBounds(6, 49, 233, 26);
		panel.add(textField);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		button.setBounds(249, 49, 113, 27);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(BorderFactory.createTitledBorder( "SearchByID"));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(33, 139, 435, 261);
		frame.getContentPane().add(panel_1);
		
		JLabel lblId = new JLabel("ID:                 ");
		lblId.setBounds(6, 23, 233, 26);
		panel_1.add(lblId);
		
		IDtextField = new JTextField();
		IDtextField.setBounds(115, 24, 288, 24);
		panel_1.add(IDtextField);
		IDtextField.setColumns(10);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setBounds(6, 62, 72, 18);
		panel_1.add(lblFirstname);
		
		FirstNametextField = new JTextField();
		FirstNametextField.setColumns(10);
		FirstNametextField.setBounds(115, 62, 288, 24);
		panel_1.add(FirstNametextField);
		
		LastNametextField = new JTextField();
		LastNametextField.setColumns(10);
		LastNametextField.setBounds(115, 99, 288, 24);
		panel_1.add(LastNametextField);
		
		AddresstextField = new JTextField();
		AddresstextField.setColumns(10);
		AddresstextField.setBounds(115, 136, 288, 24);
		panel_1.add(AddresstextField);
		
		PhoneNumbertextField = new JTextField();
		PhoneNumbertextField.setColumns(10);
		PhoneNumbertextField.setBounds(115, 173, 288, 24);
		panel_1.add(PhoneNumbertextField);
		
		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setBounds(6, 102, 72, 18);
		panel_1.add(lblLastname);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(6, 139, 72, 18);
		panel_1.add(lblAddress);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(6, 176, 95, 18);
		panel_1.add(lblPhonenumber);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String SUBS = PhoneNumbertextField.getText().substring(0,2);
				
				if ( FirstNametextField.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(FirstNametextField.getText());
					
				}
				
				if ( LastNametextField.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(LastNametextField.getText());
					
				}
				
				if ( AddresstextField.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your address should consist of input letters only.");
					System.out.println(AddresstextField.getText());
					
				}
				
				if (!SUBS.equals("08")|| PhoneNumbertextField.getText().length()!=10)
				{  
					JOptionPane.showMessageDialog(null, "Invaild. You should input the phone numbers beginning with '08' followed by 8 more digits");
					System.out.println(PhoneNumbertextField.getText());
					System.out.println(SUBS);
				}
				
				
				else {
				c=new Customers(dbase);
				try {
					boolean mod = c.modifyCustomer(Integer.parseInt(IDtextField.getText()), FirstNametextField.getText(), LastNametextField.getText(), AddresstextField.getText(), PhoneNumbertextField.getText());
					if(mod  ==true)
					{
						JOptionPane.showMessageDialog(null, "Update Successful");
						IDtextField.setText("");
						FirstNametextField.setText("");
						LastNametextField.setText("");
						AddresstextField.setText("");
						PhoneNumbertextField.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Update Failed");
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			
			}
		});
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModify.setBounds(24, 221, 175, 27);
		panel_1.add(btnModify);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//Code to open the main menu			
					MainMenu main = new MainMenu();
					main.frame.setVisible(true);
					frame.setVisible(false); 
					frame.dispose(); 
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button_1.setBounds(259, 221, 162, 27);
		panel_1.add(button_1);
	}
}
