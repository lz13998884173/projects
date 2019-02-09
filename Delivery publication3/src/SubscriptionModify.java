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

public class SubscriptionModify {

	JFrame frame;
	private JTextField textField_1;

	private Database dbase = null;
	private Subscription sc=null;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SubscriptionModify(String s,Database d,Subscription sub) {
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
		frame.setBounds(100, 100, 538, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(BorderFactory.createTitledBorder( "SearchByID"));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 10, 491, 376);
		frame.getContentPane().add(panel_1);
		
		JLabel lblModifysubid = new JLabel("Modify_SubID:                 ");
		lblModifysubid.setBounds(14, 23, 233, 26);
		panel_1.add(lblModifysubid);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(115, 24, 288, 24);
		panel_1.add(textField_1);
		
		JCheckBox checkBox = new JCheckBox("Irish Independent");
		checkBox.setBounds(14, 186, 175, 25);
		panel_1.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Irish Mirror");
		checkBox_1.setBounds(221, 186, 182, 25);
		panel_1.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Longford Leader");
		checkBox_2.setBounds(14, 231, 175, 25);
		panel_1.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Westmeath Topic");
		checkBox_3.setBounds(221, 231, 182, 25);
		panel_1.add(checkBox_3);
		
		JButton btnModifysubid = new JButton("Modify_SubID");
		btnModifysubid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sc=new Subscription();
				int id=Integer.parseInt(textField_1.getText());
				String dataStart=textField_2.getText();
				String dataEnd=textField_3.getText();
				boolean independent=checkBox.isSelected();
				boolean mirror=checkBox_1.isSelected();
				boolean leader=checkBox_2.isSelected();
				boolean topic=checkBox_3.isSelected();
				try {
				boolean	 rest=sc.modifySubscription_SubID(id, dataStart, dataEnd,independent,mirror,leader,topic);
				if(rest==true)
					JOptionPane.showMessageDialog(null, "Subscription Found");	
				else
					JOptionPane.showMessageDialog(null, "Subscription does not exist");	
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnModifysubid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModifysubid.setBounds(14, 300, 145, 27);
		panel_1.add(btnModifysubid);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Code to open the main menu			
				MainMenu main = new MainMenu();
				main.frame.setVisible(true);
				frame.setVisible(false); 
				frame.dispose(); 
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button_2.setBounds(338, 300, 125, 27);
		panel_1.add(button_2);
		
		
		
		JLabel lblNewLabel = new JLabel("HolidayStart");
		lblNewLabel.setBounds(14, 129, 87, 27);
		panel_1.add(lblNewLabel);
		
		JLabel lblHolidayend = new JLabel("HolidayEnd");
		lblHolidayend.setBounds(221, 129, 87, 27);
		panel_1.add(lblHolidayend);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 132, 95, 21);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(292, 132, 87, 21);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblModifycustid = new JLabel("Modify_CustID:                 ");
		lblModifycustid.setBounds(14, 69, 233, 26);
		panel_1.add(lblModifycustid);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(115, 72, 288, 24);
		panel_1.add(textField);
		
		JButton btnModifycustid = new JButton("Modify_CustID");
		btnModifycustid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sc=new Subscription();
				int id=Integer.parseInt(textField.getText());
				String dataStart=textField_2.getText();
				String dataEnd=textField_3.getText();
				boolean independent=checkBox.isSelected();
				boolean mirror=checkBox_1.isSelected();
				boolean leader=checkBox_2.isSelected();
				boolean topic=checkBox_3.isSelected();
				try {
				boolean	 rest=sc.modifySubscription_CustID(id, dataStart, dataEnd,independent,mirror,leader,topic);
				if(rest==true)
					JOptionPane.showMessageDialog(null, "Customer Found");	
				else
					JOptionPane.showMessageDialog(null, "Customer does not exist");	
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnModifycustid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModifycustid.setBounds(169, 300, 159, 27);
		panel_1.add(btnModifycustid);
	}
}
