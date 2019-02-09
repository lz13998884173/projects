import java.sql.SQLException;
import java.text.ParseException;

import junit.framework.TestCase;

public class CustomersTest extends TestCase {
	
	//Test No. 1
	//Objective: To test updating of customer by changing their details
	//Input(s): First Name = "zheng", Surname = "lyu", Address = "Dublin", Phone Number = "0879565846"
	//Expected Output: True

	public void testCustModify001() throws SQLException, ParseException {
		Database db=new Database();
		Customers testObject=new Customers("zheng", "lyu", "Dublin", "0879565846", db);
		assertEquals(true,testObject.modifyCustomer(4, "James", "Mangold", "Athlone", "0879891234"));
	}
	//Test No. 2
		//Objective: To test updating of customer won't work if ID doesn't exist 
		//Input(s): Cust ID: 47 First name: "James", Surname: "Mangold", Address: "Athlone", Phone number: "0879891234"
		//Expected Output: true
	public void testCustModify002() throws SQLException, ParseException {
		Database db = new Database();
		Customers testObject=new Customers("zheng", "lyu", "Dublin", "0879565846", db);
		assertEquals(false, testObject.modifyCustomer(47, "James", "Mangold", "Athlone", "0879891234"));		// Will check the expected output is false as it should not find the customer
		// output from the method
		
	}
	//Test No. 3
			//Objective: To test insert into customer 
			//Input(s):  First name: "zheng", Surname: "lyu", Address: "Dublin", Phone number: "0879565846"
			//Expected Output: true
	/*public void testinsertCustomer001() throws SQLException{
		Database db = new Database();
		Customers testObject=new Customers("zheng", "lyu", "Dublin", "0879565846", db);
		assertEquals(true,testObject.insertCustomer());
	}*/
	//Test No. 4
	//Objective: To test insert into customer if there has exists data
	//Input(s):  First name: "zheng", Surname: "lyu", Address: "Dublin", Phone number: "0812345695"
	//Expected Output: false
	public void testinsertCustomer002() throws SQLException{
		Database db = new Database();
		Customers testObject=new Customers("zheng", "lyu", "Dublin", "0812345695", db);
		assertEquals(false,testObject.insertCustomer());
	}
	//Test No. 5
		//Objective: To test research customer if there has exists data
		//Input(s):  "cust_id:1"
		//Expected Output: false
	
	public void testsearchCustomerID001() throws SQLException{
		Database db = new Database();
		Customers testObject=new Customers("zheng", "lyu", "Dublin", "0879565846", db);
		assertEquals(null,testObject.searchCustomerID(1));
	}
}
