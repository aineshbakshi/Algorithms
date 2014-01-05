import java.util.Random;
import java.util.Arrays;

public class closestPair {
	private static int numPoints = 100;
	
	public static class Point 
	{
		public double x;
		public double y;
		/*
		public Point(double a, double b)
		{
			x = a;
			y = b;
		}*/
		public String toString()
		{
			return ("(" + x + ", " + y + ")");
		}
	}
	
	public static void mergesort(Point []A, boolean b)
	{
		int q = A.length/2;
		if(A.length > 1)
		{
			Point left[] = Arrays.copyOfRange(A, 0, q);
			Point right[] = Arrays.copyOfRange(A,q,A.length);
			mergesort(left, b);
			mergesort(right, b);
			merge(A,left,right, b);
		}
	}
	
	public static void merge(Point []A, Point []left, Point right[], boolean b)
	{
		if(b)
		{
			int x=0;
			int y =0;
			int cnt =0;
			while(x<left.length && y <right.length)
			{
				if(left[x].x < right[y].x)
				{
					A[cnt] = left[x];
					cnt++;
					x++;
				}
				else
				{
					A[cnt] = right[y];
					y++;
					cnt++;
				}
			}
			if(x<y)
			{
				while(x < left.length)
				{
					A[cnt] = left[x];
					cnt++;
					x++;
				}
				
			}
			else
			{
				while(y < right.length)
				{
					A[cnt] = right[y];
					cnt++;
					y++;
				}
			}
		}
		if(!b)
		{
			int x=0;
			int y =0;
			int cnt =0;
			while(x<left.length && y <right.length)
			{
				if(left[x].y < right[y].y)
				{
					A[cnt] = left[x];
					cnt++;
					x++;
				}
				else
				{
					A[cnt] = right[y];
					y++;
					cnt++;
				}
			}
			if(x<y)
			{
				while(x < left.length)
				{
					A[cnt] = left[x];
					cnt++;
					x++;
				}
				
			}
			else
			{
				while(y < right.length)
				{
					A[cnt] = right[y];
					cnt++;
					y++;
				}
			}
		}
	}
	
	public static double distance(Point x, Point y)
	{
		return Math.sqrt(Math.pow(x.x - y.x,2) + Math.pow(x.y - y.y,2)) ;
	}
	
	public static Point [] closestpair(Point []P_x, Point [] P_y )
	{
		// Make Q the left half of P and R the right half of P
		if(P_x.length == 3)
		{
			double d1 = distance(P_x[0], P_x[1]);
			double d2 = distance(P_x[1], P_x[2]);
			double d3 = distance(P_x[0], P_x[2]);
			Point []P1 = {P_x[0], P_x[1]};
			Point []P2 = {P_x[1], P_x[2]};
			Point []P3 = {P_x[0], P_x[2]};
			if(d1<=d2 && d1<=d3)
				return P1;
			if(d2<=d3 && d2 <=d1)
				return P2;
			return P3;
		}
		if(P_x.length == 2)
			return P_x;
		else
		{
			int mid = P_x.length/2;
			boolean first = false;
			Point [] Q_x = Arrays.copyOfRange(P_x, 0, mid);
			Point [] Q_y = Arrays.copyOfRange(P_y, 0, mid);
			Point [] R_x = Arrays.copyOfRange(P_x, mid, P_x.length);
			Point [] R_y = Arrays.copyOfRange(P_y, mid, P_y.length);
			Point [] p1_q1 = closestpair(Q_x, Q_y);
			double d1 = distance(p1_q1[0], p1_q1[1]);
			Point [] p2_q2 = closestpair(R_x, R_y);
			double d2 = distance(p2_q2[0], p2_q2[1]);
			double delta;
			if(d1<d2)
			{
				delta = d1;
				first = true;
			}
			else
				delta = d2;
			Point [] p3_q3 = closestsplitpair(P_x, P_y, delta);
			if(p3_q3[0]!= null && p3_q3[1] != null)
			{
				double d3 = distance(p3_q3[0],p3_q3[1]);
				if(first)
				{
					if(d3 < d1)
						return p3_q3;
					else 
						return p1_q1;
				}
				else
				{
					if(d3 < d2)
						return p3_q3;
					else
						return p2_q2;
				}	
			}
			else if(d1 < d2)
				return p1_q1;
			else 
				return p2_q2;
		}
			
	}
	
	public static Point [] closestsplitpair(Point [] P_x, Point [] P_y, double delta)
	{
		int mid = P_x.length/2;
		double x_bar = P_x[mid].x;
		Point []temp = new Point [numPoints];
		int count=0;
		for(int i=0; i < P_y.length ; i++)
		{
			if(P_y[i].x < x_bar + delta && P_y[i].x > x_bar - delta)
			{
				temp[count] = P_y[i];
				count++;
			}
		}
		Point [] S_y = new Point [count];
		
		for(int i=0; i< count; i++)
		{
			S_y[i] = temp[i];
		}
		
		double best = delta;
		Point [] bestPair = new Point[2];
		for(int i =0; i< S_y.length -7;i++)
		{
			for(int j=i; j <= i+7; j++)
			{
				if(S_y[j] != null && S_y[i] != null)
				{
					double temp_dist = distance(S_y[i], S_y[j]);
					if(temp_dist < best){
						bestPair[0] = S_y[i];
						bestPair[1] = S_y[j];
						best = temp_dist;
					}
				}	
			}
		}
		return bestPair;
	}
	
	public static void main(String args [])
	{
		Point A[] = new Point[numPoints];
		//Creating array of points
		for (int i =0; i < A.length; i++)
		{
			A[i] = new Point();
			double randomx = new Random().nextDouble(); 
			double randomy = new Random().nextDouble();
			A[i].x = (int) (randomx*1000);
			A[i].y = (int) (randomy*1000);
			//System.out.println(A[i]);
		}
		// Making two sorted copies according to x and y P_x and P_y
		Point P_x[] = new Point[numPoints];
		Point P_y[] = new Point[numPoints]; 
		for(int i=0; i < A.length; i++)
		{
			P_x[i] = A[i];
			P_y[i] = A[i];
		}
		mergesort(P_x,true);
		mergesort(P_y,false);
		Point []closest = closestpair(P_x, P_y);
		System.out.println("Closest pair : " + closest[0] + "\t" + closest[1]);	
	}
}

