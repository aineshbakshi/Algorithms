import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class graphDriver {
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {

		Graph graph = new Graph("data");
		System.out.println("Enter first name ");
		String name = keyboard.readLine();
		int n1 = graph.indexForName(name);
		System.out.println("Enter second name ");
		name = keyboard.readLine();
		int n2 = graph.indexForName(name);
		graph.shortestPath(n1, n2);
		
	}

}
