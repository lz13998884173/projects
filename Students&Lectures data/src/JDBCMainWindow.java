import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JDBCMainWindow extends JFrame implements ActionListener
	{
		private JMenuItem exitItem;
		//private JMenuItem student;
        static JDBCMainWindowContent aWindowContent = new JDBCMainWindowContent( "JDBC Assignment");
        private static JDBCStudentContent student=new JDBCStudentContent("student");
		public JDBCMainWindow()
		{
			// Sets the Window Title
			super( "JDBC Assignment" ); 
			
			//Setup fileMenu and its elements
			JMenuBar menuBar=new JMenuBar();
			JMenu fileMenu=new JMenu("File");
			exitItem =new JMenuItem("Exit");
			//student=new JMenuItem("student");
	
			fileMenu.add(exitItem);
			fileMenu.add(student);
			menuBar.add(fileMenu );
			setJMenuBar(menuBar);
			
			// Add a listener to the Exit Menu Item
			exitItem.addActionListener(this);
			//student.addActionListener(this);

			// Create an instance of our class JDBCMainWindowContent 
			JDBCMainWindowContent aWindowContent = new JDBCMainWindowContent( "lecturer");
			 //Add the instance to the main section of the window
			getContentPane().add( aWindowContent );
			//JDBCStudentContent student=new JDBCStudentContent("student");
			//getContentPane().add(student);
			//student.setVisible(true);
			setSize( 1200, 600 );
			setVisible( true );
		}
		
		// The event handling for the main frame
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(exitItem)){
				this.dispose();
			}
		}
		
		
		
	}