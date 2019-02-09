import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class JDBCMainWindowContent extends JInternalFrame implements ActionListener
{	
	String cmd = null;

	// DB Connectivity Attributes
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private Container content;
	
	private JPanel detailsPanel;
	private JPanel exportButtonPanel;
	//private JPanel exportConceptDataPanel;
	private JScrollPane dbContentsPanel;

	private Border lineBorder;

	private JLabel IDLabel=new JLabel("ID:                 ");
	private JLabel FirstNameLabel=new JLabel("FirstName:               ");
	private JLabel LastNameLabel=new JLabel("LastName:      ");
	private JLabel AgeLabel=new JLabel("Age:        ");
	private JLabel GenderLabel=new JLabel("Gender:                 ");
	private JLabel PositionLabel=new JLabel("Position:               ");
	private JLabel DepartmentLabel=new JLabel("Department:      ");
	private JLabel RateLabel=new JLabel("Rate:      ");
	private JLabel HoursLabel=new JLabel("Hours:        ");

	private JTextField IDTF= new JTextField(10);
	private JTextField FirstNameTF=new JTextField(10);
	private JTextField LastNameTF=new JTextField(10);
	private JTextField AgeTF=new JTextField(10);
	private JTextField GenderTF=new JTextField(10);
	private JTextField PositionTF=new JTextField(10);
	private JTextField DepartmentTF=new JTextField(10);
	private JTextField RateTF=new JTextField(10);
	private JTextField HoursTF=new JTextField(10);


	private static QueryTableModel TableModel = new QueryTableModel();
	//Add the models to JTabels
	private JTable TableofDBContents=new JTable(TableModel);
	//Buttons for inserting, and updating members
	//also a clear button to clear details panel
	private JButton updateButton = new JButton("Update");
	private JButton insertButton = new JButton("Insert");
	private JButton exportButton  = new JButton("Export");
	private JButton deleteButton  = new JButton("Delete");
	private JButton clearButton  = new JButton("Clear");

	private JButton  NumLectures = new JButton("NumLecturesForDepartment:");
	private JTextField NumLecturesTF  = new JTextField(12);
	private JButton avgAgeDepartment  = new JButton("AvgAgeForDepartment");
	private JTextField avgAgeDepartmentTF  = new JTextField(12);
	private JButton ListAllDepartments  = new JButton("ListAllDepartments");
	private JButton ListAllPositions  = new JButton("ListAllPositions");
	private JMenuBar menuBar=new JMenuBar();
	private JMenu mnNewMenu = new JMenu("file");
	private JMenuItem mntmNewMenuItem = new JMenuItem("student");
	private final JTextField listDepartmentTF = new JTextField(12);
	private final JTextField listPositionTF = new JTextField(12);



	public JDBCMainWindowContent( String aTitle)
	{	
		//setting up the GUI
		super(aTitle, false,false,false,false);
		setEnabled(true);

		initiate_db_conn();
		//add the 'main' panel to the Internal Frame
		content=getContentPane();
		content.setLayout(null);
		content.setBackground(Color.lightGray);
		lineBorder = BorderFactory.createEtchedBorder(15, Color.red, Color.black);

		//setup details panel and add the components to it
		detailsPanel=new JPanel();
		detailsPanel.setBackground(Color.lightGray);
		detailsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "CRUD Actions"));
		detailsPanel.setLayout(null);
		IDLabel.setBounds(6, 18, 174, 25);

		detailsPanel.add(IDLabel);			
		IDTF.setBounds(180, 18, 174, 25);
		detailsPanel.add(IDTF);
		FirstNameLabel.setBounds(6, 43, 174, 25);
		detailsPanel.add(FirstNameLabel);		
		FirstNameTF.setBounds(180, 43, 174, 25);
		detailsPanel.add(FirstNameTF);
		LastNameLabel.setBounds(6, 68, 174, 25);
		detailsPanel.add(LastNameLabel);		
		LastNameTF.setBounds(180, 68, 174, 25);
		detailsPanel.add(LastNameTF);
		AgeLabel.setBounds(6, 93, 174, 25);
		detailsPanel.add(AgeLabel);	
		AgeTF.setBounds(180, 93, 174, 25);
		detailsPanel.add(AgeTF);
		GenderLabel.setBounds(6, 118, 174, 25);
		detailsPanel.add(GenderLabel);		
		GenderTF.setBounds(180, 118, 174, 25);
		detailsPanel.add(GenderTF);
		PositionLabel.setBounds(6, 143, 174, 25);
		detailsPanel.add(PositionLabel);
		PositionTF.setBounds(180, 143, 174, 25);
		detailsPanel.add(PositionTF);
		DepartmentLabel.setBounds(6, 168, 174, 25);
		detailsPanel.add(DepartmentLabel);
		DepartmentTF.setBounds(180, 168, 174, 25);
		detailsPanel.add(DepartmentTF);
		RateLabel.setBounds(6, 193, 174, 25);
		detailsPanel.add(RateLabel);
		RateTF.setBounds(180, 193, 174, 25);
		detailsPanel.add(RateTF);
		HoursLabel.setBounds(6, 218, 174, 25);
		detailsPanel.add(HoursLabel);
		HoursTF.setBounds(180, 218, 174, 25);
		detailsPanel.add(HoursTF);

		//setup details panel and add the components to it
		exportButtonPanel=new JPanel();
		exportButtonPanel.setBackground(Color.lightGray);
		exportButtonPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Export Data"));
		avgAgeDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		exportButtonPanel.setSize(517, 309);
		exportButtonPanel.setLocation(3, 296);
		content.add(exportButtonPanel);
		GroupLayout gl_exportButtonPanel = new GroupLayout(exportButtonPanel);
		gl_exportButtonPanel.setHorizontalGroup(
			gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addComponent(NumLectures, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addComponent(NumLecturesTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addComponent(avgAgeDepartment, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addComponent(avgAgeDepartmentTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ListAllDepartments, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(ListAllPositions, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(listPositionTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(listDepartmentTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)))
		);
		gl_exportButtonPanel.setVerticalGroup(
			gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(NumLectures, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumLecturesTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(avgAgeDepartment, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(avgAgeDepartmentTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ListAllDepartments, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_exportButtonPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(ListAllPositions, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_exportButtonPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(listPositionTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addComponent(listDepartmentTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
		);
		exportButtonPanel.setLayout(gl_exportButtonPanel);

		
		setJMenuBar(menuBar);
		
		
		menuBar.add(mnNewMenu);
		
		
		
		mnNewMenu.add(mntmNewMenuItem);
		setVisible(true);
		
		insertButton.setSize(100, 30);
		updateButton.setSize(100, 30);
		exportButton.setSize (100, 30);
		deleteButton.setSize (100, 30);
		clearButton.setSize (100, 30);

		insertButton.setLocation(370, 10);
		updateButton.setLocation(370, 110);
		exportButton.setLocation (370, 160);
		deleteButton.setLocation (370, 60);
		clearButton.setLocation (370, 210);

		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		exportButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);

		this.ListAllDepartments.addActionListener(this);
		this.NumLectures.addActionListener(this);
		this.ListAllPositions.addActionListener(this);
		this.avgAgeDepartment.addActionListener(this);
        this.mntmNewMenuItem.addActionListener(this);

		content.add(insertButton);
		content.add(updateButton);
		content.add(exportButton);
		content.add(deleteButton);
		content.add(clearButton);


		TableofDBContents.setPreferredScrollableViewportSize(new Dimension(900, 300));

		dbContentsPanel=new JScrollPane(TableofDBContents,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dbContentsPanel.setBackground(Color.lightGray);
		dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Content"));

		detailsPanel.setSize(360, 300);
		detailsPanel.setLocation(3,0);
		dbContentsPanel.setSize(700, 300);
		dbContentsPanel.setLocation(477, 0);

		content.add(detailsPanel);
		content.add(dbContentsPanel);

		setSize(982,645);
		
		

		TableModel.refreshFromDB(stmt,"details");
	}

	public void initiate_db_conn()
	{
		try
		{
			// Load the JConnector Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Specify the DB Name
			String url="jdbc:mysql://localhost:3306/BEng_Assign";
			// Connect to DB using DB URL, Username and password
			con = DriverManager.getConnection(url, "root", "admin");
			//Create a generic statement which is passed to the TestInternalFrame1
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to connect to database\n"+e.getMessage());
		}
	}

	//event handling 
	public void actionPerformed(ActionEvent e)
	{
		Object target=e.getSource();
		if (target == clearButton)
		{
			IDTF.setText("");
			FirstNameTF.setText("");
			LastNameTF.setText("");
			AgeTF.setText("");
			GenderTF.setText("");
			PositionTF.setText("");
			DepartmentTF.setText("");
			RateTF.setText("");
			HoursTF.setText("");

		}

		if (target == insertButton)
		{		 
			try
			{
				String updateTemp ="INSERT INTO details VALUES("+
				null +",'"+FirstNameTF.getText()+"','"+LastNameTF.getText()+"',"+AgeTF.getText()+",'"+GenderTF.getText()+"','"
				+PositionTF.getText()+"','"+DepartmentTF.getText()+"',"+RateTF.getText()+","+HoursTF.getText()+");";

				stmt.executeUpdate(updateTemp);

			}
			catch (SQLException sqle)
			{
				System.err.println("Error with  insert:\n"+sqle.toString());
			}
			finally
			{
				TableModel.refreshFromDB(stmt,"details");
			}
		}
		if (target == deleteButton)
		{

			try
			{
				String updateTemp ="DELETE FROM details WHERE id = "+IDTF.getText()+";"; 
				stmt.executeUpdate(updateTemp);

			}
			catch (SQLException sqle)
			{
				System.err.println("Error with delete:\n"+sqle.toString());
			}
			finally
			{
				TableModel.refreshFromDB(stmt,"details");
			}
		}
		if (target == updateButton)
		{	 	
			try
			{ 			
				String updateTemp ="UPDATE details SET " +
				"firstName = '"+FirstNameTF.getText()+
				"', lastName = '"+LastNameTF.getText()+
				"', age = "+AgeTF.getText()+
				", gender ='"+GenderTF.getText()+
				"', position = '"+PositionTF.getText()+
				"', department = '"+DepartmentTF.getText()+
				"', rate = "+RateTF.getText()+
				", hours = "+HoursTF.getText()+
				" where id = "+IDTF.getText();


				stmt.executeUpdate(updateTemp);
				//these lines do nothing but the table updates when we access the db.
				rs = stmt.executeQuery("SELECT * from details ");
				rs.next();
				rs.close();	
			}
			catch (SQLException sqle){
				System.err.println("Error with  update:\n"+sqle.toString());
			}
			finally{
				TableModel.refreshFromDB(stmt,"details");
			}
		}  if(target==this.exportButton) {
			cmd="select * from details";
			try {
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
				
			}catch(Exception e1) {
				e1.printStackTrace();
				
			}
			
		}
		if(target==this.mntmNewMenuItem) {
			//JDBCMainWindow JDBCMainWindow= new JDBCMainWindow();
			//JDBCMainWindow.aWindowContent.setVisible(false);
			JDBCStudentContent student=new JDBCStudentContent("student");
			//JDBCMainWindowContent window=new JDBCMainWindowContent("window");
			//new JDBCStudentContent("student");
			
			
			//this.setVisible(false);
			this.getContentPane().removeAll();
			getContentPane().add( student );
			//content.add( student );
			setSize( 1200, 1600 );
			//this.getContentPane().setVisible(false);
			//getContentPane().add(student);
			//student.setVisible(true);
			
		}

		

		if(target == this.ListAllDepartments){

			cmd = "select distinct department from details;";

			try{					
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}
			catch(Exception e1){e1.printStackTrace();}

		}

		if(target == this.NumLectures){
			String deptName = this.NumLecturesTF.getText();

			cmd = "select department, count(*) "+  "from details " + "where department = '"  +deptName+"';";

			System.out.println(cmd);
			try{					
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}
			catch(Exception e1){e1.printStackTrace();}

		} 
		if(target==this.avgAgeDepartment) {
			String deptName = this.avgAgeDepartmentTF.getText();
			cmd="select avg(age) "+"from details"+" where department='"+deptName+"';";
			System.out.println(cmd);
			
			try {
				rs=stmt.executeQuery(cmd);
				writeToFile(rs);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		if(target == this.ListAllPositions){

			cmd = "select distinct position from details";
			System.out.println(cmd);
			try{					
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}
			catch(Exception e1){e1.printStackTrace();}

		} 
		
	}


	private void writeToFile(ResultSet rs){
		try{
			System.out.println("In writeToFile");
			FileWriter outputFile = new FileWriter("Sheila.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for(int i=0;i<numColumns;i++){
				printWriter.print(rsmd.getColumnLabel(i+1)+",");
			}
			printWriter.print("\n");
			while(rs.next()){
				for(int i=0;i<numColumns;i++){
					printWriter.print(rs.getString(i+1)+",");
				}
				printWriter.print("\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
}
