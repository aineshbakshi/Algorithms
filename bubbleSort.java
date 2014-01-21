import java.util.Random;
public class bubbleSort {
	
	public static void bubblesort(int a[])
	{
		for(int i=0; i < a.length; i++)
		{
			for(int j= a.length -1; j>=i+1; j--)
			{
				if(a[j] < a[j-1])
				{
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
				}
			}
		}
	}
	public static void main(String args [])
	{
		int A[] = new int [100];
		for(int i=0; i< A.length; i++)
		{
			double random = new Random().nextDouble();
			A[i] = (int)(random*1000);
		}
		bubblesort(A);
		for(int k=0; k<A.length; k++)
			System.out.print(A[k] + " ");
	}
}

