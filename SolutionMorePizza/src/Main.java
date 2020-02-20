import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static final String[] filePaths =
            new String[]{
                    "./dataIn/a_example.in",
                    "./dataIn/b_small.in",
                    "./dataIn/c_medium.in",
                    "./dataIn/d_quite_big.in",
                    "./dataIn/e_also_big.in",
                        };
    public static void main(String[] args) {


            for(String file : filePaths)
            {
                MorePizzaSolutionSelector(
                        dataPreProcessing(readTheFile(file))
                        ,  file.split("\\.")[1].split("/")[2]
                );
            }

    }

    /**
     *
     * applied that the data set is of the proper size
     * @param target targeted pizza pieces
     * @param size number of pizza types
     * @param pieces pizza pieces
     * @return result
     */
    private static String dynamicProgramming(int target,int size , int [] pieces)
    {
       StringBuilder sbResult = new StringBuilder();

        boolean[][] arr = new boolean[size+1][target+1];

        for ( int j = 1; j <= target; j++)
            arr[0][j] = false;

        for (int i = 0; i <= size; i++)
            arr[i][0] = true;

        // I created the bool matrix
        for ( int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= target; j++)
            {
                arr[i][j] = (pieces[i-1] <= j) ?
                        (arr[i-1][j] || arr[i-1][j - pieces[i-1]]) :
                         arr[i - 1][j];

            }
        }

        //to find the closest result

        int tempTarget = target;
        int diff = 0;
        while (!arr[size][tempTarget])
        {
            tempTarget--;
            diff++;
        }

        int counter = 0;
        //applied back tracking process
        while (size > 0 && tempTarget > 0)
        {

            if (arr[size][tempTarget] != arr[size-1][tempTarget])
            {
                counter++;
                //added current index
                sbResult.insert(0," ").insert(0,size-1);
                tempTarget -= pieces[size-1];
            }
            size --;
        }

        System.out.println("Dynamic programming implemented");
        System.out.println("Result : " +(target-tempTarget - diff) + "\n");
        sbResult.insert(0,"\n").insert(0,counter);
       return sbResult.toString();
    }

    /**
     * applicable because the data set is large and there is no memory space available
     * @param target targeted pizza pieces
     * @param size number of pizza types
     * @param pieces pizza pieces
     * @return result
     */
    private   static String greedyApproaching(int target, int size , int [] pieces)
    {
        StringBuilder sbResult = new StringBuilder();
        int i = size-1 , temp = target;
        int counter = 0;
        // adds from large to small until the target reaches or the data ends
        while(temp  >= 0)
        {
            if(temp - pieces[i] >= 0)
            {
                counter++;
                temp -= pieces[i];
                sbResult.insert(0," ").insert(0,i);
            }
            i--;
            if(i < 0) break;
        }

        System.out.println("Greedy Approaching implemented");
        System.out.println("Result : " +(target - temp) + "\n" );
        sbResult.insert(0,"\n").insert(0,counter);
        return sbResult.toString();
    }

    /**
     * choose the appropriate selection path according to the size of the data
     * @param nums  processed data
     */
    public static void MorePizzaSolutionSelector(int[][] nums,String fileName)
    {
      int target = nums[0][0];
      int size = nums[0][1];

      String result;
      //a limit to insufficient memory status
      if(target > 4500 || size > 50)
         result= greedyApproaching(target,size,nums[1]);
     else
         result =  dynamicProgramming(target,size,nums[1]);

       writeToFile(result,fileName);

    }

    /**
     * makes the data ready for solution
     * @param data raw data
     * @return processed data
     */
    public static int[][] dataPreProcessing(ArrayList<String> data)
    {
        int[][] nums = new int[2][];
        //the amount of pizza pieces ordered and number of pizza types
        nums[0] = Arrays.stream(data.get(0).split(" ")).mapToInt(Integer::parseInt).toArray();
        //pizza slices
        nums[1] = Arrays.stream(data.get(1).split(" ")).mapToInt(Integer::parseInt).toArray();

        return nums;
    }
    /**
     * reads data files
     * @param path file path
     * @return file content
     */
    private static ArrayList<String> readTheFile(String path)
    {
        ArrayList<String> data = new ArrayList<>();
        try {
            File obj = new File(path);
            Scanner reader = new Scanner(obj);

             //first line
             data.add(reader.nextLine());
             //second line
             data.add(reader.nextLine());

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }

        return data;
    }

    /**
     * write the result to .out files
     * @param result
     * @param fileName out file path
     */
    public static void  writeToFile(String result,String fileName)
    {
        FileWriter fileWriter ;
        try {
            fileWriter = new FileWriter("./dataOut/"+fileName +".out");
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }



}
