package PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	List<HeapNode<T>> arr;
	//int size;
	public MaxHeap(){
		//this.size=size;
		arr=new ArrayList<HeapNode<T>>();
	}
    public int currentsize=0;
    void swap(HeapNode<T> h1,HeapNode<T> h2){
    	T temp=h1.value;
    	int t=h1.number;
    	h1.value=h2.value;
    	h1.number=h2.number;
    	h2.value=temp;
    	h2.number=t;
    }
    HeapNode<T> parent(int s){
    	int index=(s-1)/2;
    	if(s==0){
    		return null;
    	}
    	if(index>=0){
    		//System.out.println(index);
    		return arr.get(index);
    	}
    	return null;
    	
    }
    int  no_of_element=0;
    public void insert(T element) {
    	no_of_element++;
    	HeapNode<T> h=new HeapNode(element);
    	h.number=no_of_element;
    	int current=currentsize;
    	
    	arr.add(currentsize++,h);
    	
    	//System.out.println(parent(current).value.compareTo(arr.get(current).value));
    	
    	while(parent(current)!=null&&parent(current).compareTo(arr.get(current))<0){
    		//System.out.println(arr.get(current).value.toString()+"  "+parent(current).value.toString());
    		//System.out.print(parent(current).value+" ");
    		//System.out.println(arr.get(current).value+" ");
    		if(parent(current).compareTo(arr.get(current))==0){
    		//	System.out.print("q ");
    			if(parent(current).number>arr.get(current).number){
    				swap(arr.get(current),parent(current));
    				if(current==0){
    					break;
    				}
    			//	System.out.print(current);
    	    		current=(current-1)/2;
    	    	
    			}
    			else{
    				break;
    			}
    			
    			
    			
    		}
    		else if(parent(current).compareTo(arr.get(current))<0){
    		//	System.out.print("");
    		swap(arr.get(current),parent(current));
    		current=(current-1)/2;
    		}
    		//swap(arr.get(current),parent(current));
    		//System.out.println(arr.get(current).value.toString()+"  "+parent(current).value.toString());
    		
    		
    	}
    //	for(int i=0;i<currentsize;i++){
    //		System.out.println(arr.get(i).value);
    //	}
    //	return;
    	
    	
    }

    void maximize(int pos){
    	  //
    	if(pos>=(currentsize-1)/2&&pos<=currentsize){
    		return;
    	}
    	else{
    		if(arr.get(pos).compareTo(arr.get(2*pos+1))<0
    				||arr.get(pos).compareTo(arr.get(2*pos+2))<0
    				){
    			
    			if(arr.get(2*pos+1).compareTo(arr.get(2*pos+2))>0){
    				
    				swap(arr.get(pos),arr.get(2*pos+1));
    				maximize(2*pos+1);
    			}
    			else if(arr.get(2*pos+1).compareTo(arr.get(2*pos+2))==0){
    				//System.out.print("h ");
    				if(arr.get(2*pos+1).number<arr.get(2*pos+2).number){
    					swap(arr.get(pos),arr.get(2*pos+1));
        				maximize(2*pos+1);
    				}
    				else{
    					swap(arr.get(pos),arr.get(2*pos+2));
        				maximize(2*pos+2);
    				}
    			}
    			else{
    				swap(arr.get(pos),arr.get(2*pos+2));
    				maximize(2*pos+2);
    			}
    		}
    	}
    }
    public T extractMax() {
    	
    	
    	if(currentsize>0){
    		if(currentsize==2){
    			HeapNode<T> f1=arr.get(0);
    			HeapNode<T> f2=arr.get(1);
    			if(f2.compareTo(f1)>0){
    				swap(f1,f2);
    				currentsize--;
    				arr.remove(0);
    				return f1.value;
    				
    			}
    			
    			else if(f2.compareTo(f1)==0){
    				if(f2.number<f1.number){
    					swap(f1,f2);
        				currentsize--;
        				arr.remove(0);
        				return f1.value;
    				}
    			}
    			
    		}
    		
    		HeapNode<T> f=arr.get(0);
    		
    		arr.set(0,arr.get(currentsize-1));
    		arr.remove(--currentsize);
    		
    		
    			maximize(0);
    		
    		
    			return f.value;
    		
    		
    		
    	}
    	return null;
    }

}