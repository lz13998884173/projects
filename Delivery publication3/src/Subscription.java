
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;

public class Subscription {
	private int Sub_id;
	private int Cust_id;
	private String dateStart;
	private String dateEnd;
	private Database myDB = new Database();
	private boolean independent;
	private boolean mirror;
	private boolean leader;
	private boolean topic;
	
	public Subscription() {}
	public Subscription(String ds, String de, boolean independent, boolean mirror, boolean leader, boolean topic, Database d) {
		
		this.dateStart = ds;
		this.dateEnd = de;
		this.independent = independent;
		this.mirror = mirror;
		this.leader = leader;
		this.topic = topic;
		//myDB = d;
	}

	public boolean insertSubscription() 
	{
		boolean insert = false;
		try 
		{
			insert = myDB.addNewSubscription(dateStart, dateEnd, independent, mirror, leader, topic);
		}
		catch (SQLException | ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insert;
	}
	public boolean modifySubscription_SubID(int id, String Start, String End, boolean i, boolean m, boolean l, boolean t) throws SQLException{
		boolean modify = false;
		this.Sub_id=id;
		this.dateStart=Start;
		this.dateEnd=End;
		this.independent=i;
		this.leader=l;
		this.mirror=m;
		this.topic=t;
		//myDB=new Database();

		System.out.println(this.Sub_id);
		System.out.println(this.dateStart);
		System.out.println(this.dateEnd);
		System.out.println(this.independent);
		System.out.println(this.leader);
		System.out.println(this.mirror);
		System.out.println(this.topic);
		
		modify=myDB.modifySubscription_SubID(Sub_id, dateStart, dateEnd, independent, mirror, leader, topic);
		return modify;
		
	}
	public boolean modifySubscription_CustID(int Cust_id, String dateStart, String dateEnd, boolean independent, boolean mirror, boolean leader, boolean topic) throws SQLException{
		boolean modify = false;
		this.Cust_id=Cust_id;
		this.dateStart=dateStart;
		this.dateEnd=dateEnd;
		this.independent=independent;
		this.leader=leader;
		this.mirror=mirror;
		this.topic=topic;
		modify=myDB.modifySubscription_CusID(Cust_id, dateStart, dateEnd, independent, mirror, leader, topic);
		return modify;
		
	}
	public ResultSet searchSubscription_SubID(int id) throws SQLException {
		ResultSet search=myDB.searchSubscription_SubID(id);
		return search;
		
	}
	public ResultSet searchSubscription_CustID(int id) throws SQLException {
		ResultSet search=myDB.searchSubscription_CusID(id);
		return search;
		
	}

}
