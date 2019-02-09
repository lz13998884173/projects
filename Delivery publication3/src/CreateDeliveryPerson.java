
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

class CreateDeliveryPerson extends JFrame implements ActionListener {

	private final JLabel lblAccountsInformation = new JLabel("Delivery Person Information");
	private final JLabel lblNewLabel = new JLabel("First name");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Customers[] Customers;
	private Database dbase = null;
	private DeliveryPerson dp = null;
	private JButton btnCancel;

	public CreateDeliveryPerson(String s, Database d, DeliveryPerson del) {

		super(s);
		dbase = d;
		dp = del;

		Container content = getContentPane();
		getContentPane().setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(72, 99, 106, 26);

		getContentPane().add(lblNewLabel);
		lblAccountsInformation.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
		lblAccountsInformation.setBounds(122, 44, 265, 26);

		getContentPane().add(lblAccountsInformation);

		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(72, 146, 106, 26);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Delivery Area");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(72, 201, 120, 26);
		getContentPane().add(lblNewLabel_2);

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
		// final Database dbase=new Database();

		JButton btnNewButton = new JButton("Create Delivery Person");
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( textField.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField.getText());
					
				}
				
				if ( textField_1.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(textField_1.getText());
					
				}
				
				if ( textField_2.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your delivery area should consist of input letters only.");
					System.out.println(textField_2.getText());
					
				}
				
				else {
				dp = new DeliveryPerson(textField.getText(), textField_1.getText(), textField_2.getText(), dbase);
				boolean res = false;

				res = dp.insertDeliveryPerson();

				if (res == true) {
					JOptionPane.showMessageDialog(null, "Insert Successful");
				} else {
					JOptionPane.showMessageDialog(null, "Insert Failed");
				}
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(55, 364, 187, 40);
		getContentPane().add(btnNewButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code to open the main menu
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
		Font f = new Font("TimesRoman", Font.BOLD, 20);
		refresh();
		setSize(561, 485);
		setVisible(true);
	}

	public void refresh() {
	}

	public void actionPerformed(ActionEvent e) {

		Object target = e.getSource();
	}
}