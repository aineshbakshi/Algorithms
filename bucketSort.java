import java.util.Random;

public class bucketSort {
	//Defining the linked list and the add method
	public static class Node 
	{
		double data;
		Node next;
		public Node(double data, Node next)
		{
			this.data = data;
			this.next = next;
		}
		
	}
	public static Node addToFront(Node front, double a)
	{
		front = new Node (a, front);
		return front;
	}
	public static Node insertionSort(Node front)
	{
		// Insertion Sort from a linked list
		if(front == null)
			return null;
		//Init sorted list 
		Node sortedList = front;
		front = front.next;
		sortedList.next = null;
		//loop over the unsorted list
		while(front != null)
		{
			//Adv the nodes
			Node current = front;
			front = front.next;
			if(current.data < sortedList.data)
			{
				current.next = sortedList;
				sortedList = current;
			}
			else 
			{
				//Search the list for the correct position
				Node search = sortedList;
				while(search != null && current.data>search.data)
				{
					search = search.next;
				}
				if(search== null || search.next == null)
					return sortedList;
				current.next = search.next;
				search.next = current;
			}
			
		}
		return sortedList;
	}
	public static void bucketsort(double [] A)
	{
		// Array of Lists
		Node[] buckets = new Node [A.length];
		// Add Data to appropriate index of the array of lists
		for(int i=0; i < A.length; i++)
		{	
			buckets[(int)(A.length*A[i])] = addToFront(buckets[(int)(A.length*A[i])], A[i]);
		}
		int count = 0;
		// Sort each list and combine
		for(int i =0; i < A.length; i++)
		{
			
			buckets[i] = insertionSort(buckets[i]);
			while(buckets[i]!=null)
			{
				A[count] = buckets[i].data;
				buckets[i] = buckets[i].next;
				count++;
			}
		}
	}
	public static void main(String args[])
	{
		// Create random sample array 
		double []A = new double[10];
		for(int i=0; i< A.length; i++)
		{
			double random = new Random().nextDouble();
			A[i] = random;
		}
		// Call sorting method and print sorted array
		bucketsort(A);
		for(int i=0; i< A.length; i++)
			System.out.print(A[i] + " ");
	}
}

