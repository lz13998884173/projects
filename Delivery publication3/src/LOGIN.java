
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

class LOGIN extends JFrame implements ActionListener {

	private final JLabel lblAccountsInformation = new JLabel("Newapaper Delivery Login");
	private final JLabel lblNewLabel = new JLabel("Username");
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JFrame frame;

	public LOGIN(String s) {
		super(s);
		Container content = getContentPane();
		getContentPane().setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(52, 64, 80, 25);

		getContentPane().add(lblNewLabel);
		lblAccountsInformation.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		lblAccountsInformation.setBounds(74, 13, 209, 38);

		getContentPane().add(lblAccountsInformation);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(52, 111, 82, 15);
		getContentPane().add(lblNewLabel_1);

		usernameTextField = new JTextField();
		usernameTextField.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameTextField.setBounds(166, 66, 131, 21);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameTextField.getText().equals("admin") && passwordField.getText().equals("root")) 
				{
					//Code to open the main menu
					MainMenu main = new MainMenu();
					main.frame.setVisible(true);
					
					//Code to close current window
					setVisible(false); 
					dispose(); 
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid entry details. Try again");
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		btnNewButton.setBounds(63, 204, 89, 35);
		getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(166, 108, 131, 21);
		getContentPane().add(passwordField);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnClose.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		btnClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClose.setBounds(202, 204, 89, 35);
		getContentPane().add(btnClose);
		Font f = new Font("TimesRoman", Font.BOLD, 20);
		refresh();
		setSize(362, 427);
		setVisible(true);
	}

	public void refresh() 
	{
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object target = e.getSource();
	}
}