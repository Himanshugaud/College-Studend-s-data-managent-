package ProjectManagement;
import java.util.Queue;
import java.util.LinkedList;
public class User implements Comparable<User> {


    Queue<String> jobs;
	String name;
	public User(String name){
		this.name=name;
		jobs=new LinkedList();
	}
    public int compareTo(User user) {
        return this.name.compareTo(user.name);
    }
    public String toString(){
    	return this.name;
    }
    public Queue<String> getjobs(){
    	return this.jobs;
    }
}
