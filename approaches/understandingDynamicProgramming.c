#include<stdio.h>
#define  t 100 //target
#define  s 10 //size
#define max(x,y) x>y ? x : y
main()
{
	int a[s+1] = {0,4,14,15,18,29,32,36,82,95,95};
	int mat[s+1][t+1] = {0};
	
	int i ,j;
	
	for(i = 1; i <= s ; i++) // size
	{  
	   printf("[%3d  ]",a[i]);
		for( j = 1; j <= t ;j++) //target
		{
			mat[i][j] = (a[i] <= j) ? max(mat[i-1][j],a[i] + mat[i-1][j - a[i]]) : mat[i-1][j];
			
			printf("%3d",mat[i][j]);
			
		}
		printf("\n");
	}
	printf("\n\n\n");
	


     printf("Types,Slices\n");
     i = s;
     j = t;
    while (i > 0 && j > 0)
    {
	
        if (mat[i][j] != mat[i-1][j])
        {
        printf("%3d  %3d\n",(i-1),a[i]);
            j = j - a[i];
            i--;	
		}
          
        else
            i--;
        }

	getch();
	
}
