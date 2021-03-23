import java.io.*;
public class Assignment3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String tablesize=args[0];
		int size=Integer.parseInt(tablesize);
		String approch=args[1];
		String infile=args[2];
		BufferedReader br=new BufferedReader(new FileReader(infile));
		if(approch.equals("DH")){
			String line;
			MyHashTable<String,Student> ht=new MyHashTable(size);
			while((line=br.readLine())!=null){
				String[] words=line.split(" ");
				String fun=words[0];
				String k=words[1]+words[2];
				if(fun.equals("insert")){
					
					Student s=new Student(words[1],words[2],words[3],words[4],words[5]);
					int print =ht.insert(k,s);
					if(print==-1){
						System.out.println("E");
					}
					else{
					System.out.println(Integer.toString(print));
					}
				}
				else if(fun.equals("update")){
					//String k=words[1]+" "+words[2];
					Student s=new Student(words[1],words[2],words[3],words[4],words[5]);
					int print =ht.update(k,s);
					if(print==-1||print==-2){
					System.out.println("E");
					}
					else{
						System.out.println(Integer.toString(print));
					}
				}
				else if(fun.equals("delete")){
					//String k=words[1]+" "+words[2];
					
					int print =ht.delete(k);
					if(print==-1){
					System.out.println("E");
					}
					else{
						System.out.println(Integer.toString(print));
					}
				}
				else if(fun.equals("contains")){
					//String k=words[1]+" "+words[2];
					
					boolean b =ht.contains(k);
					if(b){
					System.out.println("T");
					}
					else{
						System.out.println("F");
					}
				}
				
				
				
				
				//to be updated
				else if(fun.equals("get")){
					//String k=words[1]+" "+words[2];
					
					Student st;
					try {
						st = ht.get(k);
						System.out.println(st.fname()+" "+st.lname+" "+st.hostel()+" "+st.department()+" "+st.cgpa());
					} catch (NotFoundException e) {
					
						System.out.println("E");
					
					}
					
						
					
				}
				else if(fun.equals("address")){
					//String k=words[1]+" "+words[2];
					
					String st;
					try {
						st = ht.address(k);
						System.out.println(st);
					} catch (NotFoundException e) {
					
						System.out.println("E");
					
					}
					
						
					
				}
				
			}
			
		}
		
		
		
		else if(approch.equals("SCBST")){
			
			String line;
			BST<pair<String,String>,Student> ht=new BST(size);
			while((line=br.readLine())!=null){
				String[] words=line.split(" ");
				String fun=words[0];
				pair<String,String> k;
				 k=new pair(words[1],words[2]);
				if(fun.equals("insert")){
					//String k=words[1]+" "+words[2];
					Student s=new Student(words[1],words[2],words[3],words[4],words[5]);
					int print =ht.insert(k,s);
					if(print==-1){
						System.out.println("E");
					}
					else{
					System.out.println(Integer.toString(print));}
				}
				else if(fun.equals("update")){
					//String k=words[1]+" "+words[2];
					Student s=new Student(words[1],words[2],words[3],words[4],words[5]);
					int print =ht.update(k,s);
					if(print==-1||print==-2){
					System.out.println("E");
					}
					else{
						System.out.println(Integer.toString(print));
					}
				}
				else if(fun.equals("delete")){
					//String k=words[1]+" "+words[2];
					
					int print =ht.delete(k);
					if(print==-1){
					System.out.println("E");
					}
					else{
						System.out.println(Integer.toString(print));
					}
				}
				else if(fun.equals("contains")){
					//String k=words[1]+" "+words[2];
					
					boolean b =ht.contains(k);
					if(b){
					System.out.println("T");
					}
					else{
						System.out.println("F");
					}
				}
				
				
				
				
				//to be updated
				else if(fun.equals("get")){
					//String k=words[1]+" "+words[2];
					
					Student st;
					try {
						st = ht.get(k);
						System.out.println(st.fname()+" "+st.lname+" "+st.hostel()+" "+st.department()+" "+st.cgpa());
					} catch (NotFoundException e) {
					
						System.out.println("E");
					
					}
					
						
					
				}
				else if(fun.equals("address")){
					//String k=words[1]+" "+words[2];
					
					String st;
					try {
						st = ht.address(k);
						System.out.println(st);
					} catch (NotFoundException e) {
					
						System.out.println("E");
					
					}
					
						
					
				}
				
			}
			
		}
		

	}

	
	

}
