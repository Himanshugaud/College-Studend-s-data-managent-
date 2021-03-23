 package Trie;

public class Person {

	String name,ph;
    public Person(String name, String phone_number) {
    	this.name=name;
    	this.ph=phone_number;
    }

    public String getName() {
        return this.name;
    }
    public String toString(){
    	return "[Name: "+name+", Phone="+ph+"]";
    }
    
    public int compareTo(Person p){
    	return name.compareTo(p.name);
    }
}
