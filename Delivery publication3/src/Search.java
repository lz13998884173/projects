import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Search {

	private static String[][] datas = {};
    private static String[] titles = { "ID", "firstName","surName","PhoneNum","Address"};
	JFrame frame;
	private JTextField IdText;
	private JTextField FirstNameText;
	private JTextField SurNameText;
	private Database dbase = null;
	private Customers c= null;
	private JTable table_1;
	private static DefaultTableModel model = new DefaultTableModel(datas, titles);
	private static DefaultTableModel mode2 = new DefaultTableModel(datas, titles);
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Search(String s,Database d,Customers cust) {
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
		frame.setBounds(100, 100, 702, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		panel.setForeground(Color.BLACK);
		panel.setBorder(BorderFactory.createTitledBorder( "Search"));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(14, 59, 245, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSearchbyid = new JLabel("SearchByID:                 ");
		lblSearchbyid.setBounds(6, 23, 233, 26);
		panel.add(lblSearchbyid);
		
		IdText = new JTextField(10);
		IdText.setBounds(6, 49, 233, 26);
		panel.add(IdText);
		
		JLabel lblSearchbyname = new JLabel("SearchByName:               ");
		lblSearchbyname.setBounds(6, 155, 233, 26);
		panel.add(lblSearchbyname);
		
		FirstNameText = new JTextField(10);
		FirstNameText.setBounds(92, 194, 147, 26);
		panel.add(FirstNameText);
		
		JButton btnSearch = new JButton("ID_Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c=new Customers("ss","ss","ss","ss",dbase);
				try {
					int m=-1;
					m=Integer.parseInt(IdText.getText());
					if(m!=-1) {
					ResultSet rs;
					rs=c.searchCustomerID(Integer.parseInt(IdText.getText()));
					
					String[] cust_details = new String[5];
					int count=0;

						cust_details[count]=rs.getString("Cust_ID");
						cust_details[count+1]=rs.getString("Cust_FirstName");
						cust_details[count+2]=rs.getString("Cust_Surname");
						cust_details[count+3]=rs.getString("Cust_PhoneNo");
						cust_details[count+4]=rs.getString("Cust_Address");
						count++;
					
					/*cust_details[0] = rs.getString(1);
					cust_details[1] = rs.getString(2);
					cust_details[2] = rs.getString(3);
					cust_details[3] = rs.getString(4);
					cust_details[4] = rs.getString(5);*/
					for(int i=0;i<=4;i++) {
						System.out.println(cust_details[i]);
					}
					model.setRowCount(1);
					model.addRow(cust_details);
						

					}
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(6, 99, 113, 27);
		panel.add(btnSearch);
		
		JButton button = new JButton("Name_Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( FirstNameText.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(FirstNameText.getText());
					
				}
				
				if ( SurNameText.getText().matches(".*\\d+.*"))
				{  
					JOptionPane.showMessageDialog(null, "Invaild. Your name should consist of input letters only.");
					System.out.println(SurNameText.getText());
					
				}
				else
				{
				c=new Customers("ss","ss","ss","ss",dbase);
				try {
					
					ResultSet rs;
					rs=c.searchCustomerName(FirstNameText.getText(), SurNameText.getText());


					String[] cust_details = new String[10];
					int count=0;
					int row_count = 1;
					while(rs.next())
					{
						cust_details[count]=rs.getString("Cust_ID");
						cust_details[count+1]=rs.getString("Cust_FirstName");
						cust_details[count+2]=rs.getString("Cust_Surname");
						cust_details[count+3]=rs.getString("Cust_PhoneNo");
						cust_details[count+4]=rs.getString("Cust_Address");
//						count+=5;
						mode2.setRowCount(row_count);
						mode2.addRow(cust_details);
						row_count++;
					}
					/*while(rs.next()){
						count=count+5;
						cust_details[count]=rs.getString("Cust_ID");
						cust_details[count+1]=rs.getString("Cust_FirstName");
						cust_details[count+2]=rs.getString("Cust_Surname");
						cust_details[count+3]=rs.getString("Cust_PhoneNo");
						cust_details[count+4]=rs.getString("Cust_Address");
						//mode2.setRowCount(1);
						//mode2.addRow(cust_details);
						System.out.println("test");
					}*/
					

//					mode2.setRowCount(2);
//					mode2.addRow(cust_details);
				
					for(int i=0;i<=9;i++) {
						System.out.println(cust_details[i]);
					}
					
						
					
					
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			}
		});
		button.setBounds(4, 279, 113, 27);
		panel.add(button);
		
		SurNameText = new JTextField(10);
		SurNameText.setBounds(92, 239, 147, 26);
		panel.add(SurNameText);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setBounds(6, 198, 72, 18);
		panel.add(lblFirstname);
		
		JLabel lblSurname = new JLabel("SurName");
		lblSurname.setBounds(6, 243, 72, 18);
		panel.add(lblSurname);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("ËÎÌו", Font.BOLD, 20));
		lblSearch.setBounds(185, 0, 116, 62);
		frame.getContentPane().add(lblSearch);
		
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
		button_1.setBounds(107, 396, 152, 27);
		frame.getContentPane().add(button_1);
		
		model.addRow(new String[] {"ID", "firstName","surName","PhoneNum","Address"});
		mode2.addRow(new String[] {"ID", "firstName","surName","PhoneNum","Address"});
		table_1 = new JTable(model);
		table_1.setBounds(269, 10, 407, 164);
		frame.getContentPane().add(table_1);
		
		table = new JTable(mode2);
		table.setBounds(269, 202, 407, 208);
		frame.getContentPane().add(table);
	}
}
