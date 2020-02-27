import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Helper {


    static ArrayList<String> logs  = new ArrayList<>();


   public static void writer(String path,int scannedBook)
   {

       FileWriter fileWriter ;
       try {
           fileWriter = new FileWriter(path);
           fileWriter.write(scannedBook + "\n");

            for(String s : logs)
                fileWriter.write(s);

          fileWriter.close();
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }
   public static  void writer(ArrayList<String> logs)
   {
       FileWriter fileWriter ;
       try {
           fileWriter = new FileWriter("C:\\Users\\mstf_\\OneDrive\\Masaüstü\\vvv\\ps2\\Solution\\dataOut\\temp.txt");



           for(String s : logs)
               fileWriter.write(s + "\n");

           fileWriter.close();
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
   }

   public static ArrayList<Object> readTheFile(String path,ArrayList<Lib> libs)
   {
       ArrayList<Object> objs = new ArrayList<>();

       try {
           File obj = new File(path);
           Scanner reader = new Scanner(obj);

           String[] pieces = reader.nextLine().split(" ");
           //I got count of Library and dead line time

           int tempLibCount =Integer.parseInt(pieces[1]);
           objs.add(Integer.parseInt(pieces[1])); // lib count

           objs.add(Integer.parseInt(pieces[2])); // dead line


           pieces = reader.nextLine().split(" ");


           HashMap<Integer,Integer> books = new HashMap<>();
           //I placed books
           for(int i = 0; i < pieces.length ; i++)
               books.put(i,Integer.parseInt(pieces[i]));
             objs.add(books.size());


            while (tempLibCount != 0)
            {
                //sign up time  ---- scanned perDay
                pieces = reader.nextLine().split(" ");
                libs.add(new Lib(Integer.parseInt(pieces[1]),Integer.parseInt(pieces[2])));

                pieces = reader.nextLine().split(" ");
               LinkedHashMap<Integer,Integer> libBook = new LinkedHashMap<>();

               for (int i = 0; i < pieces.length ; i++)
               {
                   int x = Integer.parseInt(pieces[i]);

                   libBook.put(x,books.get(x));
               }
               libs.get(libs.size()-1).setBooks(libBook);

                tempLibCount--;
            }

           reader.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();

       }


       return objs;
   }


}
