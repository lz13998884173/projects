import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import junit.framework.TestCase;

public class DatabaseTest extends TestCase 
{/*
	//Test No. 1
		//Objective: To test entry of customer data
		//Input(s): First Name = "Kevin", Surname = "McQuaide", Address = "Longford", Phone Number = "0851181958"
		//Expected Output: True
	public void testCustomerEntry001() throws SQLException {
		Database testObject = new Database();
		assertEquals(true, testObject.addNewCustomer("Kevin", "McQuaide", "Longford", "0851181958"));// Will check the expected output "true" matches the
																									// output from the method
	}

	//Test No. 2
	//Objective: To test entry of delivery person data
	//Input(s): First Name = "Walter", Surname = "White", Area = "Athone" 
	//Expected Output: True
	public void testDeliveryPersonEntry001() {
		Database testObject = new Database();
		assertEquals(true, testObject.addNewDelivery("Walter", "White", "Athlone"));		// Will check the expected output "true" matches the
																									// output from the method
	}
	
	//Test No. 3
	//Objective: To test entry of subscription data
	//Input(s): Holiday Start = "13/07/2018", Holiday End = "20/07/2018" 
	//Expected Output: True
	public void testSubscriptionEntry001() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(true, testObject.addNewSubscription("13/07/2018", "20/07/2018", false, false, false, false));		// Will check the expected output "true" matches the
																									// output from the method
	}
	
	//Test No. 4
	//Objective: To test entry of 
	//Input(s): Area = "Dublin", Delivery ID = 1, Customer ID = 2
	//Expected Output: True
	public void testDocketEntry001() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(true, testObject.addNewDocket("Dublin", 1, 2));		// Will check the expected output "true" matches the
		// output from the method
	}
	//Test No. 5
	//Objective: To test searching non-existant customer by an ID returns null
	//Input(s): Cust ID: 47
	//Expected Output: null
	public void testCustSearchId001() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(null, testObject.searchCustomerID(47));		// Will check the expected output null matches the empty result set as the id shouldn't be found
		// output from the method
	}
	
	
	//Test No. 6
	//Objective: To test searching non-existant customer by an first and surname returns null
	//Input(s): First name: "Jill", Surname: "Austin"
	//Expected Output: null
	public void testCustSearchName001() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(null, testObject.searchCustomerName("Jill", "Austin"));		// Will check the expected output null matches the empty result set as the id shouldn't be found
		// output from the method
	}
	
	//Test No. 7
	//Objective: To test updating of customer by changing their details
	//Input(s): Cust ID: 4 First name: "James", Surname: "Mangold", Address: "Athlone", Phone number: "0879891234"
	//Expected Output: true
	public void testCustModify001() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(true, testObject.modifyCustomer(4, "James", "Mangold", "Athlone", "0879891234"));		// Will check the expected output is true as it should update that customer
		// output from the method
	}
	

	//Test No. 8
	//Objective: To test updating of customer won't work if ID doesn't exist 
	//Input(s): Cust ID: 47 First name: "James", Surname: "Mangold", Address: "Athlone", Phone number: "0879891234"
	//Expected Output: true
	public void testCustModify002() throws SQLException, ParseException {
		Database testObject = new Database();
		assertEquals(false, testObject.modifyCustomer(47, "James", "Mangold", "Athlone", "0879891234"));		// Will check the expected output is false as it should not find the customer
		// output from the method
	}

	//Test No. 9
	//Objective: To test searching of subscription works if ID does exist 
	//Input(s): Sub ID: 9
	//Expected Output: true
	public void testSubSearchSubID001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionID(9);
		
		assertNotNull(rs);	
	}


	//Test No. 10 
	//Objective: To test searching of subscription works if ID does not exist 

	//Input(s): Sub ID: 100
	//Expected Output: false
	public void testSubSearchSubID002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionID(100);
		
		assertNull(rs);	
	}
	
	//Test No. 11
	//Objective: To test searching of subscription by name of a newspaper
	//Input(s): Newspaper: (Irish) "Independent"
	//Expected Output: True
	public void testSubSearchSubNewspaper001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionNewspaper("Independent");
		
		assertNotNull(rs);	
	}
	
	//Test No. 12
	//Objective: To test searching of subscription by name of a newspaper
	//Input(s): Newspaper: (Irish) "Mirror"
	//Expected Output: True
	public void testSubSearchSubNewspaper002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionNewspaper("Mirror");
		
		assertNotNull(rs);	
	}
	
	//Test No. 13
	//Objective: To test searching of subscription by name of a newspaper
	//Input(s): Newspaper: (Longford) "Leader"
	//Expected Output: True
	public void testSubSearchSubNewspaper003() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionNewspaper("Leader");
		
		assertNotNull(rs);	
	}
	
	//Test No. 14
	//Objective: To test searching of subscription by name of a newspaper
	//Input(s): Newspaper: (Westmeath) "Topic"
	//Expected Output: True
	public void testSubSearchSubNewspaper004() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscriptionNewspaper("Topic");
		
		assertNotNull(rs);	
	}
	//Test No. 15
	//Objective: To test modifying of subscription by subscription ID
	//Input(s): Sub ID: 2, Date Start: "11/11/2018", Date End: "18/11/2018", Independent: true, Mirror: false, Leader: true, Topic: false
	//Expected Output: True
	public void testSubMod001() throws SQLException, ParseException {
		Database testObject = new Database();

		assertEquals(true, testObject.modifySubscription_SubID(	2, "11/11/2018", "18/11/2018", true, false, true, false));	
	}

	//Test No. 16
	//Objective: To test modifying of subscription by customer ID 
	//Input(s): Sub ID: 2, Date Start: "11/11/2018", Date End: "18/11/2018", Independent: true, Mirror: false, Leader: true, Topic: false
	//Expected Output: True
	public void testSubMod002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		assertEquals(true, testObject.modifySubscription_CusID(6, "11/11/2018", "18/11/2018", true, false, true, false));	
	}
	
	
	//Test No. 17
	//Objective: To test modifying for non existent sub id 
	//Input(s): Newspaper: Sub ID: 999, Date Start: "11/11/2018", Date End: "18/11/2018", Independent: true, Mirror: false, Leader: true, Topic: false
	//Expected Output: False
	public void testSubMod003() throws SQLException, ParseException 
	{
		Database testObject = new Database();
		
		assertEquals(false, testObject.modifySubscription_SubID(999, "11/11/2018", "18/11/2018", true, false, true, false));	
	}
	
	//Test No. 18
	//Objective: To test modifying for non existent customer id
	//Input(s): Newspaper: Cus ID: 2, Date Start: "11/11/2018", Date End: "18/11/2018", Independent: true, Mirror: false, Leader: true, Topic: false
	//Expected Output: False
	public void testSubMod004() throws SQLException, ParseException {
		Database testObject = new Database();
		
		assertEquals(false, testObject.modifySubscription_CusID(999, "11/11/2018", "18/11/2018", true, false, true, false));	
	}
	
	//Test No. 19
	//Objective: To test searching for subscriptions by subscription ID
	//Input(s): Newspaper: Sub ID: 7
	//Expected Output: True
	public void testSearchSubscription_SubID001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscription_SubID(7);
		
		assertNotNull(rs);		
	}
	}
	
	//Test No. 20
	//Objective: To test searching for non-existant subscriptions by subscription ID
	//Input(s): Newspaper: Sub ID: 999
	//Expected Output: True
	public void testSearchSubscription_SubID002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscription_SubID(999);
		
		assertNull(rs);		
	}
	
	//Test No. 21
	//Objective: To test searching for subscriptions by customers ID
	//Input(s): Newspaper: Sub ID: 12
	//Expected Output: True
	public void testSearchSubscription_CusID001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscription_CusID(7);
		
		assertNotNull(rs);		
	}
	
	//Test No. 22
	//Objective: To test searching for subscriptions by non-existant customers ID
	//Input(s): Newspaper: Sub ID: 999
	//Expected Output: True
	public void testSearchSubscription_CusID002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchSubscription_CusID(999);
		
		assertNull(rs);		
	}
	
	//Test No. 23
	//Objective: To test searching for delivery dockets by docket ID
	//Input(s): Newspaper: Docket ID: 5
	//Result set Not Null
	public void testSearchDocket_DocketID001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchDocket_DocketID(5);
		
		assertNotNull(rs);		
	}
	//Test No. 24
	//Objective: To test searching for delivery dockets by non-existent customer ID
	//Input(s): Newspaper: Docket ID: 997
	//Result set Null
	public void testSearchDocket_DocketID002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchDocket_DocketID(997);
		
		assertNull(rs);		
	}
*/	

	//Test No. 25
	//Objective: To test searching for delivery dockets by customer ID
	//Input(s): Newspaper: Docket ID: 5
	//Result set Not Null
	public void testSearchDocket_CustID001() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchDocket_DocketID(4);
		
		assertNotNull(rs);		
	}
	//Test No. 26
	//Objective: To test searching for delivery dockets by customer ID
	//Input(s): Newspaper: Docket ID: 997
	//Result set Null
	public void testSearchDocket_CustID002() throws SQLException, ParseException {
		Database testObject = new Database();
		
		ResultSet rs = testObject.searchDocket_DocketID(997);
		
		assertNull(rs);		
	}
	
}