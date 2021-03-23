package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
    	this.name=trim;
    	this.marks=parseInt;
    }

    public String toString(){
    	return "Student{name='"+name+"', marks="+marks+"}";
    }
    
    public int compareTo(Student student) {
    	
        return marks.compareTo(student.marks);
    	
    	
    }

    public String getName() {
        return name;
    }
}
