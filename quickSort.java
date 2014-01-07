import java.util.Random;

public class quickSort {

	public static int choosePivot(int [] A, int l, int r)
	{
		//Randomly choose pivot
		double random = new Random().nextDouble();
		return l + (int)(random *((r - l) + 1)) ;
	}
	
	public static int partition (int []A, int l, int r )
	{
		int p = choosePivot(A,l,r);
		//Swap pivot and first element
		int temp1 = A[p];
		A[p] = A[l];
		A[l] = temp1;
		int i = l+1;
		for(int j=l+1; j<=r; j++)
		{
			if(A[j] < A[l])
			{
				int temp = A[j];
				A[j] = A[i];
				A[i] = temp;
				i++;
			}
		}
		//Swap pivot with its correct position
		int temp = A[l];
		A[l] = A[i-1];
		A[i-1] = temp;
		//Return position of the pivot element
		return i-1;
	}
	public static void quicksort(int []A, int l, int r)
	{
		if( (r-l +1) ==0 || (r-l +1) == 1)
			return;
		int p = partition(A,l,r);
		// Sort the part before and after the pivot position given by p
		
		//quicksort left part
		quicksort(A,l,p-1);
		//quicksort right part
		quicksort(A,p+1,r);
	}
	public static void main(String args [])
	{
		int A[] = new int [100];
		for(int i=0; i< A.length; i++)
		{
			double random = new Random().nextDouble();
			A[i] = (int)(random*1000);
		}
		quicksort(A, 0,A.length-1);
		for(int k=0; k<A.length; k++)
			System.out.print(A[k] + " ");
	}
}

