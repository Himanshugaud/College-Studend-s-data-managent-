import java.lang.Math;
public class MyHashTable<K,T> implements MyHashTable_<K,T> {
	int size;
	pair<K,T> table[];
	public MyHashTable(int n){
		this.size =n;
		this.table = new pair[size];
	}
	
	
	public int insert(K key,T obj){
		int i=1;
		String str=key.toString();
		long in=djb2(str,this.size);
		int index=(int)(in%size);
		//System.out.print(index);
		while(table[index]!=null&&table[index].type!=1){
			
			in =in+1*sdbm(str,this.size);
			index=(int)(in%size);	
			i++;
           // System.out.print(index+" ");
            if(i>this.size){
            	return -1;
            	
            }
		}
		pair<K,T> p=new pair(key,obj);
		
		table[index]=p;
		return i;
	}
	
	public int update(K key, T obj) {
		int i=1;
		String str =key.toString();
		long in =djb2(str,this.size);
		int index=(int)(in%this.size);
		while(this.table[index]!=null){
			if(i>this.size){
            	break;
            }
			if(table[index].type==1){
				in =in+ 1*sdbm(str,this.size);
				int ind=(int)(in%this.size);
				if(ind==index){
					return -2;//
				}
				else{
					index=ind;
				}
				i++;
			}
			else{
			    pair<K,T> p=table[index];
			    String str1=p.key.toString();
			    if(str1.equals(str)){
				p.changeobj(obj);
				return i;
			     }
			    else{
				in =in+ 1*sdbm(str,this.size);
				int ind=(int)(in%this.size);
					if(ind==index){
					return -2;//
					}
					else{
					index=ind;
					}
					i++;
			    }
			}
		}
		
		return -1;
		
	}



	public int delete(K key) {
		int i=1;
		String str =key.toString();
		long in =djb2(str,this.size);
		int index=(int)(in%this.size);
		//System.out.println(table[index].type);
		while(this.table[index]!=null){
			if(i>this.size){
            	break;
            }
			if(table[index].type==1){
				in =in+ 1*sdbm(str,this.size);
				int ind=(int)(in%this.size);
				if(ind==index){
					return -2;//
				}
				else{
					index=ind;
				}
				i++;
			}
			else{
			pair<K,T> p=table[index];
			String str1=p.key.toString();
			if(str1.equals(str)){
				
				p.chtype();
				
				return i;
			}
			else{
				in =in+ 1*sdbm(str,this.size);
				int ind=(int)(in%this.size);
				if(ind==index){
					return -2;//
				}
				else{
					index=ind;
				}
				i++;
			}
			}
		}
		
		return -1;
		
		
		
	}


	
	public boolean contains(K key) {
		int i=1;
		String str =key.toString();
		long in =djb2(str,this.size);
		int index=(int)(in%this.size);
		
		while(this.table[index]!=null){
			if(i>this.size){
            	break;
            }
			if(table[index].type==1){
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				
				i++;
			}
			//System.out.println(table[index].type);
			else{
			pair<K,T> p=table[index];
			String str1=p.key.toString();
			if(str1.equals(str)){
				
				return true;
			}
			else{
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				
				i++;
			}
			}
		}
		
		
		return false;
	}


	
	public T get(K key) throws NotFoundException {
		int i=1;
		String str =key.toString();
		long in =djb2(str,this.size);
		int index=(int)(in%this.size);
		while(this.table[index]!=null){
			if(i>this.size){
            	break;
            }
			if(table[index].type==1){
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				
				i++;
			}
			else{
			pair<K,T> p=table[index];
			String str1=p.key.toString();
			if(str1.equals(str)){
				
				return p.obj;
			}
			else{
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				
				i++;
			}
			}
		}
		
		
		throw new NotFoundException();
	}


	
	public String address(K key) throws NotFoundException {
		int i=1;
		String str =key.toString();
		long in =djb2(str,this.size);
		int index=(int)(in%this.size);
		//System.out.print(str);
		while(this.table[index]!=null){
			if(table[index].type==1){
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				
				i++;
			}
			else{
			pair<K,T> p=table[index];
			String str1=p.key.toString();
			if(str1.equals(str)){
				
				return Integer.toString(index);
			}
			else{
				
				in =in+ 1*sdbm(str,this.size);
				index=(int)(in%this.size);
				//System.out.print(index+"h");
				i++;
			}
			}
			if(i>this.size){
				break;
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
	public static long sdbm(String str, int hashtableSize) { 
	    long hash = 0; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash; 
	    } 
	    return Math.abs(hash) % (hashtableSize - 1) + 1; 
	}


}
