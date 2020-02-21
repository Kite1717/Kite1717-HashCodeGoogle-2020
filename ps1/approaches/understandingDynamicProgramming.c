#include<stdio.h>
#define  t 100 //target
#define  s 10 //size
#define max(x,y) x>y ? x : y
main()
{
	int a[s] = {4,14,15,18,29,32,36,82,95,95};
	int bmat[s+1][t+1];
	
	int i ,j;
	
	for ( j = 1; j <= t; j++)
		bmat[0][j] = 0;

	for (i = 0; i <= s; i++)
		bmat[i][0] = 1;
		
	for ( i = 1; i <= s; i++)
	{
		for (j = 1; j <= t; j++)
		{
		  bmat[i][j] = (a[i-1] <= j) ? ( bmat[i-1][j] || bmat[i-1][j - a[i-1]] ) : bmat[i - 1][j];
		   printf("%3d",bmat[i][j]); 
		}
	}
	printf("\n\n\n");
	


     printf("Types,Slices\n");
     i = s;
     j = t;
    while (i > 0 && j > 0)
    {
	
        if (bmat[i][j] != bmat[i-1][j])
        {
          printf("%3d  %3d\n",(i-1),a[i-1]);
            j = j - a[i-1];
		}
          
        i --;
    }

    getch();
    
}

