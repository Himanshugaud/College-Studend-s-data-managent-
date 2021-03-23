
public class node<K,T> {

	node<K,T> left;
	node<K,T> right;
	
	
	
	K key;
	T obj;
	
	public node(K key,T obj){
		this.key=key;
		this.obj=obj;
		this.left=null;
		this.right=null;
		
	}
	public void putl(node<K,T> l){
		this.left=l;
	}
	public void putr(node<K,T> r){
		this.right=r;
	}
	
	
	
}
