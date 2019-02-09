public class HashNode {
	public int stuid;
	public String from;
	public String destination;
	public String status;
	HashNode next;
	HashNode(int id, String from, String destination,String status){
		this.stuid = id;
		this.from = from;
		this.destination=destination;
        this.status=status;
		next = null;  
		}
	public int getStuid() {return stuid;}
	public String getFrom() {return from;}
	public String getDestination() {return destination;}
	public String getStatus() {return status;}
	
	public HashNode getNext() {return next;}
	public String[] getNode(){
		String[] st=new String[4];
		st[0]=this.stuid+"";st[1]=this.from;st[2]=this.destination+"";
		st[3]=this.status+"";
		return st;
	}
}