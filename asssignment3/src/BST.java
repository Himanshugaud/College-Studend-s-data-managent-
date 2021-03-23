import java.io.*;
public class BST<K extends Comparable<K>, T> implements MyHashTable_<K, T> {

	int size;
	BS<K,T> table[];
	public BST(int size){
		this.size=size;
		this.table=new BS[this.size];
	}
	public int insert(K key, T obj) {
		//int i=0;
		String str=key.toString();
		
		long ind=djb2(str,this.size);
		int index=(int)ind;
		//System.out.print(index+" ");
		
		if(table[index]==null){
			BS<K,T> bst= new BS();
			int a=bst.insert(key, obj);
			table[index]=bst;
			
			return a;
			
		}
		else{
			BS<K,T> bst=table[index];
			int a=bst.insert(key, obj);
			table[index]=bst;
			return a;
		}
		
	
	}

	
	public int update(K key, T obj) {
        String str=key.toString();
		
		long ind=djb2(str,this.size);
		int index=(int)ind;
		
		if(table[index]==null){
			
			return -1;
			
		}
		else{
			BS<K,T> bst=table[index];
			int a=bst.update(key, obj);
			table[index]=bst;
			return a;
		}
	
		
	}

	
	public int delete(K key) {
		
        String str=key.toString();
		
		long ind=djb2(str,this.size);
		int index=(int)ind;
        if(table[index]==null){
			
			return -1;
			
		}
		return table[index].delete(key);
		
	}

	
	public boolean contains(K key) {
        String str=key.toString();
		
		long ind=djb2(str,this.size);
		int index=(int)ind;
        if(table[index]==null){
			
			return false;
			
		}
		return table[index].contains(key);
	}

	
	public T get(K key) throws NotFoundException {
        String str1=key.toString();
		K str =key;
		long ind=djb2(str1,this.size);
		int index=(int)ind;
		if(table[index]!=null){
		node<K,T> n=table[index].root;
		if(n==null){
			throw new NotFoundException();
		}
    	
    	while(n!=null){
    		K st=n.key;
    		//System.out.print(st +" ");
    	   if(str.compareTo(st)==0){
    		  // if(key.toString().compareTo(n.key.toString())==0){
    		return n.obj;
    		   //}
    		/*   else if(key.toString().compareTo(n.key.toString())>0){
    			   n=n.right;
    		   }
    		   else{
    			   n=n.left; 
    		   }*/
    	   }
    	   else if(str.compareTo(st)>0){
    		n=n.right;
    	   }
    	   else if(str.compareTo(st)<0){
       		n=n.left;
       	   }
    	   }
    	   throw new NotFoundException();
		}
    	
		throw new NotFoundException();
	}

	
	public String address(K key) throws NotFoundException {
		
    	String str1=key.toString();
    	K str =key;
    	long ind=djb2(str1,this.size);
		int index=(int)ind;
		String s=Integer.toString(index)+"-";
      	node<K,T> n=table[index].root;
      	if(n==null){
      		throw new NotFoundException();
      	}
   
      	while(n!=null){
      	   	K st=n.key;
      		//System.out.println(st);
      	   if(str.compareTo(st)==0){
      		//   if(key.toString().compareTo(n.key.toString())==0){
      			 return s;  
      		//   }
      		/*   else if(key.toString().compareTo(n.key.toString())>0){
      			 s=s+"R";
           		n=n.right;
      		   }
      		   else{
      			 s=s+"L"; 
      			n=n.left;
      		   }*/
      	   }
      	   else if(str.compareTo(st)>0){
      		   s=s+"R";
      		n=n.right;
      	   }
      	   else if(str.compareTo(st)<0){
      		   
      		   s=s+"L";
      		// System.out.println(s);
         		n=n.left;
         	   }
      	}
      	
    	  
    	  
		throw new NotFoundException();
	}
	
	public static long djb2(String str, int hashtableSize) { 
	    long hash = 5381; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = ((hash << 5) + hash) + str.charAt(i); 
	    } 
	    return Math.abs(hash) % hashtableSize; 
	}
	

}
