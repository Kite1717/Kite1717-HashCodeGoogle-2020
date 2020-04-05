import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static String helperPath;

    static {
        helperPath = "";
        try {
            helperPath = new File(".").getCanonicalPath();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static final String[] InFiles = getDataInPath();
    private static final String[] OutFiles = getDataOutPath();


/*
AB: simple greedy
CF: greedy, choice a good library from one by one.
D: simulated annealing
E: local optimization (we evaluate solutions by min-cost-flow)
 */
    public static void main(String[] args)  {

       for(int i = 2; i < 3 ; i++) {
            int libCount = 0, deadLine = 0, booksCount = 0;

            ArrayList<Lib> libs = new ArrayList<>();
            ArrayList<Object> objects = Helper.readTheFile(InFiles[i], libs);
            libCount = (int) objects.get(0);
            deadLine = (int) objects.get(1);
            booksCount = (int) objects.get(2);


            Scaner scaner = new Scaner(deadLine, libs, booksCount);
            int scannedLib = scaner.findBestLibAndAdd();


          System.out.println((char)(i +65) + " score : " + formatter(scaner.getTotalScore()));

          Helper.writer(OutFiles[i], scannedLib);
          Helper.logs = new ArrayList<>();
        }

    }

    private  static  String[]  getDataInPath()  {


        return  new String[]{
                helperPath + "\\Solution\\dataIn\\a_example.txt",
                helperPath + "\\Solution\\dataIn\\b_read_on.txt",
                helperPath + "\\Solution\\dataIn\\c_incunabula.txt",
                helperPath + "\\Solution\\dataIn\\d_tough_choices.txt",
                helperPath + "\\Solution\\dataIn\\e_so_many_books.txt",
                helperPath + "\\Solution\\dataIn\\f_libraries_of_the_world.txt",

        };

    }

    private static String[] getDataOutPath()
    {
            return new String[]{
                    helperPath + "\\Solution\\dataOut\\a_example.txt",
                    helperPath + "\\Solution\\dataOut\\b_read_on.txt",
                    helperPath + "\\Solution\\dataOut\\c_incunabula.txt",
                    helperPath + "\\Solution\\dataOut\\d_tough_choices.txt",
                    helperPath + "\\Solution\\dataOut\\e_so_many_books.txt",
                    helperPath + "\\Solution\\dataOut\\f_libraries_of_the_world.txt",
               };
    }

    private static String formatter(int x)
    {
        char [] arr = String.valueOf(x).toCharArray();

        StringBuilder res = new StringBuilder();

        for(int i = arr.length -1 ,counter = 0; i >= 0 ; i--)
        {
            counter++;
            res.append(arr[i]);
            if(counter % 3 == 0)
            {
                res.append(".");
            }

        }

        return res.reverse().toString();
    }
}
