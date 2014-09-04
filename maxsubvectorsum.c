/**
 * Maximum subvector sum Problem
 * Input: Array of integers 
 * Output: THe pair (i,j) that maximizes the subvector sum
 * Complexity : O(n)
 */


#include <stdio.h>

int main()
{
    // Initialize array 
    int A[] = {-10, -20, 10, 5, -3, -2, -1, 6, -1, 4, -3, -16, -32, 12, -1};
    //int A[] = { -2, 1, -3, 4, -1, 2, 2, -5, 4};
    int n = sizeof(A)/sizeof(A[0]);
    // tail and head counters for the subarray
    // tail_begin_temp counter for every start 
    int tail =0;
    int tail_begin_temp = 0;
    int head = 0;
    //global max 
    int max_so_far = 0;
    //max at every point 
    int max_ending_here = 0;
    int i;
    for(i=0; i < n; i++)
    {
        // max_ending_here = max(max_ending_here+ A[i], 0)
        // here is where the magic happens, it is dynamically
        // determining the current max sum 
        if( max_ending_here + A[i] < 0)
        {    
            max_ending_here = 0;
            tail_begin_temp =i+1;
        }
        //max sum doesn't matter if it is negative 
        else
        {
            max_ending_here = max_ending_here + A[i];
        }
        //fix the positions of tail and head 
        if( max_ending_here > max_so_far )
        {    
            max_so_far = max_ending_here;
            tail = tail_begin_temp;
            head = i;
        }

    }

    printf("i: %d , j: %d \n", tail, head);
    printf("max sum = %d\n", max_so_far);
    return 0;
}

