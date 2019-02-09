import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

@SuppressWarnings("serial")
public class JDBCStudentContent extends JInternalFrame implements ActionListener
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
	private JScrollPane gradeContent;

	private Border lineBorder;

	private JLabel IDLabel=new JLabel("ID:                 ");
	private JLabel FirstNameLabel=new JLabel("FirstName:               ");
	private JLabel LastNameLabel=new JLabel("LastName:      ");
	private JLabel AgeLabel=new JLabel("Age:        ");
	private JLabel GenderLabel=new JLabel("Gender:                 ");
	private JLabel SubjectLabel=new JLabel("Subject:               ");
	private JLabel yearLabel=new JLabel("Year:    ");
	private JLabel GradesLabel=new JLabel("Grades:      ");

	private JTextField IDTF= new JTextField(10);
	private JTextField FirstNameTF=new JTextField(10);
	private JTextField LastNameTF=new JTextField(10);
	private JTextField AgeTF=new JTextField(10);
	private JTextField GenderTF=new JTextField(10);
	private JTextField SubjectTF=new JTextField(10);
	private JTextField yearTF=new JTextField(10);
	private JTextField GradesTF=new JTextField(10);


	private static QueryTableModel TableModel = new QueryTableModel();
	private static QueryTableModel TableMode2 = new QueryTableModel();
	//Add the models to JTabels
	private JTable TableofDBContents=new JTable(TableModel);
	private JTable TableofDBContents_2=new JTable(TableMode2);
	//Buttons for inserting, and updating members
	//also a clear button to clear details panel
	private JButton updateButton = new JButton("Update");
	private JButton insertButton = new JButton("Insert");
	private JButton exportButton  = new JButton("Export");
	private JButton deleteButton  = new JButton("Delete");
	private JButton clearButton  = new JButton("Clear");
	private JMenuItem lecturers=new JMenuItem("lecturers");

	private JButton  NumStudent = new JButton("numStudents for subject");
	private JTextField NumStudentsTF  = new JTextField(12);
	private JButton avggrades  = new JButton("AvgSubjectGrades");
	private JTextField avgSubjectGradesTF  = new JTextField(12);
	private JButton ListAllSubjects  = new JButton("ListAllSubject");
	private JButton ListSubjectDetails  = new JButton("ListSubjectDetails");
	private final JTextField ListSubjectDetailsTF = new JTextField(12);



	public JDBCStudentContent( String aTitle)
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
		SubjectLabel.setBounds(6, 143, 174, 25);
		detailsPanel.add(SubjectLabel);
		SubjectTF.setBounds(180, 143, 174, 25);
		detailsPanel.add(SubjectTF);
		yearLabel.setBounds(6, 168, 174, 25);
		detailsPanel.add(yearLabel);
		yearTF.setBounds(180, 168, 174, 25);
		detailsPanel.add(yearTF);
		GradesLabel.setBounds(6, 193, 174, 25);
		detailsPanel.add(GradesLabel);
		GradesTF.setBounds(180, 193, 174, 25);
		detailsPanel.add(GradesTF);


		TableofDBContents.setPreferredScrollableViewportSize(new Dimension(900, 300));
		TableofDBContents_2.setPreferredScrollableViewportSize(new Dimension(900, 300));

		
	
		//setup details panel and add the components to it
		exportButtonPanel=new JPanel();
		exportButtonPanel.setBackground(Color.lightGray);
		exportButtonPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Export Data"));
		avggrades.addActionListener(new ActionListener() {
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
					.addComponent(NumStudent, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addComponent(NumStudentsTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addComponent(avggrades, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addComponent(avgSubjectGradesTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ListAllSubjects, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(ListSubjectDetails, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
					.addComponent(ListSubjectDetailsTF, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
		);
		gl_exportButtonPanel.setVerticalGroup(
			gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_exportButtonPanel.createSequentialGroup()
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(NumStudent, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumStudentsTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(avggrades, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(avgSubjectGradesTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_exportButtonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ListAllSubjects, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_exportButtonPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(ListSubjectDetails, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_exportButtonPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(ListSubjectDetailsTF, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))))
		);
		exportButtonPanel.setLayout(gl_exportButtonPanel);

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
		lecturers.addActionListener(this);

		this.ListAllSubjects.addActionListener(this);
		this.NumStudent.addActionListener(this);
		this.ListSubjectDetails.addActionListener(this);
		this.avggrades.addActionListener(this);


		content.add(insertButton);
		content.add(updateButton);
		content.add(exportButton);
		content.add(deleteButton);
		content.add(clearButton);


		
        detailsPanel.setSize(360, 300);
		detailsPanel.setLocation(3,0);
		
		content.add(detailsPanel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 93, 21);
		detailsPanel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem lecturers = new JMenuItem("lecturers");
		mnNewMenu.add(lecturers);
		
		dbContentsPanel=new JScrollPane(TableofDBContents,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dbContentsPanel.setBackground(Color.lightGray);
		dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Content"));

		
		dbContentsPanel.setSize(700, 260);
		dbContentsPanel.setLocation(477, 0);
		content.add(dbContentsPanel);
		
		 gradeContent = new JScrollPane(TableofDBContents_2 , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		gradeContent.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		gradeContent.setBorder(BorderFactory.createTitledBorder(lineBorder,"Database Content"));
		gradeContent.setBackground(Color.LIGHT_GRAY);
		gradeContent.setBounds(545, 327, 398, 211);
		content.add(gradeContent);


		setSize(1247,645);
		setVisible(true);

		TableModel.refreshFromDB(stmt,"students");
		TableMode2.refreshFromDB(stmt,"Grades_result");
		
		
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
			SubjectTF.setText("");
			yearTF.setText("");
			GradesTF.setText("");
			

		}

		if (target == insertButton)
		{		 
			try
			{
				String updateTemp ="INSERT INTO students VALUES("+
				null +",'"+FirstNameTF.getText()+"','"+LastNameTF.getText()+"',"+AgeTF.getText()+",'"+GenderTF.getText()+"','"
				+SubjectTF.getText()+"','"+yearTF.getText()+"',"+GradesTF.getText()+");";

				stmt.executeUpdate(updateTemp);

			}
			catch (SQLException sqle)
			{
				System.err.println("Error with  insert:\n"+sqle.toString());
			}
			finally
			{
				TableModel.refreshFromDB(stmt,"students");
				TableMode2.refreshFromDB(stmt,"Grades_result");
			}
		}
		if (target == deleteButton)
		{

			try
			{
				String updateTemp ="DELETE FROM students WHERE id = "+IDTF.getText()+";"; 
				stmt.executeUpdate(updateTemp);

			}
			catch (SQLException sqle)
			{
				System.err.println("Error with delete:\n"+sqle.toString());
			}
			finally
			{
				TableModel.refreshFromDB(stmt,"students");
				TableMode2.refreshFromDB(stmt,"Grades_result");
			}
		}
		if (target == updateButton)
		{	 	
			try
			{ 			
				String updateTemp ="UPDATE students SET " +
				"firstName = '"+FirstNameTF.getText()+
				"', lastName = '"+LastNameTF.getText()+
				"', age = "+AgeTF.getText()+
				", gender ='"+GenderTF.getText()+
				"', subjects = '"+SubjectTF.getText()+
				"', years = '"+yearTF.getText()+
				"', grades = "+GradesTF.getText()+
				" where id = "+IDTF.getText();


				stmt.executeUpdate(updateTemp);
				//these lines do nothing but the table updates when we access the db.
				rs = stmt.executeQuery("SELECT * from students ");
				rs.next();
				rs.close();	
			}
			catch (SQLException sqle){
				System.err.println("Error with  update:\n"+sqle.toString());
			}
			finally{
				TableModel.refreshFromDB(stmt,"students");
				TableMode2.refreshFromDB(stmt,"Grades_result");
			}
		}
		if(target==exportButton) {

			cmd="select * from Students";
			try {
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
				
			}catch(Exception e1) {
				e1.printStackTrace();
				
			}
			
		
		}

		

		if(target == this.ListAllSubjects){

			cmd = "select distinct subjects from students;";

			try{					
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}
			catch(Exception e1){e1.printStackTrace();}

		}

		if(target == this.NumStudent){
			String subjects = this.NumStudentsTF.getText();

			cmd = "select subjects, count(*) "+  "from students " + "where subjects = '"  +subjects+"';";

			System.out.println(cmd);
			try{					
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}
			catch(Exception e1){e1.printStackTrace();}

		} 
		if(target==this.avggrades) {
			String subjectName = this.avgSubjectGradesTF.getText();
			cmd="select avg(grades) "+"from students"+" where subjects='"+subjectName+"';";
			System.out.println(cmd);
			
			try {
				rs=stmt.executeQuery(cmd);
				writeToFile(rs);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		  if(target==ListSubjectDetails){
			String subjectName=this.ListSubjectDetailsTF.getText();
			cmd="select distinct subjects , concat(students.firstName,'_ ', students.lastName) as student_name,years,grades, concat(details.firstname,' ',details.lastname) as lecturer_name from students"
					+ " inner join details"
					+ " on students.subjects=details.department and details.position=\"lecturer\" and subjects='"+subjectName+"';";
			System.out.println(cmd);
			try {
				rs= stmt.executeQuery(cmd); 	
				writeToFile(rs);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		  if(target==lecturers) {
			  JDBCMainWindowContent aWindowContent = new JDBCMainWindowContent( "lecturer");
			  this.getContentPane().removeAll();
				//getContentPane().add( aWindowContent );
				this.setVisible(false);
				//aWindowContent.getContentPane().setVisible(true);
				
			  
		  }
		
	}
	
	private void writeToFile(ResultSet rs){
		try{
			System.out.println("In writeToFile");
			FileWriter outputFile = new FileWriter("Export.csv");
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
