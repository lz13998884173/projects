import java.sql.SQLException;

import junit.framework.TestCase;

public class DeliveryPersonTest extends TestCase {
	


	
	//Test No. 
		//Objective: To test entry of Delivery Person data
		//Input(s): First Name = "Lance", Surname = "Uppercut", Area = "Laois"
		//Expected Output: True
		public void testDeliveryPersonEntry002() throws SQLException 
		{	
			Database db = new Database();
			DeliveryPerson testObject = new DeliveryPerson("Lance", "Uppercut", "Laois", db);
			assertEquals(true, testObject.insertDeliveryPerson());
		}

}
