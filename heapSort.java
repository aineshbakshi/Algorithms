import java.util.Random;


public class heapSort {
	private static int heapsize;
	public static void heapsort(int [] A)
	{
		heapsize = A.length;
		buildMaxHeap(A);
		for (int i=A.length-1; i>=0; i--)
		{
			int temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			heapsize = heapsize-1;
			maxHeapify(A,0);
		}
	}
	public static void buildMaxHeap(int [] A)
	{
		for(int i = A.length/2; i>=0; i--)
		{
			maxHeapify(A,i);
		}
	}
	public static void maxHeapify(int []A, int i)
	{
		int l = 2*i;
		int r = 2*i + 1;
		int largest=-1;
		if(l>=heapsize || r >=heapsize)
			return;
		if(l< heapsize && A[l] > A[i])
			largest = l;
		else 
			largest = i;
		if(r< heapsize && A[r] > A[largest])
			largest=r;
		if(largest != i)
		{
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A, largest);
		}
	}
	public static void main (String args [])
	{
		int A[] = new int [100];
		for(int i=0; i< A.length; i++)
		{
			double random = new Random().nextDouble();
			A[i] = (int)(random*1000);
		}
		heapsort(A);
		for(int k=0; k<A.length; k++)
			System.out.print(A[k] + " ");
	}
}

