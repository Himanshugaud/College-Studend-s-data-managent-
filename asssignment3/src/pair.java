
public class pair<A,B> implements Comparable<pair<A,B>>{
	A key;
	B obj;
    int type;
	public pair(A key,B obj){
		this.key=key;
		this.obj=obj;
		this.type=0;
		
	}
	public void changeobj(B obj){
		this.obj=obj;
	}
	public void chtype(){
		this.type=1;
	}
	public String toString(){
		return key.toString()+obj.toString();
		}


	
	
	public int compareTo(pair<A,B> o) {
		if(key.toString().compareTo(o.key.toString())!=0){
			return key.toString().compareTo(o.key.toString());
		}
		else{
		    return obj.toString().compareTo(o.obj.toString());
		}
	}
	
	public boolean equals(pair<A,B> o){
		
		return key.toString().equals(o.key.toString());
	}

}
