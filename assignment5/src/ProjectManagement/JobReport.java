package ProjectManagement;

public class JobReport implements JobReport_ {

	String name;
	String user;
	String p_name;
	String priority;
	String runtime;
	int budget;
	int arrival_time;
	int completion_time;
	public JobReport(String name,String user,String p_name,String b,int time,String priority,String rt){
		this.name=name;
		this.user=user;
		this.p_name=p_name;
		this.budget=Integer.parseInt(b);
		this.arrival_time=time;
		this.completion_time=-1;
		this.priority=priority;
		this.runtime=rt;
		//this.completion_time=Integer.parseInt(ct);
	}
	
	
	public String user() {
		
		return user;
	}

	
	public String project_name() {
	
		return p_name;
	}


	public int budget() {
		
		return budget;
	}

	@Override
	public int arrival_time() {
		// TODO Auto-generated method stub
		return arrival_time;
	}

	@Override
	public int completion_time() {
		if(completion_time==-1){return  0;}
		return completion_time;
	}
	public String toString() {
		
		return "Job{user='"+user+"', project='"+p_name +", priority="+priority+", name='"+name+"'}";
	}
	

}
