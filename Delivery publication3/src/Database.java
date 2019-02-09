	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;


import javax.swing.JOptionPane;

public class Database 
{
	/** Database Connection */
    private Connection con;
    
    /** Used for executing database statements*/
    private Statement stmt;      
    
    /** Holds result of stmt, moves to next row in database*/
    private ResultSet rs;
    
    /** Holds the number of rows in database value*/
    int count;
    
    /** Holds the current number of the row in the database we are on*/
    int current;
	
	/*Customer values*/
    private String cust_first_name;
	private String cust_surname;
	private String cust_addr;
	private String cust_phone;
	private int cust_id_search;
	private String cust_fname_search;
	private String cust_sname_search;

	/*Delivery person values*/
	private String del_first_name;
	private String del_surname;
	private String del_area;

	/*Subscription values*/
	private String date_start;
	private String date_end;
	private boolean independent;
	private boolean mirror;
	private boolean leader;
	private boolean topic;
	private String search_newspaper;
	private int search_sub_id;
	private int modify_sub_id;
	private int modify_cus_id;
	private String mod_date_start;
	private String mod_date_end;
	private boolean mod_independent;
	private boolean mod_mirror;
	private boolean mod_leader;
	private boolean mod_topic;
	private int search_cus_id;
	
	/*Delivery docket values*/
	private String docket_delarea;
	private String docket_delivery_firstName;
	private String docket_delivery_surname;
	private String docket_cust_firstName;
	private String docket_cust_surname;
	private int docket_sub_id;
	private int docket_search_id;
	
    public Database()
    {
        con = null;         /** @param con       the connection parameter to connect to MS Access database.*/
        stmt = null;        /** @param stmt      the statement parameter to execute database statements.*/
        rs = null;          /** @param rs        the result parameter to hold the result of statement and move rows.*/
        count = 0;          /** @param count     the int value to count number of rows in database.*/
        current = 0;        /** @param current   the int value to keep track of which row in database we are on.*/
    
        dbConn();// method to connect to database using odbc-jdbc
      //  initDB();// method to initialise gui with database info

    }
    
    private void initDB() {
		// TODO Auto-generated method stub
		
	}

	public void dbConn()
    {
		try		
		{
			// driver to use with named database which is stored alongside program in C Drive. Access database must be in this directory
			//Requires ucanaccess library to be implemented to connect
	        //String url = "jdbc:ucanaccess://c:/Agile/Newsagent.accdb";
			String url = "jdbc:ucanaccess://src/Newsagent.accdb";
	        
	        // connection represents a session with a specific database
	        con = DriverManager.getConnection(url);
	
	        // stmt used for executing sql statements and obtaining results
	        stmt = con.createStatement();
	        System.out.println("Connected");
	        rs = stmt.executeQuery("SELECT * FROM Customer");
	
			while(rs.next())	// count number of rows in table
	        {
	            count++;
	        }
	        System.out.println(count);
	        rs.close();
		}
		catch(Exception e) 
		{
			System.out.println("Unable to connect to database");
			/*JOptionPane.showMessageDialog(database.this,"Error in startup.","Error", JOptionPane.PLAIN_MESSAGE);} */
		}
    }
	
	/*Method called when creating new customer to insert new row*/
	public boolean addNewCustomer(String fname, String sname, String addr, String phone) throws SQLException 
	{
		
		this.cust_first_name = fname;
		this.cust_surname = sname;
		this.cust_addr = addr;
		this.cust_phone = phone;

		String checkNum = "Select Cust_PhoneNo From Customer";
		rs = stmt.executeQuery(checkNum);
		
		while(rs.next())
		{
			if(cust_phone.equals(rs.getString("Cust_PhoneNo")))
			{
				JOptionPane.showMessageDialog(null, "Customer already exists");
				return false;
			}
		}
		
        String newCust = "INSERT INTO Customer(Cust_FirstName, Cust_Surname, Cust_PhoneNo, Cust_Address)VALUES('"+cust_first_name+"', '"+cust_surname+"', '"+cust_phone+"', '"+cust_addr+"')";
        
    try 
        {
			stmt.executeUpdate(newCust);
	        System.out.println("Customer Insert Completed");
		
			return true;
}
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			return false;
		}
	}
	
	public void readCustTable() throws SQLException
	{
		rs = stmt.executeQuery("SELECT * FROM Customer");
		
        rs.next();   
        
        String fname;                  /** @param name          the String to store the name from the database.*/
        String sname;              /** @param address1      the String to store the first address line from the database*/
        
        fname = rs.getString("Cust_FirstName");
        sname = rs.getString("Cust_Surname");
        System.out.println("First Name: " +fname);
        System.out.println("Surname : " +sname);
        
/*		while(rs.next())	// count number of rows in table
        {
            count++;
        }
        System.out.println(count);*/
        rs.close();
	}

	/*Method called when searching customer by their ID*/
	public ResultSet searchCustomerID(int idSearch) 
	{
		this.cust_id_search = idSearch;
		
		/*SQL statement to search by the id number entered*/
        String searchID= "Select * From Customer Where Cust_ID = '" + idSearch + "';";	
		try 
		{
			rs = stmt.executeQuery(searchID);
	    	while(rs.next())
	    	{
	    		if(cust_id_search ==rs.getInt("Cust_ID"))	//Checking if the ID to be searched exists in the database
	    		{
	    			JOptionPane.showMessageDialog(null, "Customer Found");	
	    			return rs;	//If ID is found, return the result set
	    		}
	    	}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Customer does not exist");	
		return null;    		//If ID is not found, return null
	}
	

	/*Method called when searching customer by their name*/
	public ResultSet searchCustomerName(String searchFirstName, String searchSurname) 
	{
		this.cust_fname_search = searchFirstName;
		this.cust_sname_search = searchSurname;
		
        String searchName = "Select * From Customer Where Cust_FirstName = '" +cust_fname_search + "' And Cust_Surname = '"+cust_sname_search+"';";

        try 
        {
			rs = stmt.executeQuery(searchName);
	    	while(rs.next())
	    	{
	    		if(cust_fname_search.equals(rs.getString("Cust_FirstName")) && cust_sname_search.equals(rs.getString("Cust_Surname")))	//Checking if the ID to be searched exists in the database
	    		{
	    			JOptionPane.showMessageDialog(null, "Customer(s) Found");
	    			rs = stmt.executeQuery(searchName);
	    			return rs;	//If name is found, return the result set
	    		}
	    	}
	    	/*if(rs == null)	//Checking if the name to be searched exists in the database
	    	{
	    		JOptionPane.showMessageDialog(null, "Customer does not exist");
	    		return null;
	    	}*/
	    }
        catch (SQLException e) 
        {
			e.printStackTrace();
		}		
		return null;
		
	}

	/*Method called when modifying customer details*/
	public boolean modifyCustomer(int cusId, String fname, String sname, String addr, String phone) throws SQLException 
	{
		this.cust_id_search = cusId;
		this.cust_first_name = fname;
		this.cust_surname = sname;
		this.cust_addr = addr;
		this.cust_phone = phone;	

		boolean custExist = false;
		
		String checkCust = "Select Cust_ID From Customer;";
		rs = stmt.executeQuery(checkCust);
		
		
		while(rs.next())
		{
			if(cust_id_search ==rs.getInt("Cust_ID"))	//Checking if the ID to be updated exists in the database
			{
				custExist = true;
			}
		}
		
		if(custExist == false) //If ID is found, boolean remains false and error message returned.
		{
			JOptionPane.showMessageDialog(null, "Customer does not exist");
			return false;		
		}
		else		//If ID is found, boolean is set to true and should carry out the update.
		{
			String update = "Update Customer Set Cust_FirstName = '"+cust_first_name+"', Cust_Surname = '"+cust_surname+"', Cust_Address = '"+cust_addr+"', Cust_PhoneNo = '"+cust_phone+"' Where Cust_ID = '"+cust_id_search+"';";
			stmt.executeUpdate(update);
			return true;
		}	
	}
	
		

    /*Method called when creating new delivery person to insert new row*/
	public boolean addNewDelivery(String fname, String sname, String area)
	{
		this.del_first_name = fname;
		this.del_surname = sname;
		this.del_area = area;
		
        String newDelivery = "INSERT INTO Delivery_Person(First_Name, Surname, Delivery_Area) VALUES('"+del_first_name+"', '"+del_surname+"', '"+del_area+"')";
        
        try 
        {
			stmt.executeUpdate(newDelivery);
	        System.out.println("Delivery Person Insert Completed");
			return true;
		}
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

    /*Method called when creating new subscription to insert new row*/
	public boolean addNewSubscription(String dateStart, String dateEnd, boolean independent, boolean mirror, boolean leader, boolean topic) throws SQLException, ParseException
	{
		this.date_start = dateStart;
		this.date_end = dateEnd;
		this.independent = independent;
		this.mirror = mirror;
		this.leader = leader;
		this.topic = topic;
		
		String i = null, m = null, l = null, t = null;
		
		//The newspapers are entered as Yes or No into the subscription depending on if they were picked
		if(independent == true)
		{
			i = "Yes";
		}
		else if (independent == false)
		{
			i = "No";
		}
		
		if(mirror == true)
		{
			m = "Yes";
		}
		else if (mirror == false)
		{
			m = "No";
		}
		
		if(leader == true)
		{
			l = "Yes";
		}
		else if (leader == false)
		{
			l = "No";
		}
		if(topic == true)
		{
			t = "Yes";
		}
		else if (topic == false)
		{
			t = "No";
		}
		
		
		rs = stmt.executeQuery("Select max(Cust_ID) From Customer");
		
		int recentCustId = 0;
		
		if (rs.next()) {
		    recentCustId = rs.getInt(1);
		}
		System.out.println(recentCustId);

		
		String newSubscription = "INSERT INTO Subscription(Holiday_Start, Holiday_End, Independent, Mirror, Leader, Topic, Cust_ID)VALUES('"+date_start+"', '"+date_end+"', '"+i+"', '"+m+"', '"+l+"', '"+t+"', '"+recentCustId+"')";     
        try 
        {
			stmt.executeUpdate(newSubscription);
			System.out.println("Subscription Insert Completed");
			return true;
		}
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
    /*Method called when searching a subscription by ID */
	public ResultSet searchSubscription_SubID(int id) throws SQLException
	{
		this.search_sub_id = id;
		
		String searchSubID = "Select * From Subscription Where Sub_ID = '"+search_sub_id+"';";
		try 
		{
		
		rs = stmt.executeQuery(searchSubID);
	    	while(rs.next())
	    	{
	    		if(search_sub_id ==rs.getInt("Sub_ID"))	//Checking if the ID to be searched exists in the database
	    		{
	    			JOptionPane.showMessageDialog(null, "Subscription Found");	
	    			return rs;		//If ID is found, return the result set
	    		}
	    	}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Subscription does not exist");	
		return null;    		//If ID is not found, return null
	}
	

	
    /*Method called when searching a subscription by customers ID */
	public ResultSet searchSubscription_CusID(int id) throws SQLException
	{
		this.search_cus_id = id;
		
		String searchCustID = "Select * From Subscription Where Cust_ID = '"+search_cus_id+"';";
		try 
		{
		
		rs = stmt.executeQuery(searchCustID);
	    	while(rs.next())
	    	{
	    		if(search_cus_id ==rs.getInt("Cust_ID"))	//Checking if the ID to be searched exists in the database
	    		{
	    			JOptionPane.showMessageDialog(null, "Subscription Found");	
	    			return rs;		//If ID is found, return the result set
	    		}
	    	}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Subscription does not exist");	
		return null;    		//If ID is not found, return null
	}	
	
	
	
	
	/*Method to modify the subscription table by the subscription ID*/
	public boolean modifySubscription_SubID(int id, String dateStart, String dateEnd, boolean independent, boolean mirror, boolean leader, boolean topic) throws SQLException
	{
		boolean res=false;
		this.modify_sub_id = id;
		this.mod_date_start = dateStart;
		this.mod_date_end = dateEnd;
		this.mod_independent = independent;
		this.mod_mirror = mirror;
		this.mod_leader = leader;
		this.mod_topic = topic;
		
		
		System.out.println(this.mod_independent);
		System.out.println(this.mod_mirror);
		System.out.println(this.mod_leader);
		System.out.println(this.mod_topic);
		
		boolean subExist = false;
		
		String checkSub = "Select Sub_ID From Subscription;";
		rs = stmt.executeQuery(checkSub);
		
		String i = null, m = null, l = null, t = null; //Strings to change to Yes and No for entering newspapers into the datbase
		
		//The newspapers are entered as Yes or No into the subscription depending on if they were picked
				if(mod_independent == true)
				{
					i = "Yes";
				}
				else if (mod_independent == false)
				{
					i = "No";
				}
				
				if(mod_mirror == true)
				{
					m = "Yes";
				}
				else if (mod_mirror == false)
				{
					m = "No";
				}
				
				if(mod_leader == true)
				{
					l = "Yes";
				}
				else if (mod_leader == false)
				{
					l = "No";
				}
				if(mod_topic == true)
				{
					t = "Yes";
				}
				else if (mod_topic == false)
				{
					t = "No";
				}
		
		while(rs.next())
		{
			if(modify_sub_id ==rs.getInt("Sub_ID"))	//Checking if the ID to be updated exists in the database
			{
				subExist = true;
			}
		}
		 
		if(subExist == false) //If ID is found, boolean remains false and error message returned.
		{
			
			JOptionPane.showMessageDialog(null, "Subscription does not exist");
			res=false;	
		}
		else		//If ID is found, boolean is set to true and should carry out the update.
		{
			String update = "Update Subscription Set Holiday_Start = '"+mod_date_start+"', Holiday_End = '"+mod_date_end+"', Independent = '"+i+"', Mirror = '"+m+"', Leader = '"+l+"', Topic = '"+t+"' Where Sub_ID = '"+modify_sub_id+"';";
			stmt.executeUpdate(update);
			res=true;
		}	
		return res;
		
	}
	
	/*Method to modify the subscription table by the customers ID*/
	public boolean modifySubscription_CusID(int id, String dateStart, String dateEnd, boolean independent, boolean mirror, boolean leader, boolean topic) throws SQLException
	{
		this.modify_cus_id = id;
		this.mod_date_start = dateStart;
		this.mod_date_end = dateEnd;
		this.mod_independent = independent;
		this.mod_mirror = mirror;
		this.mod_leader = leader;
		this.mod_topic = topic;
		
		boolean subExist = false;
		
		String checkSub_Cus = "Select Cust_ID From Subscription;";
		rs = stmt.executeQuery(checkSub_Cus);
		
		String i = null, m = null, l = null, t = null; //Strings to change to Yes and No for entering newspapers into the database
		
		//The newspapers are entered as Yes or No into the subscription depending on if they were picked
				if(mod_independent == true)
				{
					i = "Yes";
				}
				else if (mod_independent == false)
				{
					i = "No";
				}
				
				if(mod_mirror == true)
				{
					m = "Yes";
				}
				else if (mod_mirror == false)
				{
					m = "No";
				}
				
				if(mod_leader == true)
				{
					l = "Yes";
				}
				else if (mod_leader == false)
				{
					l = "No";
				}
				if(mod_topic == true)
				{
					t = "Yes";
				}
				else if (mod_topic == false)
				{
					t = "No";
				}
		
		while(rs.next())
		{
			if(modify_cus_id ==rs.getInt("Cust_ID"))	//Checking if the ID to be updated exists in the database
			{
				subExist = true;
			}
		}
		
		if(subExist == false) //If ID is found, boolean remains false and error message returned.
		{
			JOptionPane.showMessageDialog(null, "Customer does not exist");
			return false;		
		}
		else		//If ID is found, boolean is set to true and should carry out the update.
		{
			String update = "Update Subscription Set Holiday_Start = '"+mod_date_start+"', Holiday_End = '"+mod_date_end+"', Independent = '"+i+"', Mirror = '"+m+"', Leader = '"+l+"', Topic = '"+t+"' Where Cust_ID = '"+modify_cus_id+"';";
			stmt.executeUpdate(update);
			return true;
		}	
		
	}
	
	
    /*Method called when searching a subscription by a newspaper chosen*/
/*	public ResultSet searchSubscriptionNewspaper(String newspaper)
	{
		this.search_newspaper = newspaper;
		rs = null;
		String searchNewspaper = null;
		
		switch (search_newspaper)
		{	//Switch case which determines the newspaper to be searched by depending on the string received
			case "Independent":
		       searchNewspaper = "Select * From Subscription Where Independent = 'Yes';";
		       break;
		    
			case "Mirror":
				searchNewspaper = "Select * From Subscription Where Mirror = 'Yes';";
				break;
				
			case "Leader":
				searchNewspaper = "Select * From Subscription Where Leader = 'Yes';";
				break;
				
			case "Topic":
				searchNewspaper = "Select * From Subscription Where Topic = 'Yes';";
				break;
		}
		
        try 
        {
			rs = stmt.executeQuery(searchNewspaper);
	    }
        catch (SQLException e) 
        {
			e.printStackTrace();
		}		
		return rs;
	}*/
	
	
    /*Method called when creating a new delivery docket*/
	public boolean addNewDocket(String area, String delivery_firstName, String delivery_surname, String cust_firstName, String cust_surname, int sub_id)
	{
		this.docket_delarea = area;
		this.docket_delivery_firstName = delivery_firstName;
		this.docket_delivery_surname = delivery_surname;
		this.docket_cust_firstName = cust_firstName;
		this.docket_cust_surname = cust_surname;
		this.docket_sub_id = sub_id;

        String newDocket = "INSERT INTO Dockets(Del_Area, Del_FirstName, Del_Surname, Customer_FirstName, Customer_Surname, Sub_ID) VALUES('"+docket_delarea+"', '"+docket_delivery_firstName+"', '"+docket_delivery_surname+"', '"+docket_cust_firstName+"', '"+docket_cust_surname+"', '"+docket_sub_id+"')";
        
        try 
        {
			stmt.executeUpdate(newDocket);
	        System.out.println("Delivery Person Insert Completed");
			return true;
		}
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	
	/*Method called when searching for a delivery docket by the docket ID*/
	public ResultSet searchDocket_DocketID(int id)
	{
		this.docket_search_id = id;
		
		String searchDocketID = "Select * From Dockets Where Docket_ID = '"+docket_search_id+"';";

		try 
		{
		
			rs = stmt.executeQuery(searchDocketID);
		
	    	while(rs.next())
	    	{
	    		
	    		if(docket_search_id ==rs.getInt("Docket_ID"))	//Checking if the ID to be searched exists in the database
	    		{
	    			JOptionPane.showMessageDialog(null, "Delivery Docket Found");	
	    			rs = stmt.executeQuery(searchDocketID);
	    			return rs;		//If ID is found, return the result set
	    		}
	    	}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Docket ID does not exist");	
		return null; //If ID is not found, return null
	}
	
	/*Method called when searching for a delivery docket by the customer ID*/
	public ResultSet searchDocket_CustID(int id)
	{
		this.docket_search_id = id;
		
		String searchCustID = "Select * From Dockets Where Cust_ID = '"+docket_search_id+"';";
		
		try 
		{
			
			rs = stmt.executeQuery(searchCustID);
			
			while(rs.next())
			{
				if(docket_search_id ==rs.getInt("Cust_ID"))	//Checking if the ID to be searched exists in the database
				{
					JOptionPane.showMessageDialog(null, "Customer ID Found");	
					rs = stmt.executeQuery(searchCustID);
					return rs;		//If ID is found, return the result set
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Customer ID does not exist");	
		return null; //If ID is not found, return null
	}
}