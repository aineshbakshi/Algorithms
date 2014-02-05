import java.util.Random;

public class countingSort {

	public static int [] countingsort(int [] A)
	{
		int C [] = new int [1000]; // Range of numbers
		int B [] = new int[A.length];
		for(int i=0; i < C.length; i++)
			C[i] = 0;
		for(int j=0; j<A.length; j++)
			C[A[j]] = C[A[j]] + 1;
		for(int i=1; i<C.length;i++)
			C[i] = C[i] + C[i-1];
		for(int i=A.length-1; i>=0; i--)
		{
			B[C[A[i]]-1] = A[i];
			C[A[i]] = C[A[i]] -1;
		}
		return B;
	}
	public static void main (String args [])
	{
		int A[] = new int [100];
		for(int i=0; i< A.length; i++)
		{
			double random = new Random().nextDouble();
			A[i] = (int)(random*1000);
		}
		int B[] = countingsort(A);
		for(int k=0; k<B.length; k++)
			System.out.print(B[k] + " ");
	}
}

