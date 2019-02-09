public class DeliveryPerson {
	private String DeliveryPerson_firstName;
	private String DeliveryPerson_lastName;
	private String DeliveryPerson_deliveryArea;
	Database myDB = null;

	public DeliveryPerson(String firstName,String lastName,String deliveryArea,Database d) {
		this.DeliveryPerson_firstName=firstName;
		this.DeliveryPerson_lastName=lastName;
		this.DeliveryPerson_deliveryArea=deliveryArea;
		myDB=d;
		
	}
	
	 public boolean insertDeliveryPerson(){
		   boolean insert = myDB.addNewDelivery(DeliveryPerson_firstName,DeliveryPerson_lastName,DeliveryPerson_deliveryArea);
		   return insert;
	   }
}
