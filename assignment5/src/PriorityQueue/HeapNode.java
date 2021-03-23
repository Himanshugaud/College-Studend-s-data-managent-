package PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class HeapNode<T extends Comparable<T>> {
	T value;
	int number=0;
	
	HeapNode(T value){
		this.value=value;
		
	}
	public int compareTo(HeapNode<T> n){
		if(value.compareTo(n.value)!=0){
			//System.out.print("....");
			return value.compareTo(n.value);
		}
		
		return n.number-this.number;
	}
	
}
