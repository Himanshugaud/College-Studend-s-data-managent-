
public class Triangle implements Comparable<Triangle>,TriangleInterface {

	Point A;
	Point B;
	Point C;
	int id=0;
	PointInterface[] points=new Point[3];
	Edge eab;
	Edge ebc;
	Edge eca;
	float[] cordinates;
	EdgeInterface[] edges=new Edge[3];
	int mark=-1;
	int weight=0;
	boolean reached=false;
	boolean reach2=false;
	int weight2=0;
	//int mark2=0;
	List<Triangle> neighbors;
	
	Triangle(float[] cds){
		cordinates=cds;
		A=new Point(cds[0],cds[1],cds[2]);
		B=new Point(cds[3],cds[4],cds[5]);
		C=new Point(cds[6],cds[7],cds[8]);
		points[0]=A;
		points[1]=B;
		points[2]=C;
		
		eab=new Edge(cds[0],cds[1],cds[2],cds[3],cds[4],cds[5]);
		ebc=new Edge(cds[3],cds[4],cds[5],cds[6],cds[7],cds[8]);
		eca=new Edge(cds[6],cds[7],cds[8],cds[0],cds[1],cds[2]);
		
		edges[0]=eab;
		edges[1]=ebc;
		edges[2]=eca;
		neighbors=new List();
	}
	Triangle(float[] cds,int id){
		cordinates=cds;
		A=new Point(cds[0],cds[1],cds[2]);
		B=new Point(cds[3],cds[4],cds[5]);
		C=new Point(cds[6],cds[7],cds[8]);
		points[0]=(PointInterface)A;
		points[1]=(PointInterface)B;
		points[2]=(PointInterface)C;
		
		eab=new Edge(cds[0],cds[1],cds[2],cds[3],cds[4],cds[5]);
		ebc=new Edge(cds[3],cds[4],cds[5],cds[6],cds[7],cds[8]);
		eca=new Edge(cds[6],cds[7],cds[8],cds[0],cds[1],cds[2]);
		
		edges[0]=(EdgeInterface)eab;
		edges[1]=(EdgeInterface)ebc;
		edges[2]=(EdgeInterface)eca;
		neighbors=new List();
		this.id=id;
	}
	
	public PointInterface[] triangle_coord() {
		PointInterface[] pi={(PointInterface)A,(PointInterface)B,(PointInterface)C};
		return pi;
	}

	public boolean have(Point p){
		if(A.compareTo(p)==0){
			return true;
		}
		else if(B.compareTo(p)==0){
			return true;
		}
		else if(C.compareTo(p)==0){
			return true;
		}
		return false;
	}
	public int compareTo(Triangle t) {
		if(t.have(A)&&t.have(B)&&t.have(C)){
			return 0;
		}
		else if(id!=t.id){
			return id-t.id;
		}
		return 1;
	}
	
	public void print(){
		System.out.print("[");
		A.print();
		B.print();
		C.print();
		System.out.print("]");
	}
	
	public void mark_neighbor(int m){
		//int marked=1;
		Node<Triangle> t=neighbors.head;
		for(int i=0;i<neighbors.currentSize;i++){
			//System.out.println("...");
			if(t.value.mark==m){
				t=t.next;
			}
			else{
				t.value.mark=m;
				Triangle ths=new Triangle(cordinates);
				if(t.value.compareTo(ths)==0){
					t=t.next;
				}
				else{
					
					t.value.mark_neighbor(m);
				    t=t.next;
				}
			}
		}
		
	}
	
	public Point centroid(){
		float X=A.X+B.X+C.X;
		float Y=A.Y+B.Y+C.Y;
		float Z=A.Z+B.Z+C.Z;
		float f=(float)3;
		Point p=new Point(X/f,Y/f,Z/f);
		return p;
	}
	
	
/*	public Point update_centroid(Triangle ths,Point centroid,int m,float f3,float f4){
	//	centroid.print();
		Node<Triangle> t=neighbors.head;
		for(int i=0;i<neighbors.currentSize;i++){
			//System.out.println("...");
			if(t.value.mark==m){
				t=t.next;
			}
			else{
				t.value.mark=m;
				//Triangle ths=new Triangle(cordinates);
				Point noncom=t.value.non_comman_point(ths);
			//	float f3=(float)3;float f4=(float)4;
				if(noncom.mark!=m){
					noncom.mark=m;
					noncom.print();System.out.println();
					//System.out.println(noncom.X+"  "+(int)(f3*centroid.X+noncom.X)/f4+"   "+f3);
					centroid.X=(f3*centroid.X+noncom.X)/f4;  centroid.Y=(f3*centroid.Y+noncom.Y)/f4;  centroid.Z=(f3*centroid.Z+noncom.Z)/f4;
					//centroid=new Point((f3*centroid.X+noncom.X)/f4,(f3*centroid.Y+noncom.Y)/f4,(f3*centroid.Z+noncom.Z)/f4);
					//System.out.println(noncom.X+" x= "+(int)(f3*centroid.X+noncom.X)/f4+"   "+f3);
				     f4=f4+(float)1;f3=f3+(float)1;
				     
				}
				if(t.value.compareTo(ths)==0){
					t=t.next;
				}
				else{
					t.value.update_centroid(t.value,centroid,m,f3,f4);
					t=t.next;
				}
			}
		}
		return centroid;
	}*/
	
	public Point non_comman_point(Triangle t){
		if(A.compareTo(t.A)!=0&&A.compareTo(t.B)!=0&&A.compareTo(t.C)!=0){
			return A;
		}
		else if(B.compareTo(t.A)!=0&&B.compareTo(t.B)!=0&&B.compareTo(t.C)!=0){
			return B;
		}
		else{
			return C;
		}
	}
	
	public int count_mark_neighbor(int m,int marked){
		//int marked=1;
		Node<Triangle> t=neighbors.head;
		for(int i=0;i<neighbors.currentSize;i++){
			//System.out.println("...");
			if(t.value.mark==m){
				t=t.next;
			}
			else{
				t.value.mark=m;
				Triangle ths=new Triangle(cordinates);
				if(t.value.compareTo(ths)==0){
					t=t.next;
				}
				else{
					//marked++;
					
					marked=t.value.count_mark_neighbor(m,marked+1);
				    t=t.next;
				}
			}
		}
		
		return marked;
		
	}
	
	
	public String toString(){
		return A.toString()+" "+B.toString()+" "+C.toString();
	}
	
	
	
	
	
	
	
	

		

}
