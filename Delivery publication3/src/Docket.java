
public class Docket {
	private String customer_firstName;
	private String customer_lastName;
	private String DeliveryPerson_firstName;
	private String DeliveryPerson_lastName;
	private String DeliveryPerson_deliveryArea;
	private int docket_sub_id;
	Database myDB = null;

	public Docket(String cf, String cl, String df, String dl, String de, int si, Database d) {
		this.customer_firstName = cf;
		this.customer_lastName = cl;
		this.DeliveryPerson_firstName = df;
		this.DeliveryPerson_lastName = dl;
		this.DeliveryPerson_deliveryArea = de;
		this.docket_sub_id = si;
		this.myDB = d;

	}

	public boolean insertDocket() {
		boolean insert = myDB.addNewDocket(DeliveryPerson_deliveryArea, DeliveryPerson_firstName,DeliveryPerson_lastName, customer_firstName, customer_lastName, docket_sub_id);
		return insert;
	}

}
