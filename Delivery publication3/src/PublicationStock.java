
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PublicationStock extends JFrame implements ActionListener{
	
	//private Team t =new Team("Manu",0,0);
	private final JLabel lblAccountsInformation = new JLabel("Publication Stock Search");
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public PublicationStock(String s){
		super(s);
		Container content=getContentPane();
		getContentPane().setLayout(null);
		lblAccountsInformation.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblAccountsInformation.setBounds(159, 10, 256, 25);
		
		getContentPane().add(lblAccountsInformation);
		
		JLabel lblNewLabel = new JLabel("Publication ID");
		lblNewLabel.setBounds(135, 48, 110, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Publication Stock");
		lblNewLabel_1.setBounds(135, 95, 110, 15);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(269, 92, 66, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("=====================================================================================");
		lblNewLabel_2.setBounds(10, 224, 514, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Inserting a new publication");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_3.setBounds(159, 240, 256, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New ID");
		lblNewLabel_4.setBounds(95, 307, 54, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New Stock");
		lblNewLabel_5.setBounds(85, 346, 93, 15);
		getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.setBounds(139, 136, 93, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.setBounds(256, 136, 93, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Last");
		btnNewButton_2.setBounds(378, 136, 93, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add");
		btnNewButton_3.setBounds(196, 181, 93, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Remove");
		btnNewButton_4.setBounds(329, 181, 93, 23);
		getContentPane().add(btnNewButton_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(209, 304, 212, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(209, 342, 66, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Insert");
		btnNewButton_5.setBounds(196, 393, 93, 23);
		getContentPane().add(btnNewButton_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(269, 45, 202, 21);
		getContentPane().add(comboBox);
		Font f=new Font("TimesRoman", Font.BOLD,20);
		refresh();
		setSize(530,502);    setVisible(true);}
	
	public void refresh(){
	}
	
	public void actionPerformed(ActionEvent e){
        
	  	Object target=e.getSource();
	 }
}