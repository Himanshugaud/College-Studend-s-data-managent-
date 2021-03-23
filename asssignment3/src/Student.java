
public class Student implements Student_ {

	String fname;
	String lname;
	String hostel;
	String department;
	String cgpa;
	public Student(String f,String l,String h,String d,String c){
		this.fname=f;
		this.lname=l;
		this.hostel=h;
		this.department=d;
		this.cgpa=c;
	}
	
	public String fname() {
		
		return this.fname;
	}
	public String lname() {
		
		return this.lname;
	}
	public String hostel() {
		
		return this.hostel;
	}
	public String department() {
		
		return this.department;
	}
	public String cgpa() {
		
		return this.cgpa;
	}

}
