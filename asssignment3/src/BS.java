
public class BS<K extends Comparable<K>, T> {
      node<K,T> root;
      BS(){
    	  this.root=null;
      }
      int counter;
      int insert(K key,T obj){

    	   counter=0;
    	  root=ins(root,key,obj);
    
    	 
    	  return counter;
      }
      
      node<K,T> ins(node<K,T> r,K key,T obj){
    	  
    	  
    	 K str=key;
    	  
    	  if(r==null){
    		  node<K,T> n=new node<K,T>(key,obj);
    		 //System.out.println("h");
    		  r=n;
    		  
    		 // System.out.println("as");
    		  counter++;
    		  
    		  return r;
    	  }
    	  
    	  K st=r.key;
    	  
    	  if(str.compareTo(st)<0){
    		  
    		 
    		 // System.out.println("r");
    		  counter++;
    		  r.left=ins(r.left,key,obj);
    		  
    	  }
    	  else if(str.compareTo(st)>0){
    		//  i++;
    		  counter++;
    		  r.right=ins(r.right,key,obj);
    		  
    	  }
        else if(str.compareTo(st)==0){
        	counter = -1;
        	/*if(key.toString().equals(r.key.toString())){
    		  counter = -1;
    		  
        	}
        	else if(key.toString().compareTo(r.key.toString())>0){
        		counter++;
      		  r.right=ins(r.right,key,obj);
        		
        	}
        	else{
        		 counter++;
       		  r.left=ins(r.left,key,obj);
        	}*/
    	  }
    	  
    	//  r.a=r.a+1;
    	  return r;
    	  
      }
      
      int update(K key,T obj){
    	  counter=0;
    	  root=upd(root,key,obj);
    	  return counter;
    	  
      }
      node<K,T> upd(node<K,T> r,K key,T obj){
    	  
    	  K str =key;
    	  if(r==null){
    		  counter=-1;
    		  return r;
    	  }
    	  
    	  K st=r.key;
    	  if(str.compareTo(st)==0){
    		  r.obj=obj;
			  counter++;
			  return r;
    		 /* if(key.toString().compareTo(r.key.toString())==0){
    			  r.obj=obj;
    			  counter++;
    			  return r;
    		  }
    		  else if(key.toString().compareTo(r.key.toString())>0){
          		counter++;
        		  r.right=upd(r.right,key,obj);
          		
          	}
          	else{
          		 counter++;
         		  r.left=upd(r.left,key,obj);
          	}*/
    		  
    		  
    		 
    	  }
    	  else if(str.compareTo(st)>0){
    		  counter++;
    		  r.right=upd(r.right, key,obj);
    	  }
    	  else{
    		  counter++;
    		 r.left=upd(r.left,key,obj);
    	  }
    	 
    	  return r;	  
    	  
      }
      
      int delete(K key){
    	  counter=0;
    	  root=del(root,key);
    	  return counter;
      }
      node<K,T> del(node<K,T> r,K key){
    	  K str =key;
    	  if(r==null){
    		  counter=-1;
    		  return r;
    	  }
    	  
    	  K st=r.key;
    	  if(str.compareTo(st)==0){
    		 // if(key.toString().compareTo(r.key.toString())==0){

    		  counter++;
    		  if(r.right!=null&&r.left!=null){
    			  node<K,T> n=r.right;
        		  
    	    		 while(n.left!=null){
    	    			 
    	    			 n=n.left;
    	    			 
    	    		 }
    	    		 r.obj=n.obj;
    	    		 r.key=n.key;
    	    		 
    	    		 r.right=del(r.right,n.key);
    	    		 
    		
    	       }
    		  else if(r.right==null&&r.left==null){
    			  
    			  r=null;
    			  return r;
    			  
    		  }
    		  else if(r.right==null&&r.left!=null){
    			  counter++;
    			  return r.left;
    		  }
    		  else if(r.right!=null&&r.left==null){
    			  counter++;
    			  r=r.right;
    			  return r;
    		  }
    		  
    		  
    		  
    		  
    		  return r;
    	    
    	//	 }
    		 /* else if(key.toString().compareTo(r.key.toString())>0){
    			  counter++;
        		  r.right=del(r.right, key);
    		  }
    		  else{
    			  counter++;
    	    		 r.left=del(r.left,key);
    		  }*/
    		  
    	  }
    	  else if(str.compareTo(st)>0){
    		  counter++;
    		  r.right=del(r.right, key);
    	  }
    	  else{
    		  counter++;
    		 r.left=del(r.left,key);
    	  }
    	 
    	  return r;	  
      }
      
      boolean contains(K key){
    	K str=key;
    	node<K,T> n=root;
    	if(root==null){
    		return false;
    	}
    	
    	while(n!=null){
    		K st=n.key;
    	   if(str.compareTo(st)==0){
    		  
    		//   if(key.toString().compareTo(n.key.toString())==0){
    			  // System.out.println(key.toString());
    			   
    		    return true;
    		 //  }
    		 /*  else if(key.toString().compareTo(n.key.toString())>0){
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
    	return false;
      }     
      
}
