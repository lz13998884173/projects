import java.util.ArrayList;

class Node {
    int  key;
    String from;
    String destination;
    String status;
    Node leftChild;
    Node rightChild;
    public static ArrayList<String[]> ta=new ArrayList<String[]>();
     
    Node(int key,String from,String destination,String status) {
        this.key = key;
        this.from=from;
        this.destination=destination;
        this.status=status;
        
    }
     
    public void display() {
        System.out.print(this.key + "\t");
        System.out.print(this.from + "\t");
        System.out.print(this.destination + "\t");
        System.out.print(this.status + "\t");
    }
    
    public void printNode() {  
        if (this.leftChild != null) {  
            this.leftChild.printNode();  
        }  
        //System.out.println("Print :Flight id:"+this.key+" Flight from:"+this.from+"  destination:"+this.destination+" Status:"+this.status);
        String id=this.key+"";
        String from=this.from;
        String destination=this.destination;
        String status=this.status;
        String[] n=new String[]{id,from,destination,status};
        System.out.println(ta.add(n));
        if (this.rightChild!= null) {  
            this.rightChild.printNode();  
        }  
    }  
 
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(key);
    }
    public int readKey(){return key;}
    public String readFrom() {return from;}
    public String readDestination(){return destination;}
    public String readStatus(){return status;}
    public String[] getNode(){
		String[] st=new String[4];
		st[0]=this.key+"";st[1]=this.from;st[2]=this.destination+"";
		st[3]=this.status+"";
		return st;
	}
}