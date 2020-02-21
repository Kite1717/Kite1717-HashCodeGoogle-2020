import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {


    static ArrayList<String> logs  = new ArrayList<>();


   public static void writer(String path)
   {
       FileWriter fileWriter ;
       try {
           fileWriter = new FileWriter("./dataOut/"+path +".txt");
           for (String s : logs)
               fileWriter.write(s + "\n");

           fileWriter.close();
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }


}
