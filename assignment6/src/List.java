


public class List<V extends Comparable<V>> {

	int currentSize=0;
	//int capacity=100;
	Node<V> head;
	Node<V> last;
	List(){
		head=null;
		last=null;	
	}
	
	public void add(V value){
		//System.out.println("a");
		if(currentSize==0){
			
			head=new Node(value);
			last=head;
			
		}
		else{
		last.next=new Node(value);
		last=last.next;
		}
		currentSize++;
	}
	
	public V get(V value){
		Node<V> pointer= head;
		Node<V> n=new Node(value);
		while(pointer!=null){
			//
			if(pointer.value.compareTo(n.value)==0){
				//System.out.println("a");
				return pointer.value;
			}
			pointer=pointer.next;
		}
		return null;
	}
	public V head(){
		if(head!=null){
		return head.value;
		}
		
		return null;
	}
	
	
	
}
