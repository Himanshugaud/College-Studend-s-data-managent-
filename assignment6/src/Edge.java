
public class Edge implements Comparable<Edge>, EdgeInterface {

	Point A;
	Point B;
	List<Triangle> adj_tri;
	float length=0;
	
	Edge(float x1,float y1,float z1,float x2,float y2,float z2){
		A=new Point(x1,y1,z1);
		B=new Point(x2,y2,z2);
		float x=(x1-x2)*(x1-x2);
		float y=(y1-y2)*(y1-y2);
		float z=(z1-z2)*(z1-z2);
		length =squareRoot(x+y+z);
		this.adj_tri=new List();
	}
	public PointInterface[] edgeEndPoints() {
		PointInterface[] pi={(PointInterface)A,(PointInterface)B};
		return pi;
	}
	
	public float length(){
		//System.out.println(length);
		return this.length;
	}

	float squareRoot(float n) 
	    { 
	        /*We are using n itself as initial approximation 
	          This can definitely be improved */
	        float x = n; 
	        float y = 1; 
	        float e = (float)0.000001; /* e decides the accuracy level*/
	        while (x - y > e) { 
	            x = (x + y) / 2; 
	            y = n / x; 
	        } 
	        return x; 
	    }
	
	
	
	public void print(){
		System.out.print("[");
		A.print();
		System.out.print(" ");
		B.print();
		System.out.print("]");
	}
	
	public int compareTo(Edge e) {
		if((this.A.compareTo(e.A)==0&&this.B.compareTo(e.B)==0)||(this.A.compareTo(e.B)==0&&this.B.compareTo(e.A)==0)){
            // System.out.println();
			return 0;
		}
		else if(this.length!=e.length){
			if(this.length>e.length){
				return 1;
			}
			else{
				return -1;
			}
		}
		return 1;
	}
	
	public Point other_point(Point p){
		if(A.compareTo(p)==0){
			return B;}
		
		else {
			return A;
		}
	}
	
	public String toString(){
		return A.toString()+" "+B.toString();
	}
	
	
}
