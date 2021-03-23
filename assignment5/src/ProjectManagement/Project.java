package ProjectManagement;
import java.util.Queue;
import java.util.LinkedList;
//import PriorityQueue.MaxHeap;


public class Project {
	String name;
	String budget;
	String priority;
	
	Queue<String> jobs;
	
	//MaxHeap<Job> heap;
	
	public Project(String n,String p,String b){
		this.name=n;
		this.budget=b;
		this.priority=p;
		jobs=new LinkedList();
		//this.heap= new MaxHeap();
	}
	public String toString(){
		return this.name;
	}
	
	public Queue<String> getjobs(){
		return this.jobs;
	}
	
	
}
