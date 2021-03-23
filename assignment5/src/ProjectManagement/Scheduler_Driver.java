package ProjectManagement;
import PriorityQueue.MaxHeap;
//import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
//import Trie.Person;
import Trie.Trie;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

//import java.io.*;
//import java.net.URL;
import java.util.ArrayList;

public class Scheduler_Driver extends Thread implements SchedulerInterface {

	 MaxHeap<Job> jobs=new MaxHeap();
	   // Queue<Job> jobq=new LinkedList();
	    Queue<Job> completejobs=new LinkedList();
	    Queue<Job> notcompjobs=new LinkedList();
	    
	    int noofjobs=0;
	    int jobid=1;
	   // RBTree<String,Job> rmjobs=new RBTree();
	    
	    
	    Trie<Job> rbjobs=new Trie();
	    
	    Trie<Project> projects=new Trie();
	    
	    Trie<User> users = new Trie();
	  //  Queue<UserReport> urq=new LinkedList();
	    MaxHeap<UserReport> urmh=new MaxHeap();
	    int globletime=0;
	    String executionline=new String();
	    
	    Trie<JobReport> jr=new Trie();     //comtains complition info
	    Queue<JobReport> jrq=new LinkedList();        //comtains complition info
	    Queue<JobReport> jrcq=new LinkedList();      //contains conplition info

    public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


          
            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
    	
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
            	
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
            	
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
            	
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
            	
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
    //	System.out.println("Top query");
    	ArrayList<UserReport_> ret=new ArrayList<UserReport_>();
 	    int index=0;
 	   MaxHeap<UserReport> new_urmh=new MaxHeap();
    	while(urmh.currentsize!=0){
    		
    		UserReport urep=urmh.extractMax();
    		if(index<top){
    			ret.add(index,(UserReport_)urep);
    			//System.out.println(urep.user+" "+urep.consumed);
    		}
    		new_urmh.insert(urep);
    		
	
    	}
    	urmh=new_urmh;
    	
        return ret;
    }



    @Override
    public void timed_flush(int waittime) {

    //	System.out.println("Flush query");
    	MaxHeap<Job> new_jobs=new MaxHeap();
    	int timehere=globletime;
    	while(jobs.currentsize!=0){
    		Job job=jobs.extractMax();
    		
    		int wait_time=timehere-job.start_time;
    		if(job.status==0){
    		 if(wait_time>=waittime){
    			 Project p=projects.search(job.project).getValue();
    			 int runtime=Integer.parseInt(job.runtime);
				 int budget=Integer.parseInt(p.budget);
    			 if(budget-runtime>=0){
    			    // job.priority=Integer.toString(9999);
    			     
    				
    			     p.budget=Integer.toString(budget-runtime);
    		    	//	System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		    	//	System.out.println(executionline);
    		    		
    		    		JobReport j2=jr.search(job.name).getValue();
    		    		j2.completion_time=globletime+runtime;
    		    		jrcq.add(j2);
    		    		
    		    		for(JobReport jobrep:jrq){
    		    			if(jobrep.name.equals(job.name)){
    		    				if(jobrep.completion_time==-1){
    		    				jobrep.completion_time=globletime+runtime;
    		    				break;
    		    				}
    		    				
    		    			}
    		    		}
    		    		
    		    		//updating max heap for top consumer
    		    		MaxHeap<UserReport> new_urmh=new MaxHeap();
    		    	
    		    		while(urmh.currentsize!=0){
    		    			UserReport userrep=urmh.extractMax();
    		    			if(userrep.user.equals(job.user)){
    		    				userrep.consumed=userrep.consumed()+runtime;
    		    				
    		    			}
    		    			new_urmh.insert(userrep);
    		    		}
    		    		urmh=new_urmh;
    		    		
    		    		//updating for job status for order in flush 
    		    	/*	for(Job job1:jobq){
    		    			if(job1.name.equals(job.name)){
    		    				job1.status=1;
    		    			break;
    		    			}
    		    		}*/
    		    		
    		    		noofjobs--;
    		    		job.status=1;
    		    		globletime=globletime+runtime;
    		    		job.end_time=globletime;
    		    		
    		    		completejobs.add(job);
    			 //  System.out.println("Job{user='"+job.user+"', project='"+job.project+"', jobstatus="+"REQUESTED"+", execution_time="+job.runtime+", end_time="+job.end_time+", priority="+job.priority+", name='"+job.name+"'}");
    			 }
    			 
    			 else{
    				 new_jobs.insert(job);
    			 }
    			 
    		 }
    		 else{
    		//if(jobsearch.search(job.name)!=null)
    		    new_jobs.insert(job);
    		    }
    		}
    		
    	}
    //	while(new_jobs.currentsize!=0){
    //		jobs.insert(new_jobs.extractMax());
    //	}
    	jobs=new_jobs;
    	
    	
    }
    

    private ArrayList<JobReport_> handle_new_priority(String s) {
    //	System.out.println("Priority query");
    	ArrayList<JobReport_> ret=new ArrayList<JobReport_>();
    	   int index=0;
    	for(JobReport jobrep:jrq){
    		//JobReport jobrep;
    		
    		//System.out.println(jobrep.name+" "+jobrep.completion_time+" "+jobrep.priority+" "+s);
    		//System.out.println(jobrep.priority.compareTo(s));
    		if(jobrep.completion_time==-1&&Integer.parseInt(jobrep.priority)>=Integer.parseInt(s)){
    			//System.out.println(".......................");
    			ret.add(index++,(JobReport_)jobrep);
    			//System.out.println(jobrep.toString());
    			
    		}
    	}
    	return ret;
    	
       // return null;
    }

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
    //	System.out.println("Project User query");
       ArrayList<JobReport_> ret=new ArrayList<JobReport_>();
   	   int index=0;
   	   for(JobReport jrep:jrcq){
   		   if(jrep.p_name.equals(cmd[1])&&jrep.user.equals(cmd[2])&&jrep.arrival_time>=Integer.parseInt(cmd[3])&&jrep.arrival_time<=Integer.parseInt(cmd[4])){
   			 ret.add(index++,(JobReport_)jrep);
   			//System.out.println(jrep.toString());
   		   }
   	   }
   	   
   	   
   	   
   	   
   	   Project proj=projects.search(cmd[1]).getValue();
   	   if(proj!=null){
   		   Queue<String> jobs=proj.jobs;
   		   for(String job:jobs){
   			   
   			   JobReport jobrep=jr.search(job).getValue();
   			   if(jobrep.user.equals(cmd[2])&&jobrep.completion_time==-1&&jobrep.arrival_time>=Integer.parseInt(cmd[3])&&jobrep.arrival_time<=Integer.parseInt(cmd[4])){
   				   ret.add(index++,(JobReport_)jobrep);
   				//System.out.println(jobrep.toString());
   			   }
   		   }
   		  
   	   }
   	   return ret;
       // return null;
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
    //	System.out.println("User query");
    	ArrayList<JobReport_> ret=new ArrayList<JobReport_>();
   	   int index=0;
   	   
   	   User user=users.search(cmd[1]).getValue();
   	   if(user!=null){
   		   Queue<String> jobs=user.jobs;
   		   for(String job:jobs){
   			  
   			   JobReport jobrep=jr.search(job).getValue();
   			   if(jobrep.arrival_time>=Integer.parseInt(cmd[2])&&jobrep.arrival_time<=Integer.parseInt(cmd[3])){
   				   ret.add(index++,(JobReport_)jobrep);
   				//System.out.println(jobrep.toString());
   			   }
   			   
   		   }
   		  
   	   }
   	      return ret;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
    //	System.out.println("Project query");
    	   ArrayList<JobReport_> ret=new ArrayList<JobReport_>();
    	   int index=0;
    	   Project proj=projects.search(cmd[1]).getValue();
    	   if(proj!=null){
    		   Queue<String> jobs=proj.jobs;
    		   for(String job:jobs){
    			   
    			   JobReport jobrep=jr.search(job).getValue();
    			   
    			   if(jobrep.arrival_time>=Integer.parseInt(cmd[2])&&jobrep.arrival_time<=Integer.parseInt(cmd[3])){
    				   ret.add(index++,(JobReport_)jobrep);
    				  // System.out.println(jobrep.toString());
    			   }
    			   
    		   }
    		  // return ret;
    	   }
    	
    	
    	
    	   return ret;
    }




    public void schedule() {
    	
    	Job j=jobs.extractMax();
    	//System.out.println(j.priority);
    	noofjobs--;
    	//System.out.println(j.priority);
    	if(j==null){
    		System.out.println(executionline);
    		return;
    	}
    	//System.out.println(j.priority);
    	
    	
    	int runtime=Integer.parseInt(j.runtime);
    	System.out.println("Executing: "+j.name+" from: "+j.project);
    	Project p=projects.search(j.project).getValue();
    
    	int budget=Integer.parseInt(p.budget);
    	
    	if(budget<runtime){
    		System.out.println("Un-sufficient budget.");
    		
    		
    		notcompjobs.add(j);
    		//add j in not able to non exicutiable jobs;
    		
    		
    		
    		schedule();
    	}
    	else{
    		p.budget=Integer.toString(budget-runtime);
    		System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		System.out.println(executionline);
    		
    		JobReport j2=jr.search(j.name).getValue();
    		j2.completion_time=globletime+runtime;
    		jrcq.add(j2);
    		
    		for(JobReport jobrep:jrq){
    			if(jobrep.name.equals(j.name)&&jobrep.completion_time==-1){
    				jobrep.completion_time=globletime+runtime;
    				break;
    			}
    		}
    		
    		//updating max heap for top consumer
    		MaxHeap<UserReport> new_urmh=new MaxHeap();
    	
    		while(urmh.currentsize!=0){
    			UserReport userrep=urmh.extractMax();
    			if(userrep.user.equals(j.user)){
    				userrep.consumed=userrep.consumed()+runtime;
    				
    			}
    			new_urmh.insert(userrep);
    		}
    		urmh=new_urmh;
    		
    		//updating for job status for order in flush 
    	/*	for(Job job:jobq){
    			if(job.name.equals(j.name)){
    				job.status=1;
    			break;
    			}
    		}*/
    		
    		
    		j.status=1;
    		globletime=globletime+runtime;
    		j.end_time=globletime;
    		
    		completejobs.add(j);
    		//  add in  queue of completed projects 
    		
    		
    		return;
    		
    	}
    	
    	
    }
    
    public void run() {
        // till there are JOBS
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+noofjobs);
    	executionline="System execution completed";
        schedule();
    }

    public void run_to_completion() {

    	  while(jobs.currentsize!=0){
          	run();
          }
    	
    }

    public void print_stats() {

    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+completejobs.size());
    	while(completejobs.peek()!=null){
    		Job j=completejobs.remove();
    		String st=new String();
    		if(j.status==1){
    			st="COMPLETED";
    		}
    		else{
    			st="REQUESTED";
    		}
    		System.out.println("Job{user='"+j.user+"', project='"+j.project+"', jobstatus="+st+", execution_time="+j.runtime+", end_time="+j.end_time+", name='"+j.name+"'}");
    	}
    	System.out.println("------------------------");
    	
    	
    	
    	System.out.println("Unfinished jobs: ");
    	MaxHeap<Job> ufjobs=new MaxHeap();
    	for( Job j:notcompjobs){
    		ufjobs.insert(j);
    	}
    //	System.out.println("Total jobs done: "+completejobs.size());
    	int count=0;
    	while(ufjobs.currentsize!=0){
    		Job j=ufjobs.extractMax();
    		//Job j=notcompjobs.remove();
    		
    		
    		
    		System.out.println("Job{user='"+j.user+"', project='"+j.project+"', jobstatus="+"REQUESTED"+", execution_time="+j.runtime+", end_time="+"null"+", name='"+j.name+"'}");
    		count++;
    		
    	}
    	System.out.println("Total unfinished jobs: "+count);
    	System.out.println("--------------STATS DONE---------------");
    }

    public void handle_add(String[] cmd) {

    	Project p=projects.search(cmd[1]).getValue();
    	if(p==null){
    		System.out.println("No such project exists. "+cmd[1]);
    		return;
    	}
    	else{
    		System.out.println("ADDING Budget");
    		int b=Integer.parseInt(cmd[2]);
    		int bp=Integer.parseInt(p.budget);
    		p.budget=Integer.toString(bp+b);
    	
    		// add jobs into heap
    		Queue<Job> q=new LinkedList();
    		for(Job j:notcompjobs){
    			//System.out.println("...");
    			//System.out.println();
    			//Job j=notcompjobs.remove();
    			//System.out.print(j.project+" ");
    			//System.out.print(j.name+".......");
    			if(j.project.equals(p.name)){
    				//System.out.print("1");
    				jobs.insert(j);
    				//System.out.print(jobs.currentsize);
    				noofjobs++;
    			}
    			//Queue<Job> q=new LinkedList();
    			else{
    				q.add(j);
    			}
    			
    		}
    		notcompjobs=q;
    		
    		
    	}
    	
    	
    }

    public void handle_empty_line() {

    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+noofjobs);
    	executionline="Execution cycle completed";
    	schedule();
    	
    }


    public void handle_query(String key) {

    	System.out.println("Querying");
    	if(rbjobs.search(key)==null){
    		System.out.println("Doesnotexists: NO SUCH JOB");
    		return;
    	}
    	Job job=rbjobs.search(key).getValue();
    	
    	if(job==null){
    		System.out.println("Doesnotexists: NO SUCH JOB");
    		return;
    	}
    	
    	if(job.status==0){
    		System.out.println(key+": NOT FINISHED");
    	}
    	else{
    		System.out.println(key+": COMPLETED");
    	}
    }

    public void handle_user(String name) {

    	System.out.println("Creating user");
    	User user=new User(name);
    	users.insert(name, user);
    	
    	UserReport ur=new UserReport(user.name);
    	//urq.add(ur);
    	urmh.insert(ur);
    	
    	
    	//users.print();
    	//users.search(name).getValue();
    	//System.out.println(users.search(name).getValue().name);
    }

    public void handle_job(String[] cmd) {
    	//users.print();

    	System.out.println("Creating job");
    	Project proj=projects.search(cmd[2]).getValue();
    	//System.out.println(cmd[3]);
    //	System.out.println(users.search(cmd[3]).getValue().name);
    	if(users.search(cmd[3])==null){
    		System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	User u=users.search(cmd[3]).getValue();
    	if(u==null){
    		System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	if(proj!=null){
    		//System.out.println("Creating job");
    		Job job=new Job(cmd[1],cmd[2],cmd[3],cmd[4],proj.priority,globletime,jobid++);
    		JobReport jobrep=new JobReport(job.name,job.user,job.project,proj.budget,globletime,proj.priority,job.runtime);
    		jr.insert(job.name,jobrep);
    		jrq.add(jobrep);
    		proj.jobs.add(job.name);
    		u.jobs.add(job.name);
    		//System.out.println("");
    		jobs.insert(job);
    	//	jobq.add(job);
    		//System.out.println("1");
    		
    		noofjobs++;
    		rbjobs.insert(cmd[1], job);
    	}
    	else{
    		System.out.println("No such project exists. "+cmd[2]);
    	}
    }

    public void handle_project(String[] cmd) {

    	System.out.println("Creating project");
    	Project proj=new Project(cmd[1],cmd[2],cmd[3]);
    	projects.insert(cmd[1],proj);
    	
    }

    public void execute_a_job() {
    	Job j=jobs.extractMax();
    	//System.out.println(j.priority);
    	noofjobs--;
    	//System.out.println(j.priority);
    	if(j==null){
    		//System.out.println(executionline);
    		return;
    	}
    	//System.out.println(j.priority);
    	
    	
    	int runtime=Integer.parseInt(j.runtime);
    //	System.out.println("Executing: "+j.name+" from: "+j.project);
    	Project p=projects.search(j.project).getValue();
    
    	int budget=Integer.parseInt(p.budget);
    	
    	if(budget<runtime){
    	//	System.out.println("Un-sufficient budget.");
    		
    		
    		notcompjobs.add(j);
    		//add j in not able to non exicutiable jobs;
    		
    		
    		
    		execute_a_job();
    	}
    	else{
    		p.budget=Integer.toString(budget-runtime);
    		//System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    		//System.out.println(executionline);
    		
    		JobReport j2=jr.search(j.name).getValue();
    		j2.completion_time=globletime+runtime;
    		jrcq.add(j2);
    		
    		for(JobReport jobrep:jrq){
    			if(jobrep.name.equals(j.name)&&jobrep.completion_time==-1){
    				jobrep.completion_time=globletime+runtime;
    				break;
    			}
    		}
    		
    		//updating max heap for top consumer
    		MaxHeap<UserReport> new_urmh=new MaxHeap();
    	
    		while(urmh.currentsize!=0){
    			UserReport userrep=urmh.extractMax();
    			if(userrep.user.equals(j.user)){
    				userrep.consumed=userrep.consumed()+runtime;
    				
    			}
    			new_urmh.insert(userrep);
    		}
    		urmh=new_urmh;
    		
    		//updating for job status for order in flush 
    	/*	for(Job job:jobq){
    			if(job.name.equals(j.name)){
    				job.status=1;
    			break;
    			}
    		}*/
    		
    		
    		j.status=1;
    		globletime=globletime+runtime;
    		j.end_time=globletime;
    		
    		completejobs.add(j);
    		//  add in  queue of completed projects 
    		
    		
    		return;
    		
    	}
    	
    	
    	

    }
    
    
    public void timed_handle_user(String name){
    	//System.out.println("Creating user");
    	User user=new User(name);
    	users.insert(name, user);
    	
    	UserReport ur=new UserReport(user.name);
    	//urq.add(ur);
    	urmh.insert(ur);
    	
    }
    public void timed_handle_job(String[] cmd){
    //	System.out.println("Creating job");
    	Project proj=projects.search(cmd[2]).getValue();
    	//System.out.println(cmd[3]);
    //	System.out.println(users.search(cmd[3]).getValue().name);
    	if(users.search(cmd[3])==null){
    		//System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	User u=users.search(cmd[3]).getValue();
    	if(u==null){
    		//System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	if(proj!=null){
    		//System.out.println("Creating job");
    		Job job=new Job(cmd[1],cmd[2],cmd[3],cmd[4],proj.priority,globletime,jobid++);
    		JobReport jobrep=new JobReport(job.name,job.user,job.project,proj.budget,globletime,proj.priority,job.runtime);
    		jr.insert(job.name,jobrep);
    		jrq.add(jobrep);
    		proj.jobs.add(job.name);
    		u.jobs.add(job.name);
    		//System.out.println("");
    		jobs.insert(job);
    	//	jobq.add(job);
    		//System.out.println("1");
    		
    		noofjobs++;
    		rbjobs.insert(cmd[1], job);
    	}
    	else{
    		//System.out.println("No such project exists. "+cmd[2]);
    	}
    }
    public void timed_handle_project(String[] cmd){
    	//System.out.println("Creating project");
    	Project proj=new Project(cmd[1],cmd[2],cmd[3]);
    	projects.insert(cmd[1],proj);
    }
    public void timed_run_to_completion(){
    	  while(jobs.currentsize!=0){
    		//  System.out.println("Running code");
    	    //	System.out.println("Remaining jobs: "+noofjobs);
    	    //	executionline="System execution completed";
    	        execute_a_job();
          }
    }
    
    
    
}
