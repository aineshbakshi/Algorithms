import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/*
 * Neighbor class that contains vertex number and edge weights
 * 
 */
class Neighbor implements Comparable <Neighbor> 
{
    public int vertexNum;
    public Neighbor next;
    public int edgeWeight;
    
    public Neighbor(int vnum, Neighbor nbr, int weight) 
    {
            this.vertexNum = vnum;
            next = nbr;
            edgeWeight = weight;
    }
    /*
     * compareTo method for edge weights 
     * 
     */
    public int compareTo(Neighbor v)
    {
    	if(v.edgeWeight == this.edgeWeight)
    		return 0;
    	else if(v.edgeWeight <= this.edgeWeight)
    		return 1;
    	else 
    		return -1;
    }
}

/*
 * Vertex class that has a name, and a linked list for all it's neighbors
 * 
 */
class Vertex {
    String name;
    Neighbor adjList;
    int prev;
    Vertex(String name, Neighbor neighbors) {
            this.name = name;
            this.adjList = neighbors;
            prev =-1;
    }
    
}

/*
 * Graph object 
 * Contains depth first search, breadth first search, Dijkstra's shortest path for weighted graphs
 * Method details given below
 */
public class Graph  {

	Vertex [] adjLists;
	
	boolean undirected=true;
	
	public Graph(String file) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(file));
		try{
			String graphType = sc.next();
			if (graphType.equals("directed")) {
				undirected=false;
			}
			int size = sc.nextInt();
			adjLists = new Vertex [size];
	
			// read vertices
			for (int v=0; v < adjLists.length; v++) {
				adjLists[v] = new Vertex(sc.next(), null);
			}
	
			// read edges
			while (sc.hasNext()) {
				
				// read vertex names and translate to vertex numbers
				int v1 = indexForName(sc.next());
				int v2 = indexForName(sc.next());
				int rand = sc.nextInt();
				// add v2 to front of v1's adjacency list and
				// add v1 to front of v2's adjacency list
				//int rand = 1+ (int)(Math.random()*50);
				adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList, rand);
				if (undirected) {
					adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList, rand);
				}
				System.out.println("1: " + adjLists[v1].name + " 2: " + adjLists[v2].name + " "+ rand);
				
			}
		}
		finally 
		{
			sc.close();
		}
	}
	/*
	 * Depth First Search on the Graph. 
	 * 
	 */
	public void dfs()
	{
		boolean visited [] = new boolean[adjLists.length];
		for (int i =0; i < visited.length; i++)
		{
			visited[i]= false;
		}
		for (int j=0; j < adjLists.length; j++)
		{
			if(!visited[j])
			{
				dfs(j, visited);
			}
		}
	}
	private void dfs(int v, boolean [] visited)
	{
		visited[v] = true;
		for (Neighbor e=adjLists[v].adjList; e != null; e=e.next) {
			if (!visited[e.vertexNum]) {
				System.out.println("\t" + adjLists[v].name + "--" + e.edgeWeight + "--" + adjLists[e.vertexNum].name);
				dfs(e.vertexNum, visited);
			}
		}
	}
	
	/*
	 * Breadth first search on the Graph
	 */
	public void bfs()
	{
		boolean visited [] = new boolean[adjLists.length];
		for(int i=0; i< visited.length; i++)
		{
			visited[i] = false;
		}
		for(int i=0; i < visited.length; i++)
		{
			if(!visited[i])
			{
				bfs(i, visited);
			}
		}
	}
	
	private void bfs(int i, boolean visited [])
	{
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(i);
		while(!q.isEmpty())
		{
			int w = q.dequeue();
			for( Neighbor e = adjLists[w].adjList; e!= null; e = e.next )
			{
				if(!visited[e.vertexNum])
				{
					q.enqueue(e.vertexNum);
					visited[e.vertexNum] = true;
					System.out.println("Visiting vertex number: " + e.vertexNum);
				}
			}
		}
	}
	/*
	 * Takes initial and final destination and runs Dijksta's shortest path algorithm. Prints Shortest path
	 * 
	 */
	public void shortestPath(int v, int f)
	{
		
		int [] dist = new int[adjLists.length];
		dist[v] = 0;
		boolean [] done = new boolean[adjLists.length];
		Heap<Neighbor> heap = new Heap<Neighbor>();
		for(int i=0; i < adjLists.length; i++)
		{
			done[i] = false;
			dist[i] = Integer.MAX_VALUE;
		}
		for(Neighbor e = adjLists[v].adjList; e!=null; e=e.next)
		{
			dist[e.vertexNum] = e.edgeWeight;
			adjLists[e.vertexNum].prev = v;
			//add to the fringe
			heap.insert(e);
		}
		while(!heap.isEmpty())
		{
			Neighbor k = heap.delete();
			done[k.vertexNum] = true;
			for(Neighbor w = adjLists[k.vertexNum].adjList; w!=null; w = w.next)
			{
				if(done[w.vertexNum])
					continue;
				if(dist[w.vertexNum]== Integer.MAX_VALUE)
				{
					dist[w.vertexNum] = dist[k.vertexNum] + w.edgeWeight;
					heap.insert(w);
					adjLists[w.vertexNum].prev = k.vertexNum;
				}
				else if(dist[k.vertexNum] + w.edgeWeight < dist[w.vertexNum])
				{
					dist[w.vertexNum] = dist[k.vertexNum] + w.edgeWeight;
					adjLists[w.vertexNum].prev = k.vertexNum;
				}
			}
		}
		Stack <Vertex> stk = new Stack<Vertex>();
		int p = f;
		while(p != v)
		{
			if(p == -1)
			{
				System.out.println("Path Does not exist");
				return;
			}
			stk.push(adjLists[p]);
			p = adjLists[p].prev;
		}
		stk.push(adjLists[v]);
		while(!stk.isEmpty())
		{
			if(stk.size()== 1)
				System.out.print( stk.pop().name);
			else
				System.out.print( stk.pop().name + " -- ");
		}
		System.out.println();
	}
	/*
	 * Method to convert from index to corresponding name
	 */
	int indexForName(String name) {
		for (int v=0; v < adjLists.length; v++) {
			if (adjLists[v].name.equalsIgnoreCase(name)) {
				return v;
			}
		}
		return -1;
	}	
}

