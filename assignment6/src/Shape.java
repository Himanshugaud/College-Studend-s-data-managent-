

public class Shape implements ShapeInterface
{

	List<Triangle> triangles;
	List<Point> points;
	List<Edge> edges;
	int no_of_t=0;
	int mark=0;
	//int mark2=0;
	Shape(){
		triangles=new List();
		points=new List();
		edges=new List();
	}
	public boolean ADD_TRIANGLE(float [] triangle_coord){
		//System.out.println(triangle_coord[0]);
		Triangle t=new Triangle(triangle_coord,triangles.currentSize+1);
		
		
		
		Point p1=t.A;
		Point p2=t.B;
		Point p3= t.C;
		
		Edge e12=t.eab;
		Edge e23=t.ebc;
		Edge e31=t.eca;
		
		Point ps1=points.get(p1);Point ps2=points.get(p2);Point ps3=points.get(p3);
		Edge es12=edges.get(e12);Edge es23=edges.get(e23);Edge es31=edges.get(e31);
		//finding condition
		int no_of_commanp=0; int one=0; int two=0;int three=0,e1=0,e2=0,e3=0;
		if(ps1!=null){no_of_commanp++;one=1;}
		if(ps2!=null){no_of_commanp++;two=1;}
		if(ps3!=null){no_of_commanp++;three=1;}
		if(es12!=null){e1=1;}
		if(es23!=null){e2=1;}
		if(es31!=null){e3=1;}
		
		//System.out.println(e1.length()+" "+e2.length()+" "+e3.length());
		if((e12.length()+e23.length>e31.length)&&(e23.length()+e31.length>e12.length)&&(e31.length()+e12.length>e23.length)){
			//System.out.println("");
			if(no_of_commanp==0){
			//	System.out.println("case0");
				e12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
				edges.add(e12);edges.add(e23);edges.add(e31);
				p1.adjacent.add(e12);p1.adjacent.add(e31);
				p2.adjacent.add(e12);p2.adjacent.add(e23);
				p3.adjacent.add(e23);p1.adjacent.add(e31);
				p1.adj_triangles.add(t);p2.adj_triangles.add(t);p3.adj_triangles.add(t);
				points.add(p1);points.add(p2);points.add(p3);
				triangles.add(t);
				
			}
			else if(no_of_commanp==1){
			//	System.out.println("case1");
				if(one==1){
					e12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
					edges.add(e12);edges.add(e23);edges.add(e31);
					ps1.adjacent.add(e12);ps1.adjacent.add(e31);
					p2.adjacent.add(e12);p2.adjacent.add(e23);
					p3.adjacent.add(e23);p3.adjacent.add(e31);
					ps1.adj_triangles.add(t);p2.adj_triangles.add(t);p3.adj_triangles.add(t);
					points.add(p2);points.add(p3);
					
					triangles.add(t);
				}
				else if(two==1){
					e12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
					edges.add(e12);edges.add(e23);edges.add(e31);
					p1.adjacent.add(e12);p1.adjacent.add(e31);
					ps2.adjacent.add(e12);ps2.adjacent.add(e23);
					p3.adjacent.add(e23);p3.adjacent.add(e31);
					p1.adj_triangles.add(t);ps2.adj_triangles.add(t);p3.adj_triangles.add(t);
					points.add(p1);points.add(p3);
					
					triangles.add(t);
				}
				else if(three==1){
					e12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
					edges.add(e12);edges.add(e23);edges.add(e31);
					p1.adjacent.add(e12);p1.adjacent.add(e31);
					p2.adjacent.add(e12);p2.adjacent.add(e23);
					ps3.adjacent.add(e23);ps3.adjacent.add(e31);
					p1.adj_triangles.add(t);p2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					points.add(p1);points.add(p2);
					
					triangles.add(t);
				}
				
				
				
			}
			else if(no_of_commanp==2){
			//	System.out.println("case2");
				if(one==1&&two==1){
					//System.out.println("0");
					if(es12!=null){
						Node<Triangle> n=es12.adj_tri.head;
						while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
						}
					
					
						es12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
						edges.add(e23);edges.add(e31);
					}
					else{
						Node<Triangle> n=e12.adj_tri.head;
						while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
						}
					
					
						e12.adj_tri.add(t);e23.adj_tri.add(t);e31.adj_tri.add(t);
						edges.add(e12);edges.add(e23);edges.add(e31);
					}
					ps1.adjacent.add(e12);ps1.adjacent.add(e31);
					ps2.adjacent.add(e12);ps2.adjacent.add(e23);
					p3.adjacent.add(e23);p3.adjacent.add(e31);
					ps1.adj_triangles.add(t);ps2.adj_triangles.add(t);p3.adj_triangles.add(t);
					points.add(p3);
					
					triangles.add(t);
				}
				else if(two==1&&three==1){
					//System.out.println("1");
					if(es23!=null){
						Node<Triangle> n=es23.adj_tri.head;
						while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
						}
						e12.adj_tri.add(t);es23.adj_tri.add(t);e31.adj_tri.add(t);
						edges.add(e12);edges.add(e31);
					}
					
					else{
						Node<Triangle> n=e23.adj_tri.head;
						while(n!=null){
							n.value.neighbors.add(t);
							t.neighbors.add(n.value);
							n=n.next;
						}
						e12.adj_tri.add(t);es23.adj_tri.add(t);e31.adj_tri.add(t);
						edges.add(e12);edges.add(e23);edges.add(e31);
					}
					
					p1.adjacent.add(e12);p1.adjacent.add(e31);
					ps2.adjacent.add(e12);ps2.adjacent.add(e23);
					ps3.adjacent.add(e23);ps3.adjacent.add(e31);
					p1.adj_triangles.add(t);ps2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					points.add(p1);
					
					triangles.add(t);
				}
				else if(three==1&&one==1){
					//System.out.println("2");
					if(es31!=null){
						Node<Triangle> n=es31.adj_tri.head;
						while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
						}
					
						e12.adj_tri.add(t);e23.adj_tri.add(t);es31.adj_tri.add(t);
						edges.add(e12);edges.add(e23);
					}
					else{
						Node<Triangle> n=e31.adj_tri.head;
						while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
						}
					
						e12.adj_tri.add(t);e23.adj_tri.add(t);es31.adj_tri.add(t);
						edges.add(e12);edges.add(e23);edges.add(e31);
					}
					ps1.adjacent.add(e12);ps1.adjacent.add(e31);
					p2.adjacent.add(e12);p2.adjacent.add(e23);
					ps3.adjacent.add(e23);ps3.adjacent.add(e31);
					ps1.adj_triangles.add(t);p2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					points.add(p2);
					
					triangles.add(t);
				}
			}
			
			else if(no_of_commanp==3){
			//	System.out.println("case3");
				
				if(es12!=null&&es23!=null&&es31!=null){
					if(triangles.get(t)==null){
						Node<Triangle> n=es12.adj_tri.head;
						while(n!=null){
							n.value.neighbors.add(t);
							t.neighbors.add(n.value);
							n=n.next;
						}
						 n=es23.adj_tri.head;
						while(n!=null){
							n.value.neighbors.add(t);
							t.neighbors.add(n.value);
							n=n.next;
						}
						n=es31.adj_tri.head;
						while(n!=null){
							n.value.neighbors.add(t);
							t.neighbors.add(n.value);
							n=n.next;
						}
						es12.adj_tri.add(t);es23.adj_tri.add(t);es31.adj_tri.add(t);
						ps1.adj_triangles.add(t);ps2.adj_triangles.add(t);ps3.adj_triangles.add(t);
						triangles.add(t);
						return true;
					}
					return false;
				}
				else if(es12==null){
					Node<Triangle> n=es23.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					n=es31.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					e12.adj_tri.add(t);es23.adj_tri.add(t);es31.adj_tri.add(t);
					edges.add(e12);
					ps1.adj_triangles.add(t);ps2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					triangles.add(t);
				}
				else if(es23==null){
					Node<Triangle> n=es12.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					n=es31.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					
					es12.adj_tri.add(t);e23.adj_tri.add(t);es31.adj_tri.add(t);
					edges.add(e23);
					ps1.adj_triangles.add(t);ps2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					triangles.add(t);
				}
				else if(es31==null){
					Node<Triangle> n=es23.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					n=es12.adj_tri.head;
					while(n!=null){
						n.value.neighbors.add(t);
						t.neighbors.add(n.value);
						n=n.next;
					}
					es12.adj_tri.add(t);es23.adj_tri.add(t);e31.adj_tri.add(t);
					ps1.adj_triangles.add(t);ps2.adj_triangles.add(t);ps3.adj_triangles.add(t);
					edges.add(e31);
					triangles.add(t);
				}
				
			}
			
			
			
			return true;
		}
		return false;
		}

		public int TYPE_MESH(){
			Node<Edge> e=edges.head;
		//	System.out.println(edges.currentSize);
			boolean isproper=true;
			while(e!=null){
				//e.value.print;
				if(e.value.adj_tri.currentSize==1){
					isproper=false;
				}
				
				else if(e.value.adj_tri.currentSize==3){
					return 3;
				}
				e=e.next;
			}
			
			if(isproper){
				return 1;
			}
			
			return 2;
			}
		
		public EdgeInterface [] BOUNDARY_EDGES(){
			Node<Edge> e=edges.head;
			int count=0;
			List<Edge> l=new List();
			
			while(e!=null){
				if(e.value.adj_tri.currentSize==1){
					count++;
					l.add(e.value);
					
				}
				e=e.next;
			}
			
			
			Edge[] be=new Edge[count];
			Node<Edge> n=l.head;
			for(int i=0;i<count;i++){
				if(n==null){break;}
				be[i]=n.value;
				n.value.print();
				n=n.next;
			}
			sorte(be,count);
			EdgeInterface[] ber=new Edge[count];
			for(int i=0;i<count;i++){
				ber[i]=be[i];
			}
			
			return ber;
		}
		
		
		public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
			Triangle t1=new Triangle(triangle_coord);
			Triangle t=triangles.get(t1);
			List<Triangle> l=t.neighbors;
			int size=l.currentSize;
			if(size==0){
				return null;
			}
//			System.out.println(size);
			Node<Triangle> n=l.head;
			TriangleInterface[] not=new Triangle[size];
			for(int i=0;i<size;i++){
//				n.value.print();
				not[i]=n.value;
				n=n.next;
			}
			
			return not;
		}
		
		public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
			Triangle t1=new Triangle(triangle_coord);
			Triangle t=triangles.get(t1);
			if(t==null){
				return null;
			}
			
			return  t.edges;
		}
		
		public int COUNT_CONNECTED_COMPONENTS(){
			mark++;
			int no_of_diff_comp=0;
			Node<Triangle> t=triangles.head;
			while(t!=null){
				if(t.value.mark!=mark){
					no_of_diff_comp++;
					t.value.mark=mark;
					t.value.mark_neighbor(mark);
				}
				
				t=t.next;
			}
			return no_of_diff_comp;
		}
		
		public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
			Triangle t1=new Triangle(triangle_coord);
			Triangle t=triangles.get(t1);
			if(t==null){
				return null;
			}
			return t.points;
		}
		
		public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
			Triangle t1=new Triangle(triangle_coord);
			Triangle t=triangles.get(t1);
			if(t==null){
				return null;
			}
			List<Triangle> neighbor=t.neighbors;
			Node<Triangle> A=t.A.adj_triangles.head;
			
			List<Triangle> list=new List();
			
			while(A!=null){
			//	A.value.print();System.out.println(".A.");
				if(list.get(A.value)==null&&A.value.compareTo(t)!=0){
					list.add(A.value);
				}
				A=A.next;
			}
			
			A=t.B.adj_triangles.head;
			while(A!=null){
			//	A.value.print();System.out.println(".B.");
				if(list.get(A.value)==null&&A.value.compareTo(t)!=0){
					list.add(A.value);
				}
				A=A.next;
			}
			A=t.C.adj_triangles.head;
			while(A!=null){
			//	A.value.print();System.out.println(".C.");
				if(list.get(A.value)==null&&A.value.compareTo(t)!=0){
					list.add(A.value);
				}
				A=A.next;
			}
			
			Triangle[] ti=new Triangle[list.currentSize];
			
			Node<Triangle> n=list.head;
			int a=list.currentSize;
			for(int i=0;i<a;i++){
				ti[i]=n.value;
				n=n.next;
			}
			sortt(ti,a);
			TriangleInterface[] tir=new Triangle[a];
			for(int i=0;i<a;i++){
				tir[i]=ti[i];
			}
			
			
			return tir;
		}
		
		public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
			Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			Point p=points.get(p1);
			if(p==null){return null;}
			List<Triangle> list=p.adj_triangles;
			Node<Triangle> t=list.head;
			int size=list.currentSize;
			TriangleInterface[] ti=new Triangle[size];
			for(int i=0;i<size;i++){
				ti[i]=t.value;
//				t.value.print();
				t=t.next;
			}
			
			return ti;
		}
		
		 public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
			 Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			 Point p=points.get(p1);
			 if(p==null){return null;}
			 List<Point> list=p.adj_points();int size=list.currentSize;
			 PointInterface[] pi=new Point[size];
			 Node<Point> n=list.head;
			 for(int i=0;i<size;i++){
				 pi[i]=n.value;
			//	 n.value.print();System.out.println("n");
				 n=n.next;
			 }
			 
			 return pi;
		 }
		 
		 public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
			 Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			 Point p=points.get(p1);
			 if(p==null){return null;}
			 List<Edge> list=p.adjacent;int size=list.currentSize;
			 EdgeInterface[] ei=new Edge[size];
			 Node<Edge> n=list.head;
			 for(int i=0;i<size;i++){
				 ei[i]=n.value;
				 n=n.next;
			 }
			 
			 return ei;
		 }

		 public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
			 return INCIDENT_TRIANGLES(point_coordinates);
		 }
		 
		 public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){
			 Edge e1=new Edge(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2],edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
			 Edge e=edges.get(e1);
			 if(e==null){return null;}
			 List<Triangle> list=e.adj_tri;
				Node<Triangle> t=list.head;
				int size=list.currentSize;
				TriangleInterface[] ti=new Triangle[size];
				for(int i=0;i<size;i++){
					ti[i]=t.value;
					t=t.next;
				}
			return ti;
			 
		 }
		 
		 public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
			 mark++;
			// System.out.println(mark);
			 Triangle t1=new Triangle(triangle_coord_1);
			 Triangle t2=new Triangle(triangle_coord_2);
			 Triangle tr1=triangles.get(t1);Triangle tr2=triangles.get(t2);
			 if(tr1!=null&&tr2!=null){
				tr1.mark_neighbor(mark);tr1.mark=mark;
				Triangle t=triangles.get(t2);
			//	System.out.println(t.mark);
				if(t.mark==mark){
					return true;
				}
			 }
				
			 return false;
		 }
		 
		 
		 public PointInterface [] CENTROID (){
			 mark++;
				// System.out.println(mark);
			if(triangles.head==null){
				return null;
			}
			
			Node<Triangle> t=triangles.head;
		//	Triangle t1=t.value;
			//Point centroid=t1.centroid(); t1.A.mark=mark;t1.B.mark=mark;t1.C.mark=mark;
			//centroid.print();System.out.println("....");
		//	centroid=t.value.update_centroid(t.value,centroid, mark,(float)3,(float)4);
			List<Triangle> list1=new List();
			//list.add(t1);
			
			while(t!=null){
				if(t.value.mark!=mark){
					//no_of_diff_comp++;
					//System.out.println("11");
					
					list1.add(t.value);
					
					t.value.mark_neighbor(mark);t.value.mark=mark;
					//mark++;
				}
				t=t.next;
			}
			
			
			List<Point> list=new List();
			int i=0;
			Point[] pi=new Point[list1.currentSize];
			t=list1.head;
			while(t!=null){
				Point centroid=CENTROID_OF_COMPONENT1(t.value.A.coordinates);
				pi[i++]=centroid;
			//	centroid.print();System.out.println();
				list.add(centroid);
				t=t.next;
			}
			sortp(pi,i);
			//int a=i;
			PointInterface[] pir=new Point[i];
			for(int j=0;j<i;j++){
				pir[j]=pi[j];
			}
			
			 return pir;
			
			
			
		 }
		 
		 
		 public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
			 mark++;
			 Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			 Point p=points.get(p1);
			 if(p==null){
				//System.out.println("...");
				 return null;
			 }
			 Triangle t1=p.adj_triangles.head();
			 t1=triangles.get(t1);
			 t1.mark_neighbor(mark);t1.mark=mark;
			 List<Point> list=new List();
			 Node<Triangle> n=triangles.head;
			 while(n!=null){
				 if(n.value.mark==mark){
					 if(list.get(n.value.A)==null){
						 list.add(n.value.A);
					 }
					 if(list.get(n.value.B)==null){
						 list.add(n.value.B);
					 }
					 if(list.get(n.value.C)==null){
						 list.add(n.value.C);
					 }
					 
				 }
				 n=n.next;
			 }
			 
			 Node<Point> np=list.head;
			
			 float X=0;float Y=0;float Z=0;
			 while(np!=null){
				 X=X+np.value.X;//System.out.println(X);
				 Y=Y+np.value.Y;
				 Z=Z+np.value.Z;
				 np=np.next;
			 }
			 float size=list.currentSize;
			 X=X/size;Y=Y/size;Z=Z/size;
			 
			 Point centroid=new Point(X,Y,Z);
		//	 centroid.print();
			 return centroid;
		 }
		 
		 public int MAXIMUM_DIAMETER(){
			 mark++;
			 if(triangles.head==null){
					return 0;
				}
				
			 Node<Triangle> t3=triangles.head;
			 Triangle req=triangles.head();
			 int max=0;
			 int i=0;
			 List<Triangle> list1=new List();
				//list.add(t1);
				
			while(t3!=null){
					if(t3.value.mark!=mark){
						//no_of_diff_comp++;
					//	System.out.print("..");
						
						//list1.add(t.value);
						
						int no_of_tri=t3.value.count_mark_neighbor(mark,0);
						if(no_of_tri>=max){
							req=t3.value;
							max=no_of_tri;
						//	System.out.println(max);
						}
						//mark++;
					}
					t3=t3.next;
				}
			 
			 
			 
			 Triangle t=req;
		//	 Triangle t=triangles.head();
			 t.reached=true;t.weight=0;
			 if(t==null){return 0;}
			 List<Triangle> queue=new List();
			 queue.add(t);
			 Node<Triangle> n=queue.head;
			 Node<Triangle> pre_node=null;
			 while(n!=null){
				 
				 List<Triangle> adj=n.value.neighbors;
				 Node<Triangle> n1=adj.head;
				 while(n1!=null){
					 if(!n1.value.reached){
						 n1.value.reached=true;
						 n1.value.weight=n.value.weight+1;
						 queue.add(n1.value);
					 }
					 n1=n1.next;
				 }
				 pre_node=n;
				 n=n.next;
				 
			 }
			 t=pre_node.value;
			// System.out.println("t.weight");
			 t.reach2=true;t.weight2=0;
			 List<Triangle> queue2=new List();
			 queue2.add(t);
			 n=queue2.head;
			 pre_node=null;
			 while(n!=null){
				//System.out.println("2"); 
				 List<Triangle> adj=n.value.neighbors;
				 Node<Triangle> n1=adj.head;
				 while(n1!=null){
				//	
					 if(!n1.value.reach2){
						// System.out.println("1..");
						 n1.value.reach2=true;
						 n1.value.weight2=n.value.weight2+1;
						 queue2.add(n1.value);
					 }
					 n1=n1.next;
				 }
				 pre_node=n;
				 n=n.next;
			 }
			 
			 int to_return =pre_node.value.weight2;
			 
			 Node<Triangle> pointer=triangles.head;
			 while(pointer!=null){
				 pointer.value.weight=0;
				 pointer.value.weight2=0;
				 pointer.value.reach2=false;
				 pointer.value.reached=false;
				 pointer=pointer.next;
			 }
			 System.out.println(to_return);
			 return to_return;
			 
			 
			 
		 }
		 
		void sortp(Point[] pi,int length){
			for(int i=0;i<length;i++){
				for(int j=i+1;j<length;j++){
					if(pi[i].compareTo(pi[j])>0){
						Point temp=pi[i];
						pi[i]=pi[j];
						pi[j]=temp;
					}
				}
			}
		}
		
		void sortt(Triangle[] pi,int length){
			for(int i=0;i<length;i++){
				for(int j=i+1;j<length;j++){
					if(pi[i].compareTo(pi[j])>0){
						Triangle temp=pi[i];
						pi[i]=pi[j];
						pi[j]=temp;
					}
				}
			}
		}
		void sorte(Edge[] pi,int length){
			for(int i=0;i<length;i++){
				for(int j=i+1;j<length;j++){
					if(pi[i].compareTo(pi[j])>0){
						Edge temp=pi[i];
						pi[i]=pi[j];
						pi[j]=temp;
					}
				}
			}
		}
		 
		 
		public Point CENTROID_OF_COMPONENT1 (float [] point_coordinates){
			 mark++;
			 Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			 Point p=points.get(p1);
			 if(p==null){
				// System.out.println("...");
				 return null;
			 }
			 Triangle t1=p.adj_triangles.head();
			 t1=triangles.get(t1);
			 t1.mark_neighbor(mark);t1.mark=mark;
			 List<Point> list=new List();
			 Node<Triangle> n=triangles.head;
			 while(n!=null){
				 if(n.value.mark==mark){
					 if(list.get(n.value.A)==null){
						 list.add(n.value.A);
					 }
					 if(list.get(n.value.B)==null){
						 list.add(n.value.B);
					 }
					 if(list.get(n.value.C)==null){
						 list.add(n.value.C);
					 }
					 
				 }
				 n=n.next;
			 }
			// System.out.println(list.currentSize);
			 Node<Point> np=list.head;
			
			 float X=0;float Y=0;float Z=0;
			 while(np!=null){
				 X=X+np.value.X;
				 Y=Y+np.value.Y;
				 Z=Z+np.value.Z;
				 np=np.next;
			 }
			 
			 float size=list.currentSize;
			 X=X/size;Y=Y/size;Z=Z/size;
			// System.out.println(X);
			 Point centroid=new Point(X,Y,Z);
			// centroid.print();System.out.println("..");
			 
			 
	/*		 Point centroid=t1.centroid();   t1.mark=mark;t1.A.mark=mark;t1.B.mark=mark;t1.C.mark=mark;
			 centroid =  t1.update_centroid(t1,centroid,mark,(float)3,(float)4);
			                        centroid.print();System.out.println();*/
			 return centroid;
		 }
		 
		 
}

