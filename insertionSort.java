public class insertionSort
{
    public static void main(String args[])
    {
        int a [] = {6,5,3,1,8,7,2,4};

        for(int i=1; i< a.length; i++)
        {
            int loc =i-1;
            int temp = a[i];
            while(loc >=0 && temp<=a[loc])
            {
                int x = a[loc];
                a[loc] = a[loc+1];
                a[loc+1] = x;
                loc--;
            }
        }
        for(int i=0; i <a.length; i++)
            System.out.println(a[i]);
    }
}
                
