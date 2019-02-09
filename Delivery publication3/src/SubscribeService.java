
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

class SubscribeService extends JFrame implements ActionListener{
	private Database dbase = null;
	private Subscription subscribe = null;
	private JTextField dayFrom;
	private JTextField mthFrom;
	private JTextField yearFrom;
	private JTextField dayTo;
	private JTextField mthTo;
	private JTextField yearTo;
	
	
	public SubscribeService(String s,Subscription sub,  Database db){
		super(s);
		dbase = db;
		this.subscribe = sub;
		Container content=getContentPane();
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("From");
		lblNewLabel_1.setBounds(27, 140, 54, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setBounds(27, 191, 54, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblD = new JLabel("D");
		lblD.setBounds(156, 140, 26, 15);
		getContentPane().add(lblD);
		
		JLabel lblNewLabel_3 = new JLabel("M");
		lblNewLabel_3.setBounds(247, 140, 54, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Y");
		lblNewLabel_4.setBounds(333, 140, 54, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel day = new JLabel("D");
		day.setBounds(156, 191, 54, 15);
		
		getContentPane().add(day);
		
		JLabel lblNewLabel_6 = new JLabel("M");
		lblNewLabel_6.setBounds(247, 191, 19, 15);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Y");
		lblNewLabel_7.setBounds(333, 191, 26, 15);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblSubscriptionInformation = new JLabel("Subscription Information");
		lblSubscriptionInformation.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
		lblSubscriptionInformation.setBounds(225, 60, 212, 26);
		getContentPane().add(lblSubscriptionInformation);
		
		JButton btnCreateSubscription = new JButton("Create Subscription");

		btnCreateSubscription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreateSubscription.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCreateSubscription.setBounds(89, 419, 178, 40);
		getContentPane().add(btnCreateSubscription);
		
		dayFrom = new JTextField();
		dayFrom.setColumns(10);
		dayFrom.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		dayFrom.setBounds(89, 135, 54, 25);
		getContentPane().add(dayFrom);
		
		mthFrom = new JTextField();
		mthFrom.setColumns(10);
		mthFrom.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		mthFrom.setBounds(182, 135, 54, 25);
		getContentPane().add(mthFrom);
		
		yearFrom = new JTextField();
		yearFrom.setColumns(10);
		yearFrom.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		yearFrom.setBounds(271, 135, 54, 25);
		getContentPane().add(yearFrom);
		
		dayTo = new JTextField();
		dayTo.setColumns(10);
		dayTo.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		dayTo.setBounds(89, 181, 54, 25);
		getContentPane().add(dayTo);
		
		mthTo = new JTextField();
		mthTo.setColumns(10);
		mthTo.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		mthTo.setBounds(181, 181, 54, 25);
		getContentPane().add(mthTo);
		
		yearTo = new JTextField();
		yearTo.setColumns(10);
		yearTo.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		yearTo.setBounds(271, 181, 54, 25);
		getContentPane().add(yearTo);
		
		JCheckBox independentCheck = new JCheckBox("Irish Independent");
		independentCheck.setBounds(63, 292, 134, 25);
		getContentPane().add(independentCheck);
		
		JCheckBox mirrorCheck = new JCheckBox("Irish Mirror");
		mirrorCheck.setBounds(225, 292, 134, 25);
		getContentPane().add(mirrorCheck);
		
		JCheckBox leaderCheck = new JCheckBox("Longford Leader");
		leaderCheck.setBounds(63, 340, 134, 25);
		getContentPane().add(leaderCheck);
		
		JCheckBox topicCheck = new JCheckBox("Westmeath Topic");
		topicCheck.setBounds(225, 340, 134, 25);
		getContentPane().add(topicCheck);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//Code to open the main menu			
				MainMenu main = new MainMenu();
				main.frame.setVisible(true);
				setVisible(false); 
				dispose(); 
			}
		});
		
		btnCreateSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String dateFrom = dayFrom.getText() + "/" + mthFrom.getText() + "/" + yearFrom.getText();
				String dateTo = dayTo.getText() + "/" + mthTo.getText() + "/" + yearTo.getText();
				
				System.out.println(dateFrom);
				System.out.println(dateTo);
				
				boolean independent, mirror, leader, topic, result;
				
				independent = independentCheck.isSelected();
				mirror = mirrorCheck.isSelected();
				leader = leaderCheck.isSelected();
				topic = topicCheck.isSelected();
				
				
				subscribe = new Subscription(dateFrom,dateTo,independent,mirror,leader,topic,dbase);
				result = subscribe.insertSubscription();
				
				if(result == true)
				{
					JOptionPane.showMessageDialog(null, "Subscription Complete");
					setVisible(false); 
					dispose(); 

				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert Failed");
				}
			}
		});
		
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setBounds(335, 419, 178, 40);
		getContentPane().add(btnCancel);
		Font f=new Font("TimesRoman", Font.BOLD,20);
		refresh();
		setSize(655,530);    setVisible(true);}
	
	public void refresh(){
	}
	
	public void actionPerformed(ActionEvent e){
        
	  	Object target=e.getSource();
	 }
}