public class selectionSort
{
    public static void main(String args[])
    {
        int a [] = {6,5,3,1,8,7,2,4};

        for(int i =1 ; i < a.length; i++)
        {
            int minloc=i-1;
            for(int j=i; j<a.length;j++)
            {
                if(a[j]<a[minloc])
                    minloc = j;
            }
            int temp = a[minloc];
            a[minloc] = a[i-1];
            a[i-1] = temp;
        }

        for (int i=0; i< a.length; i++)
            System.out.println(a[i]);
    }
}

