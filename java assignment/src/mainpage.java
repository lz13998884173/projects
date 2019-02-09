import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class mainpage extends JFrame  {
	private static String[][] datas = {};
    private static String[] titles = { "Flight_ID", "Flight_from","Flight_Destination","Status"};

	private JFrame frame;
	private JTextField Flight_IDText;
	private JTextField Flight_fromText;
	private JTextField Flight_DestinationText;
	private JTextField StatusText;
	private JButton InsertButton=new JButton("Insert");
	private JButton DeleteButton=new JButton("Insert");
	//private JButton ExportButton=new JButton("Insert");
	private JButton ClearButton=new JButton("Insert");
	private JPanel p1=new JPanel();
	private JLabel l1=new JLabel();
	private static BinaryTree bstree;
	private static HashTable Ht;
	private static DefaultTableModel model = new DefaultTableModel(datas, titles);
	private JTable table=new JTable(model);
	private static ArrayList<String[]>ta=new ArrayList<String[]>();
	private static boolean change=true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage window = new mainpage();
					window.frame.setVisible(true);
					
					bstree=new BinaryTree();
					bstree.insert(1,"Dublin","NEW YORK", "normal");
					bstree.insert(2,"ShangHai","London", "normal");
					bstree.insert(3,"BeiJing","Dublin", "delay");
					bstree.insert(4,"Tokyo","Paris", "delay");
					bstree.insert(5,"Washington","Berlin", "normal");
					
					Ht=new HashTable();
					Ht.insert(5,"Dublin","NEW YORK", "normal");
					Ht.insert(4,"ShangHai","London", "normal");
					Ht.insert(3,"BeiJing","Dublin", "delay");
					Ht.insert(2,"Tokyo","Paris", "delay");
					Ht.insert(1,"Washington","Berlin", "normal");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainpage() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		model.addRow(new String[] {"Flight_ID", "Flight_from","Flight_Destination","Status"});
		JButton ExportButton = new JButton("Export");
		ExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showall();
			}
		});
		ExportButton.setBounds(392, 275, 93, 23);
		frame.getContentPane().add(ExportButton);
		
		JButton ClearButton_1 = new JButton("Clear");
		ClearButton_1.setBounds(200, 221, 93, 23);
		frame.getContentPane().add(ClearButton_1);
		ClearButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Flight_IDText.setText("");
				Flight_fromText.setText("");
				Flight_DestinationText.setText("");
				StatusText.setText("");
			}
		});
		table.setBounds(300, 30, 307, 208);
		frame.getContentPane().add(table);
		
		JButton DeleteButton_1 = new JButton("Delete");
		DeleteButton_1.setBounds(197, 164, 93, 23);
		frame.getContentPane().add(DeleteButton_1);
		
		
		
		
		DeleteButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id=Integer.parseInt(Flight_IDText.getText());
					if(bstree.findKey(id)==null||Ht.search(id)==null){
		 		    	JOptionPane.showMessageDialog(null,"flight not existed","failed", 1);
		 		    }
					else {
				bstree.delete(id);
				Ht.delete(id);
				
				
				JOptionPane.showMessageDialog(DeleteButton_1,"flight delete successful", "successful", 1);
					}
				}catch(Exception e1) {
					e1.printStackTrace();
					
				}	
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Flight_id");
		lblNewLabel.setBounds(22, 40, 74, 35);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("From");
		lblNewLabel_1.setBounds(22, 103, 74, 22);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Destination");
		lblNewLabel_2.setBounds(0, 164, 114, 22);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(22, 221, 66, 23);
		frame.getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 14));
		
		JButton BStreeSearchButton = new JButton("BStreeSearch");
		BStreeSearchButton.setBounds(74, 354, 114, 29);
		frame.getContentPane().add(BStreeSearchButton);
		BStreeSearchButton.setEnabled(false);
		BStreeSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				int m=Integer.parseInt(Flight_IDText.getText());
				
					Node h=bstree.findKey(m);
					if(h==null) {
						
						JOptionPane.showMessageDialog(BStreeSearchButton,"No that flight", "Sorry", 1);
					}
					else {
				String[] result=bstree.findKey(m).getNode();
				//if(result==null)JOptionPane.showMessageDialog(HashSearchButton,"No that flight", "Sorry", 1);
				
					model.setRowCount(1);
					model.addRow(result);
					Flight_IDText.setText(result[0]);
					Flight_fromText.setText(result[1]);
					Flight_DestinationText.setText(result[2]);
					StatusText.setText(result[3]);	
					}
				
				
			}
		});
		

		
		JButton InsertButton_1 = new JButton("Insert");
		InsertButton_1.setBounds(197, 46, 93, 23);
		frame.getContentPane().add(InsertButton_1);
		InsertButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int id=Integer.parseInt(Flight_IDText.getText());
	 		     String from= Flight_fromText.getText();
	 		    String to=Flight_DestinationText.getText();
	 		    String status=StatusText.getText();
	 		    if(bstree.findKey(Integer.parseInt(Flight_IDText.getText()))!=null||Ht.search(Integer.parseInt(Flight_IDText.getText()))!=null){
	 		    	JOptionPane.showMessageDialog(null,"flight existed","failed", 1);
	 		    }
	 		    else {
	 		   bstree.insert(id, from, to, status);
	 		   Ht.insert(id, from, to, status);
	 		  JOptionPane.showMessageDialog(null,"insert successful","successful", 1);
	 		    }
			}
		});
		
		JButton HashSearchButton = new JButton("HashSearch");
		HashSearchButton.setBounds(237, 354, 108, 26);
		frame.getContentPane().add(HashSearchButton);
		HashSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				int	m=Integer.parseInt(Flight_IDText.getText());
				
					HashNode h=Ht.search(m);
					
					
					if(h==null) {
						System.out.println("null value");
						JOptionPane.showMessageDialog(HashSearchButton,"No that flight", "Sorry", 1);
					}
					else {
				String[] result=h.getNode();
				//if(result==null)JOptionPane.showMessageDialog(HashSearchButton,"No that flight", "Sorry", 1);
				
					model.setRowCount(1);
					model.addRow(result);
					Flight_IDText.setText(result[0]);
					Flight_fromText.setText(result[1]);
					Flight_DestinationText.setText(result[2]);
					StatusText.setText(result[3]);	
					}
				
			
			}
		});
		JRadioButton rdbtnNewRadioButton_hash = new JRadioButton("Hash\r\n");
		rdbtnNewRadioButton_hash.setBounds(237, 305, 121, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_hash);
		rdbtnNewRadioButton_hash.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_BS = new JRadioButton("BStree");
		rdbtnNewRadioButton_BS.setBounds(74, 305, 121, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_BS);
		rdbtnNewRadioButton_BS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashSearchButton.setEnabled(false);
				BStreeSearchButton.setEnabled(true);
				rdbtnNewRadioButton_hash.setSelected(false);
				change=false;
				
			}
		});
		
		
		
		rdbtnNewRadioButton_hash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashSearchButton.setEnabled(true);
				BStreeSearchButton.setEnabled(false);
				rdbtnNewRadioButton_BS.setSelected(false);
				change=true;
				
			}
		});
		
		Flight_fromText = new JTextField();
		Flight_fromText.setBounds(94, 104, 94, 21);
		frame.getContentPane().add(Flight_fromText);
		Flight_fromText.setColumns(10);
		
		Flight_IDText = new JTextField();
		Flight_IDText.setBounds(94, 46, 94, 21);
		frame.getContentPane().add(Flight_IDText);
		Flight_IDText.setColumns(10);
		
		Flight_DestinationText = new JTextField();
		Flight_DestinationText.setBounds(94, 165, 94, 21);
		frame.getContentPane().add(Flight_DestinationText);
		Flight_DestinationText.setColumns(10);
		
		StatusText = new JTextField();
		StatusText.setBounds(94, 222, 94, 21);
		frame.getContentPane().add(StatusText);
		StatusText.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 620, 427);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("src/0010.jpg"));
		Icon icon=new ImageIcon("src/0010.jpg"); 
		JLabel Jlbl = new JLabel();
		
		Jlbl.setFont(new Font("宋体", Font.PLAIN, 16));
		Jlbl.setIcon(icon);
		Jlbl.setBounds(0, 0, 620,427);
		panel.add(Jlbl,new Integer(Integer.MIN_VALUE));
		
		pack();
		//Jlbl.setBounds(0, 0, 593, 427);
		panel.add(Jlbl);
		
		
		
	}
	public void showall(){
		if(change){
			if(Ht.head!=null){
				model.setRowCount(1);
				ta=Ht.Table_print();
				Iterator<String[]>it=ta.iterator();
				while(it.hasNext()){
					model.addRow(it.next());
				}
				JOptionPane.showMessageDialog(null,"There are "+ta.size()+" record(s)","Finish", 1);	
				ta.clear();		
			}else JOptionPane.showMessageDialog(null,"This is empty table","Sorry", 1);		
		}
		else{
			if(bstree.root!=null){
				model.setRowCount(1);
				ta=bstree.Tree_print();
				Iterator<String[]>it=ta.iterator();
				while(it.hasNext()){
					model.addRow(it.next());
				}
				JOptionPane.showMessageDialog(null,"This is "+ta.size()+" record(s)","Finish", 1);	
				ta.clear();	
			}else JOptionPane.showMessageDialog(null,"This is empty table","Sorry", 1);
		}
	}
}
