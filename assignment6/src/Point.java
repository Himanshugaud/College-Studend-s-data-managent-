
public class Point implements Comparable<Point>, PointInterface {

	float X;
	float Y;
	float Z;
	float[] coordinates=new float[3];
	int mark=-1;
	List<Edge> adjacent;
	List<Triangle> adj_triangles;
	Point(float x,float y,float z){
		
		X=x;Y=y;Z=z;
		coordinates[0]=X;coordinates[1]=Y;coordinates[2]=Z;
		adjacent=new List();
		adj_triangles=new List();
		
	}
	public float getX() {
		
		return X;
	}
	public float getY() {
		// TODO Auto-generated method stub
		return Y;
	}
	public float getZ() {
		// TODO Auto-generated method stub
		return Z;
	}
	public float[] getXYZcoordinate() {
		float[] coordinate={X,Y,Z};
		return coordinate;
	}
	
	
	@Override
	public int compareTo(Point p) {
		if(X==p.X){
			if(Y==p.Y){
				if(Z==p.Z){return 0;}
				else if(Z>p.Z){return 1;}
				else{return -1;}
			}
			else if(Y>p.Y){return 1;}
			else{return -1;}	
		}
		else if(X>p.X){return 1;}
		
		return -1;
	}
	
	public void print(){
	//	System.out.print("("+(int)X+","+(int)Y+","+(int)Z+")");
		System.out.printf("%.0f", X);
		System.out.print(" ");
		System.out.printf("%.0f", Y);
		System.out.print(" ");
		System.out.printf("%.2f", Z);
		
	}
	
	public List<Point> adj_points(){
		List<Point> list=new List();
		Point p=new Point(X,Y,Z);
		Node<Edge> n=this.adjacent.head;
		while(n!=null){
			if(list.get(n.value.other_point(p))==null){
			list.add(n.value.other_point(p));
			}
			n=n.next;
		}
		return list;
	}
	
	public String toString(){
		return "("+X+","+Y+","+Z+")";
	}
	
	
	
	

}
