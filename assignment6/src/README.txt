class Point implemensts PointInterface,comparable
have float X;
	float Y;
	float Z;
	float[] coordinates=new float[3];
	int mark=-1;
	List<Edge> adjacent;
	List<Triangle> adj_triangles;
	methods are compareTO(),List<Points> adj_points


class Edge implements EdgeInterface ,compareble
	Point A;
	Point B;
	List<Triangle> adj_tri;
	float length=0;
	methods  length(),squareRoot();



class Triangle implemnets TriangleInterface, Comparable
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

	methods  compareTo(),mark_neighbor(),centroid().noncomman_point(),count_mark_neighbor()

class shape implements shapeInterface, comparable
	List<Triangle> triangles;
	List<Point> points;
	List<Edge> edges;
	int no_of_t=0;
	int mark=0;

	extra methods   void sortp(Point[] pi,int length
		void sortt(Triangle[] pi,int length)
	public Point CENTROID_OF_COMPONENT1 (float [] point_coordinates
	