import java.sql.ResultSet;
import java.sql.SQLException;

public class Customers {

	private String customer_firstName;
	private String customer_lastName;
	private String customer_address;
	private String customer_phone;
	private int customer_id;
	Database myDB = null;

	
	/*public Customers() 
	{

		this.customer_firstName = null;
		this.customer_lastName = null;
		this.customer_address = null;
		this.customer_phone = null;
	}*/
	public Customers(Database d) {
		myDB = d;
	}
	
	public Customers(String customer_firstName, String customer_lastName, String customer_address, String customer_phone, Database d) 
	{

		this.customer_firstName = customer_firstName;
		this.customer_lastName = customer_lastName;
		this.customer_address = customer_address;
		this.customer_phone = customer_phone;
		myDB = d;
	}
	public ResultSet searchCustomerID(int id)throws SQLException{
		
		ResultSet search=myDB.searchCustomerID(id);
		return search;
		
	}

	public ResultSet searchCustomerName(String firstName ,String surName)throws SQLException {
		
		
		ResultSet search=myDB.searchCustomerName(firstName, surName);
		return search;
		
	}
	public boolean insertCustomer() throws SQLException {
		
		boolean insert = myDB.addNewCustomer(customer_firstName, customer_lastName, customer_address, customer_phone);

		return insert;
	}
	public boolean modifyCustomer(int id,String fname,String sname,String addr,String phone) throws SQLException{
		this.customer_id=id;
		this.customer_firstName=fname;
		this.customer_lastName=sname;
		this.customer_address=addr;
		this.customer_phone=phone;
		boolean modify=myDB.modifyCustomer(customer_id, customer_firstName, customer_lastName, customer_address, customer_phone);

		return modify;
	}

}
