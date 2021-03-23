package ProjectManagement;

public class Job implements Comparable<Job> {

    String runtime;
    String priority;
    String name;
    String project;
    String user;
    int id;
    int start_time;
    int end_time=0;
    int status;
    Job(String n,String proj,String user,String rt,String pri,int st,int id){
    	this.runtime=rt;
    	this.priority=pri;
    	this.name=n;
    	this.project=proj;
    	this.status=0;
    	this.user=user;
    	this.start_time=st;
    	this.id=id;
    }
    public int compareTo(Job job) {
    	int p=Integer.parseInt(priority);
    	int p1=Integer.parseInt(job.priority);
        return p-p1;
    }
    
    public String toString(){
    	return this.name;
    }
}