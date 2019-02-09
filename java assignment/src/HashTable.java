import java.util.ArrayList;

public class HashTable{
	
    public HashNode[] head=new HashNode[10]; 
	public  HashTable(){
		for(int i=0;i<10;i++)
			head[i]=null;
		}
	public int hash(int id){
		return id%10;
		}
	public void insert(int stuid, String from, String destination,String status){      
		HashNode temp =new HashNode(stuid,from,destination,status);
	    int index=hash(stuid);
	    temp.next=head[index];
		head[index]=temp;
		}
	public void delete(int stuid){     
		int index=hash(stuid);
		HashNode temp=head[index];	
		boolean found=false;
		
		while(temp.next!=null&&found==false) {
			System.out.println("id: "+temp.stuid);
			if(temp.next.stuid==stuid) {
				found=true;
				if(temp.next.next!=null) {
					temp.next=temp.next.next;
				}
				else temp.next=null;
				break;
			}
			temp=temp.next;
		}
		
		}
	
	
	public HashNode search(int stuid){     
		int index=hash(stuid);
		HashNode temp=head[index];	 
		boolean found=false;
		while(temp!=null&&found==false) {
			if (temp.stuid==stuid){
				found=true;
				break;}
			temp=temp.next;}
		return temp;}
	
	public  ArrayList<String[]> Table_print(){
		ArrayList<String[]> st=new ArrayList<String[]>();
		 
		for(int i=0;i<10;i++){
			HashNode temp=head[i];	
			while(temp!=null){
				st.add(temp.getNode());
				temp=temp.next;
			}
		}
		return st;
	}
	public void destroy(){
		for(int i=0;i<10;i++){
			head[i]=null;	
			}
		}
   }


   